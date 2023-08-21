package me.shinsunyoung.Repository;

import me.shinsunyoung.Entity.BacketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BacketRepository extends JpaRepository<BacketEntity, Long> {
}

