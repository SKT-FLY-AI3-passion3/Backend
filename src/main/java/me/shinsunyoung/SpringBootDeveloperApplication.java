package me.shinsunyoung;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1p1beta1.SpeechSettings;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static java.awt.SystemColor.text;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
//        synthesizeText("안녕하세요. 만나서 반가워요. 찾으시는 곳이 여기 맞나요?");
    }

    /**
     * Demonstrates using the Text to Speech client to synthesize text or ssml.
     *
     * @param text the raw text to be synthesized. (e.g., "Hello there!")
     * @throws Exception on TextToSpeechClient Errors.
     */
//    public static ByteString synthesizeText(String text) throws Exception {
//        CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(getCredentials());
//        // Instantiates a client
//        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(TextToSpeechSettings.newBuilder().setCredentialsProvider(credentialsProvider).build())) {
//            // Set the text input to be synthesized
//            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
//
//            // Build the voice request
//            VoiceSelectionParams voice =
//                    VoiceSelectionParams.newBuilder()
//                            .setLanguageCode("ko-KR") // languageCode = "en_us"
//                            .setSsmlGender(SsmlVoiceGender.FEMALE) // ssmlVoiceGender = SsmlVoiceGender.FEMALE
//                            .build();
//
//            // Select the type of audio file you want returned
//            AudioConfig audioConfig =
//                    AudioConfig.newBuilder()
//                            .setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
//                            .build();
//
//            // Perform the text-to-speech request
//            SynthesizeSpeechResponse response =
//                    textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
//
//            // Get the audio contents from the response
//            ByteString audioContents = response.getAudioContent();
//
//            // Write the response to the output file.
//            try (OutputStream out = new FileOutputStream("output.mp3")) {
//                out.write(audioContents.toByteArray());
//                System.out.println("Audio content written to file \"output.mp3\"");
//                return audioContents;
//            }
//        }
//
//
//    }

    @Bean
    public ServletWebServerFactory servletContainer()


    {
        // Enable SSL Trafic
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        // Add HTTP to HTTPS redirect
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

        return tomcat;
    }
    /*
    We need to redirect from HTTP to HTTPS. Without SSL, this application used
    port 8082. With SSL it will use port 8443. So, any request for 8082 needs to be
    redirected to HTTPS on 8443.
     */
    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(443);
        return connector;
    }
//    private static Credentials getCredentials() throws Exception {
//        FileInputStream credentialsStream = new FileInputStream("./vaulted-fort-358506-3f5080aabb57.json");
//        return GoogleCredentials.fromStream(credentialsStream);
//    }
}