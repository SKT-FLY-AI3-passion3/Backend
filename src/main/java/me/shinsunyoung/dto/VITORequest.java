package me.shinsunyoung.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VITORequest {

    private byte[] data;
    private String fileName;
    private String contentType;

    public VITORequest(byte[] data, String fileName, String contentType) {
        this.data = data;
        this.fileName = fileName;
        this.contentType = contentType;
    }

}