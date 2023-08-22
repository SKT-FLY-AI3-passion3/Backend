package me.shinsunyoung.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AudioRequest {
    private byte[] audioData;
    private String extension;

    public AudioRequest(byte[] audioData, String extension) {
        this.audioData = audioData;
        this.extension = extension;
    }
}

