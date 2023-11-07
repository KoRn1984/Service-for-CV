package by.krainet.matveenko.serviceforcv.repository;

import by.krainet.matveenko.serviceforcv.models.Applicant;
import by.krainet.matveenko.serviceforcv.models.ApplicantTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantTestRepository extends JpaRepository<ApplicantTest, Long> {

    Page<ApplicantTest> findById(Long id, Pageable pageable);

    Page<ApplicantTest> findAll(Pageable pageable);

    Page<ApplicantTest> findApplicantTestsByTestContainingIgnoreCase(Applicant applicant, Pageable pageable);
}