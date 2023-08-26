package passion3.BackEnd.controller;

import passion3.BackEnd.Entity.BacketEntity;
import passion3.BackEnd.Service.BacketService;
import passion3.BackEnd.dto.BacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backet")
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
