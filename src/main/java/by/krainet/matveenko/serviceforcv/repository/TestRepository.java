package by.krainet.matveenko.serviceforcv.repository;

import by.krainet.matveenko.serviceforcv.models.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

    Page<Test> findById(Long id, Pageable pageable);

    Page<Test> findAll(Pageable pageable);

    Page<Test> findTestsByNameContainingIgnoreCase(String name, Pageable pageable);
}