package passion3.BackEnd.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MenuLookupDTO {
    private String main;
    private int count;
    private String side;
    private String drink;

    public MenuLookupDTO(String main, int count,String side,String drink) {
        this.main = main;
        this.count = count;
        this.side=side;
        this.drink=drink;
    }

}