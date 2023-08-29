package passion3.BackEnd.Service;

import java.io.IOException;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.cx.v3beta1.*;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import org.springframework.stereotype.Service;
@Service
public class ChatbotService {
    private static final String LOCATION_ID = "asia-northeast1";
    private static final String PROJECT_ID = "vaulted-fort-358506";
    private static final String AGENT_ID = "f31dc17b-228d-4d2b-a0da-73f059ad997b";
    private static final String SESSION_ID = "1";

    SessionsSettings sessionsSettings = createSessionSettings();
    SessionsClient sessionsClient = SessionsClient.create(sessionsSettings);
    SessionName session=SessionName.ofProjectLocationAgentSessionName(PROJECT_ID, LOCATION_ID, AGENT_ID, SESSION_ID);
    private Map<String, SessionName> sessionMap = new ConcurrentHashMap<>();


    public ChatbotService() throws IOException {
        System.out.println("err");
    }

    public boolean sessionExists(String sessionName) {
        return sessionMap.containsKey(sessionName);
    }

    public SessionName getSession(String sessionName) {
        return sessionMap.get(sessionName);
    }

    public SessionName createSession(String sessionName) throws IOException {
        SessionName session = SessionMake(sessionName);
        sessionMap.put(sessionName, session);
        return session;
    }
    public SessionName SessionMake(String session_name) throws IOException {
        //생성한 설정으로 클라이언트 생성
        SessionName session=SessionName.ofProjectLocationAgentSessionName(PROJECT_ID, LOCATION_ID, AGENT_ID, session_name);
        sessionMap.put(session_name, session);  // session_name과 SessionName을 맵에 저장
        return session;

    }
    public StringBuilder make(String Text,String session_name) throws IOException {
        //엔드포인트 설정과 Credential 설정

        SessionName session = sessionMap.get(session_name);
        SessionsSettings sessionsSettings = createSessionSettings();
        try (SessionsClient sessionsClient = SessionsClient.create(sessionsSettings))
        {
            // 챗봇과 대화를 할 세선 생성
            // Request 생성 (세션, 입력 문장)
            DetectIntentRequest request = createDetectIntentRequest(session, Text);

            // 챗봇의 요청하고 응답 받기
            DetectIntentResponse response = sessionsClient.detectIntent(request);
            //응답의 쿼리결과에서 응답 메세지만 가져오기
            List<ResponseMessage> responseMessages= response.getQueryResult().getResponseMessagesList();

            StringBuilder combinedBody = new StringBuilder();
            //여러문장으로 이러어져 있을 경우 반복해서 출력

            for(ResponseMessage responseMessage : responseMessages) {
                String body = responseMessage.getText().getText(0);
                combinedBody.append(body).append("\n"); // \n는 각 body 사이에 줄바꿈을 추가합니다.
            }
            System.out.println(combinedBody.toString());

            return combinedBody;
        }

    }

    private SessionEntityType sessionEntityType;
    public void SessionList() {
        List<EntityType.Entity> list=sessionEntityType.getEntitiesList();
        System.out.println(list);
    }

    private static SessionsSettings createSessionSettings() throws IOException {
        SessionsSettings.Builder sessionsSettingsBuilder = SessionsSettings.newBuilder();
        //엔드포인트 설정
        sessionsSettingsBuilder.setEndpoint(LOCATION_ID + "-dialogflow.googleapis.com:443");
        // Credential 설정
        try (FileInputStream credentialsStream = new FileInputStream("./application_default_credentials.json")) {
            sessionsSettingsBuilder.setCredentialsProvider(FixedCredentialsProvider.create(GoogleCredentials.fromStream(credentialsStream)));
        }

        //빌드
        return sessionsSettingsBuilder.build();
    }

    private DetectIntentRequest createDetectIntentRequest(SessionName session, String text) {
        TextInput.Builder textInput = TextInput.newBuilder().setText(text);
        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).setLanguageCode("ko-KR").build();

        return DetectIntentRequest.newBuilder()
                .setSession(session.toString())
                .setQueryInput(queryInput)
                .build();
    }

}