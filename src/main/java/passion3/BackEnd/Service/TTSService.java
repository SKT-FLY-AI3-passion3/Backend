package passion3.BackEnd.Service;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.protobuf.ByteString;
import passion3.BackEnd.dto.SynthesizeRequest;
import com.google.cloud.texttospeech.v1.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class TTSService {

    // Method to retrieve API credentials. It should be implemented based on your setup.
    private static Credentials getCredentials() throws Exception {
        FileInputStream credentialsStream = new FileInputStream("./vaulted-fort-358506-3f5080aabb57.json");
        return GoogleCredentials.fromStream(credentialsStream);
    }

    public ByteArrayResource synthesizeText(SynthesizeRequest request) throws Exception {
        ByteString audioContents;

        CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(getCredentials());

        // Setting up TextToSpeech client
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(TextToSpeechSettings.newBuilder().setCredentialsProvider(credentialsProvider).build())) {

            // Defining the text to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder()
                    .setText(request.getText())
                    .build();

            // Specifying voice parameters
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("ko-KR")
                    .setSsmlGender(SsmlVoiceGender.FEMALE)
                    .build();

            // Specifying audio output configuration
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            // Making the request to the Text-to-Speech API
            SynthesizeSpeechResponse apiResponse = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            // ... (the same as before) ...

            // Extracting the audio content from the response
            audioContents = apiResponse.getAudioContent();
        }

        // Save the audio content to a local MP3 file
        String outputPath = "output.mp3";
        try (OutputStream out = new FileOutputStream(outputPath)) {
            out.write(audioContents.toByteArray());
            System.out.println("Audio content written to file \"output.mp3\"");
        }

        // Convert the audio content to ByteArrayResource
        ByteArrayResource resource = new ByteArrayResource(audioContents.toByteArray());
        return resource;
    }
}
