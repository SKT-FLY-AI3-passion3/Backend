package me.shinsunyoung.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AudioRequest {
    private byte[] audioData;

    public AudioRequest(byte[] audioData) {
        this.audioData = audioData;
    }
}
