package passion3.BackEnd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodChangeDTO {

    private String menu;
    private String side;
    private String drink;

    public boolean isValid() {
        return menu != null && menu.contains("세트");
    }

}