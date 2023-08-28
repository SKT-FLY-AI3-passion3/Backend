package passion3.BackEnd.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FoodOrder { // 'Order'는 Java에서 기본 제공하는 클래스이므로 'FoodOrder'로 이름 변경

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String menu;
    private Integer count;


    // 기본 생성자, getters, setters, etc.
}
