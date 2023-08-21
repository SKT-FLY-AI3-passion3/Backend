package me.shinsunyoung.Service;

import me.shinsunyoung.Entity.BacketEntity;
import me.shinsunyoung.Repository.BacketRepository;
import me.shinsunyoung.dto.BacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class BacketService {

    private final BacketRepository backetRepository;

    @Autowired
    public BacketService(BacketRepository backetRepository) {
        this.backetRepository = backetRepository;
    }



    @Transactional
    public BacketEntity insertBacket(BacketDTO dto) {
        BacketEntity backetEntity = new BacketEntity(dto);
        return backetRepository.save(backetEntity);
    }

    @Transactional
    public void deletePerson(Long id) {
        backetRepository.deleteById(id); // 수정된 부분
    }
}
