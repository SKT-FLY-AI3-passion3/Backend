package me.shinsunyoung.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.cx.v3beta1.*;
import org.springframework.stereotype.Service;
import org.apache.commons.text.StringEscapeUtils;
import java.io.FileInputStream;

import static com.google.cloud.dialogflow.cx.v3beta1.ResponseMessage.*;

@Service
public class SessionsCService {
    public void make() throws IOException {
        String locationId = "asia-northeast1";
        String projectId = "lunar-goal-396113";
        String agentId = "1cf31bc4-3a8f-40f4-9418-6d5fb61bc5b0";
        String sessionId = "1";
        List<String> texts = new ArrayList<>(List.of("my-list", "of-texts"));

        SessionsSettings.Builder sessionsSettingsBuilder = SessionsSettings.newBuilder();
        sessionsSettingsBuilder.setEndpoint(locationId + "-dialogflow.googleapis.com:443");
        FileInputStream credentialsStream = new FileInputStream("./key.json");
        sessionsSettingsBuilder.setCredentialsProvider(FixedCredentialsProvider.create(GoogleCredentials.fromStream(credentialsStream)));
        SessionsSettings sessionsSettings = sessionsSettingsBuilder.build();
        try (SessionsClient sessionsClient = SessionsClient.create(sessionsSettings)) {
            SessionName session =
                    SessionName.ofProjectLocationAgentSessionName(projectId, locationId, agentId, sessionId);

            java.lang.String text = "안녕하세요";
            TextInput.Builder textInput = TextInput.newBuilder().setText(text);
            QueryInput queryInput =
                    QueryInput.newBuilder().setText(textInput).setLanguageCode("ko-KR").build();
            QueryParameters queryParameters =
                    QueryParameters.newBuilder().setDisableWebhook(true).build();

            DetectIntentRequest request =
                    DetectIntentRequest.newBuilder()
                            .setSession(session.toString())
                            .setQueryInput(queryInput)
                            .setQueryParams(queryParameters)
                            .build();
            System.out.println(request.toString());
            DetectIntentResponse response = sessionsClient.detectIntent(request);
            QueryResult queryResult = response.getQueryResult();
            System.out.println("====================");
            Text responseText = queryResult.getResponseMessages(0).getText();
            String decodedText = StringEscapeUtils.unescapeJava(responseText.toString());
            ResponseMessage responseMessage=queryResult.getResponseMessages(0);
            Text text1=responseMessage.getText();
            String body=text1.getText(0);
            System.out.println(body);
//            }
        }
    }
}