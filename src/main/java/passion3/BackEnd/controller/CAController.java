package passion3.BackEnd.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/.well-known/pki-validation")
    public class CAController {

    @GetMapping("/{fileName}.txt")
    public ResponseEntity<Resource> getPkiValidationFile(String fileName) throws IOException {
        Resource resource = new ClassPathResource("/.well-known/pki-validation/" + fileName +".txt");
        System.out.println(resource);
        return ResponseEntity.ok(resource);
    }
}
