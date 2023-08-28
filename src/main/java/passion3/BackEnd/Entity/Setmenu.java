package passion3.BackEnd.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "setmenu")
@Immutable
@Getter
public class Setmenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private Integer price;

}
