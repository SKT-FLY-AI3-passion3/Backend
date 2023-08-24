package me.shinsunyoung.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDTO {
    private String access_token;
    private long expire_at;

}