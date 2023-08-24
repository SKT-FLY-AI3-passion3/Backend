package me.shinsunyoung.controller;

import me.shinsunyoung.Service.AudioService;
import me.shinsunyoung.Service.VITOService;
import me.shinsunyoung.dto.AudioRequest;
import me.shinsunyoung.dto.VITORequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


@RestController
public class AudioController {
    @Autowired
    private AudioService  audioService;

    @PostMapping("/stt")
    public ResponseEntity<String> convertAudioToText(@RequestParam("file") MultipartFile audioFile) {
        try {
            String originalFilename = audioFile.getOriginalFilename();
            String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf('.') + 1) : "";

            if (!Arrays.asList("mp3", "wav", "flac", "ogg","m4a").contains(extension)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Unsupported file format. Supported formats are mp3, wav, flac, and ogg.");
            }

            byte[] audioData = audioFile.getBytes();

            // 오디오 파일을 로컬에 저장
            Path uploadsDir = Paths.get("uploads");
            if (!Files.exists(uploadsDir)) {
                Files.createDirectories(uploadsDir);  // 만약 디렉토리가 없다면 생성
            }

            Path outputPath = uploadsDir.resolve(originalFilename);
            Files.write(outputPath, audioData);

            AudioRequest audioRequest = new AudioRequest(audioData, extension);

            String transcription = audioService.convertAudioToText(audioRequest);
            System.out.println("Recognized Text2: " + transcription);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("text", "plain", Charset.forName("UTF-8")));
            return new ResponseEntity<>(transcription, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during audio conversion: " + e.getMessage());
        }
    }
    @Autowired
    private VITOService vitoService;
    @PostMapping("/stt/vito")
    public ResponseEntity<String> convertAudioToTextwithvito(@RequestParam("file") MultipartFile audioFile) {
        try {
            String originalFilename = audioFile.getOriginalFilename();
            String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf('.') + 1) : "";

            if (!Arrays.asList("mp3", "wav", "flac", "ogg","m4a").contains(extension)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Unsupported file format. Supported formats are mp3, wav, flac, and ogg.");
            }

            byte[] audioData = audioFile.getBytes();

            // 오디오 파일을 로컬에 저장
            Path uploadsDir = Paths.get("uploads");
            if (!Files.exists(uploadsDir)) {
                Files.createDirectories(uploadsDir);  // 만약 디렉토리가 없다면 생성
            }

            Path outputPath = uploadsDir.resolve(originalFilename);
            Files.write(outputPath, audioData);

            VITORequest vitoRequest = new VITORequest(audioData, originalFilename, URLConnection.guessContentTypeFromName(originalFilename));

            String transcription = vitoService.convertAudioToText(vitoRequest);

            return ResponseEntity.ok(transcription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during audio conversion: " + e.getMessage());
        }
    }




    @GetMapping("/")
    public String isAvailable() {
        return "Server is running!";
    }

}
