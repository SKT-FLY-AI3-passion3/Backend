package me.shinsunyoung.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import me.shinsunyoung.dto.BacketDTO;


@Entity
public class BacketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String hamberger;
    private  String side;
    private String drinkanddessert;

    // 기본 생성자, 게터, 세터

    public BacketEntity(BacketDTO dto) {
        this.hamberger = dto.getHamberger();
        this.side = dto.getSide();
        this.drinkanddessert=dto.getDrinkanddessert();
    }
}


