package me.shinsunyoung.controller;

import me.shinsunyoung.Entity.BacketEntity;
import me.shinsunyoung.Service.BacketService;
import me.shinsunyoung.dto.BacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class BacketController {

    private final BacketService backetService;

    @Autowired
    public BacketController(BacketService backetService) {
        this.backetService = backetService;
    }


    @PostMapping("/insert")
    public ResponseEntity<BacketEntity> insertBacket(@RequestBody BacketDTO dto) {
        BacketEntity entity = backetService.insertBacket(dto);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable Long id) {
        backetService.deletePerson(id); // 수정된 부분
    }
}
