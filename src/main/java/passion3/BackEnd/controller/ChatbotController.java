package passion3.BackEnd.controller;

import com.google.cloud.dialogflow.cx.v3beta1.SessionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import passion3.BackEnd.Service.SessionsCService;
import passion3.BackEnd.Service.TTSService;
import passion3.BackEnd.dto.ChatbotDTO;
import passion3.BackEnd.dto.SynthesizeRequest;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
public class ChatbotController {

    @Autowired
    private SessionsCService sessionsCService;
    @PostMapping("/chatbot")
    public ResponseEntity<String> chatWithBot(@RequestBody ChatbotDTO chatbotDTO) throws IOException {
        SessionName session;
        String sessionName = chatbotDTO.getSession();
        if (sessionsCService.sessionExists(sessionName)) {
            session = sessionsCService.getSession(sessionName);
        } else {
            session = sessionsCService.SessionMake(sessionName);
        }
        StringBuilder response = sessionsCService.make(chatbotDTO.getText(),chatbotDTO.getSession());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("UTF-8")));
        return new ResponseEntity<>(response.toString(), headers, HttpStatus.OK);
    }
}
