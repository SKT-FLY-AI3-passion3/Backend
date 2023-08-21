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
    private String name;
    private int count;
    private int price;

    // 기본 생성자, 게터, 세터

    public BacketEntity(BacketDTO dto) {
        this.name = dto.getName();
        this.count = dto.getCount();
        this.price=dto.getPrice();
    }
}


