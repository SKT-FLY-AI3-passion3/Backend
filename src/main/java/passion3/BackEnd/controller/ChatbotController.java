package passion3.BackEnd.controller;

import com.google.cloud.dialogflow.cx.v3beta1.SessionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import passion3.BackEnd.Service.ChatbotService;
import passion3.BackEnd.dto.ChatbotDTO;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;
    @PostMapping("/chatbot")
    public ResponseEntity<String> chatWithBot(@RequestBody ChatbotDTO chatbotDTO) throws IOException {
        SessionName session;
        System.out.println(chatbotDTO.getSession());
        String sessionName = chatbotDTO.getSession();
        if (chatbotService.sessionExists(sessionName)) {
            session = chatbotService.getSession(sessionName);
        } else {
            session = chatbotService.SessionMake(sessionName);
        }
        StringBuilder response = chatbotService.make(chatbotDTO.getText(),chatbotDTO.getSession());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("UTF-8")));
        return new ResponseEntity<>(response.toString(), headers, HttpStatus.OK);
    }
}
