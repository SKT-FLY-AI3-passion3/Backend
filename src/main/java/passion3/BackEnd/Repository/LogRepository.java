package passion3.BackEnd.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import passion3.BackEnd.Entity.LogEntry;

@Repository
public interface LogRepository extends JpaRepository<LogEntry, Long> {
    // 필요한 쿼리 메소드 추가 가능
}
