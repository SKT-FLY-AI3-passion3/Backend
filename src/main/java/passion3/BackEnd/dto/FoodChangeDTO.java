package passion3.BackEnd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodChangeDTO {

    private String menu;
    private String side;
    private String drink;

    public boolean isSet() {
        return menu != null && menu.contains("세트");
    }

    public boolean isInvalid() {
        return menu.equals("null")  && side.equals("null") && drink.equals("null");
    }

}