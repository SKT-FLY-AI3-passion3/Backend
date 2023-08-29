package passion3.BackEnd.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MenuLookupDTO {
    private String main;
    private int count;

    public MenuLookupDTO(String main, int count) {
        this.main = main;
        this.count = count;
    }

}