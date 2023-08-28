package passion3.BackEnd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderDTO {

    private String menu;
    private Integer count;

    public boolean isValid() {
        return menu != null || count != null;
    }

    public boolean isSetMenu() {
        return menu != null && menu.contains("세트");
    }
}