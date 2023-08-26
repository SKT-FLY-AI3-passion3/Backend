package passion3.BackEnd.Service;

import passion3.BackEnd.Entity.BacketEntity;
import passion3.BackEnd.Repository.BacketRepository;
import passion3.BackEnd.dto.BacketDTO;
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
