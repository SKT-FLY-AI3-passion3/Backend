package me.shinsunyoung.Service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class StartupRunner implements CommandLineRunner {


    private final TokenService tokenService;
    public StartupRunner(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    public void run(String... args) throws Exception {
        String token = tokenService.fetchToken();
        System.out.println("VITO JWT token: " + token);

    }
}