package by.krainet.matveenko.serviceforcv.repository;

import by.krainet.matveenko.serviceforcv.models.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {

    Page<Direction> findById(Long id, Pageable pageable);

    Page<Direction> findAll(Pageable pageable);

    Page<Direction> findDirectionsByNameContainingIgnoreCase(String name, Pageable pageable);
}