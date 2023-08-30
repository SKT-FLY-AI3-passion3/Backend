package passion3.BackEnd.Service;


import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import java.util.stream.Collectors;

@Service
public class LogService {

    private static final String LOG_PATH = "logs/app.log";  // 로그 파일 위치

    public String getLogs() {
        try {
            return Files.lines(Paths.get(LOG_PATH)).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            return "Error reading log file: " + e.getMessage();
        }
    }
}

