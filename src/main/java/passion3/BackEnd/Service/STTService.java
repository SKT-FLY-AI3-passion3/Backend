package passion3.BackEnd.Service;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1p1beta1.*;
import com.google.protobuf.ByteString;
import passion3.BackEnd.dto.AudioRequest;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.List;

@Service
public class STTService {

    public String convertAudioToText(AudioRequest audioRequest) throws Exception {
        RecognitionConfig.AudioEncoding encoding;
        String extension = audioRequest.getExtension();
        int input_channel=1;
        int sampling_rate=16000;
        switch (extension) {
            case "mp3":
                encoding = RecognitionConfig.AudioEncoding.MP3;
                break;
            case "wav":
                encoding = RecognitionConfig.AudioEncoding.LINEAR16;
                break;
            case "flac":
                sampling_rate=44100;
                input_channel=1;
                encoding = RecognitionConfig.AudioEncoding.FLAC;
                break;
            case "ogg":
                encoding = RecognitionConfig.AudioEncoding.OGG_OPUS;
                break;
            default:
                throw new IllegalArgumentException("Unsupported file format: " + extension);
        }
        System.out.println(encoding);
        CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(getCredentials());
        try (SpeechClient speechClient = SpeechClient.create(SpeechSettings.newBuilder().setCredentialsProvider(credentialsProvider).build())) {
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(encoding)  // Set encoding based on file extension
                            .setAudioChannelCount(input_channel)
                            .setSampleRateHertz(sampling_rate)
                            .setModel("latest_long") // 여기에 사용하려는 고급 모델을 지정합니다.
                            .setLanguageCode("ko-KR")
                            .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(audioRequest.getAudioData()))
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);
            System.out.println(response);
            List<SpeechRecognitionResult> results = response.getResultsList();
            String transcript = "";

            for (SpeechRecognitionResult result : response.getResultsList()) {
                // 현재 결과에서 가장 높은 신뢰도를 가진 대안을 가져옵니다.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                transcript += alternative.getTranscript() + "\n";
            }

            System.out.println("Recognized Text: " + transcript);
            return transcript;
        }
    }

    private Credentials getCredentials() throws Exception {
        FileInputStream credentialsStream = new FileInputStream("./vaulted-fort-358506-3f5080aabb57.json");
        return GoogleCredentials.fromStream(credentialsStream);
    }
}