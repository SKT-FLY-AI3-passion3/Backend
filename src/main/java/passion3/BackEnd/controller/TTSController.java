package passion3.BackEnd.controller;

import passion3.BackEnd.Service.TTSService;
import passion3.BackEnd.dto.SynthesizeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// TextToSpeechController.java
@RestController
public class TTSController {

    @Autowired
    private TTSService TTSService;
    @PostMapping("/tts")
    public ResponseEntity<ByteArrayResource> synthesize(@RequestBody SynthesizeRequest request) {
        try {
            ByteArrayResource resource = TTSService.synthesizeText(request);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("audio/mpeg"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"output.mp3\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
