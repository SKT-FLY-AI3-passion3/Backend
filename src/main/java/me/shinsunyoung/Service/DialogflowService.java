//package me.shinsunyoung.Service;
//
//import com.google.api.client.util.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import com.google.cloud.dialogflow.cx.v3.*;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.UUID;
//
//import static com.mysql.cj.conf.PropertyKey.logger;
//
//@Service
//public class DialogflowService {
//
//    private final String projectId = "lunar-goal-396113";
//    private final String location = "ko-KR";  // 예: "global"
//    private final String agentId = "8c87d045-2bac-43e3-bb28-1f79ef6dd80f";
//
////    public String detectIntent(String userQuery) {
//        SessionsClient sessionsClient = null;
//        try {
//            sessionsClient = SessionsClient.create();
//        } catch (IOException e) {
//            System.out.println("sad");
//            // 여기서 적절한 에러 처리를 할 수 있습니다.
//        }
//        String sessionId = "1";  // 예: UUID.randomUUID().toString()
//        SessionName sessionName = SessionName.of(projectId, location, agentId, sessionId);
//
//        QueryInput queryInput = QueryInput.newBuilder()
//                .setText(TextInput.newBuilder()
//                        .setText(userQuery)
//                        .build())
//                .setLanguageCode("en")
//                .build();
//
//        DetectIntentRequest request = DetectIntentRequest.newBuilder()
//                .setSession(sessionName.toString())
//                .setQueryInput(queryInput)
//                .build();
//
//        DetectIntentResponse response = sessionsClient.detectIntent(request);
//
//        return response.getQueryResult().getResponseMessagesList().get(0).getText().getText(0);
//    }
//}