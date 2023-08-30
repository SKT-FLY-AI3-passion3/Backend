package passion3.BackEnd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderDTO {

    private String menu;
    private Integer count;

    public boolean isInvalid() {
        return menu .equals("null")  && count == null;
    }

    public boolean isSetMenu() {
        return menu.contains("세트");
    }
}