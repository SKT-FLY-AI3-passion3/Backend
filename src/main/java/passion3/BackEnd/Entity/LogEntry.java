package passion3.BackEnd.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private String level;
    private String message;

    // 기본 생성자
    public LogEntry() {}

    // 모든 필드를 초기화하는 생성자
    public LogEntry(LocalDateTime date, String level, String message) {
        this.date = date;
        this.level = level;
        this.message = message;
    }

    // Getter & Setter 메소드...
}

