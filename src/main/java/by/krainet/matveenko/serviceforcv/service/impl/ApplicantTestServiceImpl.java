package by.krainet.matveenko.serviceforcv.service.impl;

import by.krainet.matveenko.serviceforcv.models.ApplicantTest;
import by.krainet.matveenko.serviceforcv.repository.ApplicantTestRepository;
import by.krainet.matveenko.serviceforcv.service.ApplicantTestService;
import by.krainet.matveenko.serviceforcv.service.convertor.ApplicantTestMapper;
import by.krainet.matveenko.serviceforcv.service.dto.ApplicantTestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class ApplicantTestServiceImpl implements ApplicantTestService {
    private final ApplicantTestRepository applicantTestRepository;
    private final ApplicantTestMapper applicantTestMapper;

    @Override
    public ApplicantTestDto findById(Long id) {
        return Optional.of(getById(id)).map(applicantTestMapper::modelToDto).get();
    }

    private ApplicantTest getById(Long id) {
        return applicantTestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Test of applicant with id: " + id + " not found!"));
    }

    @Override
    public List<ApplicantTestDto> findApplicantTestsByApplicantContainingIgnoreCase(String surname, String nametest, Pageable paging) {
        Page<ApplicantTest> result = applicantTestRepository.findAll(paging);
        if (surname != null) {
            return result.stream()
                    .filter(applicantTest -> applicantTest.getApplicant().getSurname().contains(surname))
                    .map(applicantTestMapper::modelToDto)
                    .collect(Collectors.toList());
        } else if (nametest != null) {
            return result.stream()
                    .filter(applicantTest -> applicantTest.getTest().getName().contains(nametest))
                    .map(applicantTestMapper::modelToDto)
                    .collect(Collectors.toList());
        }
        return applicantTestMapper.toListDto(result.toList());
    }

    @Override
    public ApplicantTestDto saveApplicantTest(ApplicantTestDto applicantTest) {
        return applicantTestMapper.modelToDto(applicantTestRepository.save(applicantTestMapper.dtoToModel(applicantTest)));
    }
}