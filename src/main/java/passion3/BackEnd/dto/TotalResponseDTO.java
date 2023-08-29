package passion3.BackEnd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalResponseDTO {
    private String text;

    public TotalResponseDTO(String text) {
        this.text = text;
    }

}