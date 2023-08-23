package me.shinsunyoung.dto;

import com.google.protobuf.ByteString;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SynthesizeResponse {
    private ByteString audioContent;

    // getters and setters
}