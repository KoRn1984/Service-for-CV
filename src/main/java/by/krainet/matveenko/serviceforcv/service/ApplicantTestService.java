package by.krainet.matveenko.serviceforcv.service;

import by.krainet.matveenko.serviceforcv.service.dto.ApplicantTestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicantTestService {

    ApplicantTestDto findById(Long id);

    List<ApplicantTestDto> findApplicantTestsByApplicantContainingIgnoreCase(String surname, String nametest, Pageable paging);

    ApplicantTestDto saveApplicantTest(ApplicantTestDto applicantTest);
}