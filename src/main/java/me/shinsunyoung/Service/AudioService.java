package me.shinsunyoung.Service;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1p1beta1.*;
import com.google.protobuf.ByteString;
import me.shinsunyoung.dto.AudioRequest;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.List;

@Service
public class AudioService {

    public String convertAudioToText(AudioRequest audioRequest) throws Exception {
        CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(getCredentials());
        try (SpeechClient speechClient = SpeechClient.create(SpeechSettings.newBuilder().setCredentialsProvider(credentialsProvider).build())) {
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.MP3)  // Set MP3 encoding
                            .setSampleRateHertz(16000)
                            .setLanguageCode("ko-KR")
                            .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(audioRequest.getAudioData()))
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            if (!results.isEmpty()) {
                SpeechRecognitionAlternative alternative = results.get(0).getAlternatives(0);
                String transcription = alternative.getTranscript();
                return transcription;
            } else {
                return "No transcription available.";
            }
        }
    }

    private Credentials getCredentials() throws Exception {
        FileInputStream credentialsStream = new FileInputStream("./vaulted-fort-358506-3f5080aabb57.json");
        return GoogleCredentials.fromStream(credentialsStream);
    }
}