package passion3.BackEnd.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "single")
@Immutable
@Getter
public class Single {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private Integer price;

    // getters, setters 등
}
