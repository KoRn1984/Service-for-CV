package by.krainet.matveenko.serviceforcv.service;

import by.krainet.matveenko.serviceforcv.service.dto.ApplicantDto;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {
    ApplicantDto findById(Long id);

    List<ApplicantDto> findApplicantsByNameContainingIgnoreCase(String surname, String name, Pageable paging);

    ApplicantDto saveApplicant(ApplicantDto applicant);

    Optional<ApplicantDto> uploadPhoto(Long id, MultipartFile file);

    Optional<Resource> downloadPhoto(Long id);

    Optional<ApplicantDto> uploadCv(Long id, MultipartFile file);

    Optional<Resource> downloadCv(Long id);
}