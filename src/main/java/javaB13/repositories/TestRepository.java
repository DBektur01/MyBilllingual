package javaB13.repositories;

import javaB13.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
    boolean existsByTitle(String title);
}
