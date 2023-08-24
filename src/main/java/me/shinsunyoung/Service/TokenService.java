package me.shinsunyoung.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;

import me.shinsunyoung.dto.TokenResponseDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
@Service
public class TokenService {

    private final TokenStore tokenStore;

    public TokenService(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @PostConstruct
    public void init() throws IOException {
        String token = fetchToken();
        tokenStore.setToken(token);
    }
    public String  fetchToken() throws IOException {
        URL url = new URL("https://openapi.vito.ai/v1/authenticate");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("accept", "application/json");
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConn.setDoOutput(true);

        String data = "client_id=YrUZZWXotBeUOB1w5Tt8&client_secret=_Vab5ygU5FqvwXwoBysN5SVvodsuIp3DPKXQGZVC";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = httpConn.getOutputStream();
        stream.write(out);

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        s.close();
        ObjectMapper mapper = new ObjectMapper();
        TokenResponseDTO tokenResponse = mapper.readValue(response, TokenResponseDTO.class);
        return tokenResponse.getAccess_token();
    }

    public String getStoredToken() {
        return tokenStore.getToken();
    }

}