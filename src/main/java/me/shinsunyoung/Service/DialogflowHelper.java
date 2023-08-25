//package me.shinsunyoung.Service;
//
//
//import com.google.api.gax.core.FixedCredentialsProvider;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.dialogflow.cx.v3.*;
//import org.springframework.stereotype.Service;
//
//import com.google.api.gax.core.CredentialsProvider;
//import com.google.api.gax.core.FixedCredentialsProvider;
//import com.google.auth.Credentials;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.dialogflow.cx.v3.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import com.google.api.gax.core.CredentialsProvider;
//import com.google.api.gax.core.FixedCredentialsProvider;
//import com.google.auth.Credentials;
//import com.google.auth.oauth2.GoogleCredentials;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.UUID;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.UUID;
//
//@Service
//public class DialogflowHelper {
//    private final String PROJECT_ID = "lunar-goal-396113";
//    private final String AGENT_ID = "8c87d045-2bac-43e3-bb28-1f79ef6dd80f";
//    private final String LOCATION_ID = "asia-northeast1"; // asia-northeast1
//    private final String LANGUAGE_CODE = "ko-KR";
//    private final String SESSION_ID = UUID.randomUUID().toString();
//    private SessionsClient sessionsClient;
//    private SessionName sessionName;
//    public DialogflowHelper() {
//        try{
//            CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(getCredentials());
//            FileInputStream credentialsStream = new FileInputStream("./lunar_goal_396113_credentials.json");
//            GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
//
//            SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
//            settingsBuilder.setEndpoint(LOCATION_ID+"-dialogflow.googleapis.com:443");
//            SessionsSettings settings = settingsBuilder.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
//
//            sessionsClient = SessionsClient.create(settings);
//            sessionName = SessionName.ofProjectLocationAgentSessionName(PROJECT_ID, LOCATION_ID, AGENT_ID, SESSION_ID);
//
//            // for test
//            System.out.println("Success");
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public String sendQuery(String message) {
//        TextInput.Builder textInput = TextInput.newBuilder().setText(message);
//
//        QueryInput queryInput = QueryInput.newBuilder().setText(textInput)
//                .setLanguageCode(LANGUAGE_CODE)
//                .build();
//
//        DetectIntentRequest detectIntentRequest = DetectIntentRequest.newBuilder()
//                .setSession(sessionName.toString())
//                .setQueryInput(queryInput)
//                .build();
//
//        DetectIntentResponse response = sessionsClient.detectIntent(detectIntentRequest);
//
//        QueryResult queryResult = response.getQueryResult();
//
//        // for test
//        System.out.format("Query Text: '%s'\n", queryResult.getText());
//
//        return queryResult.getText();
//    }
//}
//
//    private Credentials getCredentials() throws IOException {
//        InputStream credentialsStream = getClass().getResourceAsStream("./lunar_goal_396113_credentials.json");
//        return GoogleCredentials.fromStream(credentialsStream);
//    }
//}
//
//
//
//
