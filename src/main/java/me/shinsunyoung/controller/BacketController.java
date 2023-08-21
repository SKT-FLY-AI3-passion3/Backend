package me.shinsunyoung.controller;

import me.shinsunyoung.Entity.BacketEntity;
import me.shinsunyoung.Service.BacketService;
import me.shinsunyoung.dto.BacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BacketEntity insertBacket(@RequestBody BacketDTO dto) {
        return backetService.insertBacket(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable Long id) {
        backetService.deletePerson(id); // 수정된 부분
    }
}
