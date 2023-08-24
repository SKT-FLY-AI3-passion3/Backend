package me.shinsunyoung.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private String id;

    // Getter, Setter, 기본 생성자 등
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}