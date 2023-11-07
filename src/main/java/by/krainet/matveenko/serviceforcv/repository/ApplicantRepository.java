package by.krainet.matveenko.serviceforcv.repository;

import by.krainet.matveenko.serviceforcv.models.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Page<Applicant> findById(Long id, Pageable pageable);

    Page<Applicant> findAll(Pageable pageable);

    Page<Applicant> findApplicantsByNameContainingIgnoreCase(String name, Pageable pageable);
}