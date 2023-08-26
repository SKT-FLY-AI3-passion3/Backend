package passion3.BackEnd.Service;

import org.springframework.stereotype.Service;

@Service
public class TokenStore {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}