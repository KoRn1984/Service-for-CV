package by.krainet.matveenko.serviceforcv.service.impl;

import by.krainet.matveenko.serviceforcv.models.Applicant;
import by.krainet.matveenko.serviceforcv.repository.ApplicantRepository;
import by.krainet.matveenko.serviceforcv.service.ApplicantService;
import by.krainet.matveenko.serviceforcv.service.convertor.ApplicantMapper;
import by.krainet.matveenko.serviceforcv.service.dto.ApplicantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    private final FileStorageServiceImpl fileStorageService;

    @Override
    public ApplicantDto findById(Long id) {
        return Optional.of(getById(id)).map(applicantMapper::modelToDto).get();
    }

    private Applicant getById(Long id) {
        return applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Applicant with id: " + id + " not found!"));
    }

    @Override
    public List<ApplicantDto> findApplicantsByNameContainingIgnoreCase(String surname, String name, Pageable paging) {
        Page<Applicant> page = applicantRepository.findAll(paging);
        if (surname != null) {
            return page.stream()
                    .filter(applicant -> applicant.getSurname().contains(surname))
                    .map(applicantMapper::modelToDto)
                    .collect(Collectors.toList());
        } else if (name != null) {
            return page.stream()
                    .filter(applicant -> applicant.getName().contains(name))
                    .map(applicantMapper::modelToDto)
                    .collect(Collectors.toList());
        }
        return applicantMapper.toListDto(page.toList());
    }

    @Override
    public ApplicantDto saveApplicant(ApplicantDto applicant) {
        return applicantMapper.modelToDto(applicantRepository.save(applicantMapper.dtoToModel(applicant)));
    }

    @Override
    public Optional<ApplicantDto> uploadPhoto(Long id, MultipartFile file) {
        var photoName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        var resultApplicant = applicantRepository.findById(id).get();
        fileStorageService.storeFile(file, photoName);
        resultApplicant.setPhoto(photoName);
        log.info("Photo uploaded successfully!");
        return Optional.of(applicantMapper.modelToDto(applicantRepository.save(resultApplicant)));
    }

    @Override
    public Optional<Resource> downloadPhoto(Long id) {
        var resultApplicant = applicantRepository.findById(id).get();
        var photoName = resultApplicant.getPhoto();
        var photo = fileStorageService.loadFileAsResource(photoName);
        log.info("Photo downloaded successfully!");
        return Optional.of(photo);
    }

    @Override
    public Optional<ApplicantDto> uploadCv(Long id, MultipartFile file) {
        String cvName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Applicant resultApplicant = applicantRepository.findById(id).get();
        fileStorageService.storeFile(file, cvName);
        resultApplicant.setCv(cvName);
        log.info("CV uploaded successfully!");
        return Optional.of(applicantMapper.modelToDto(applicantRepository.save(resultApplicant)));
    }

    @Override
    public Optional<Resource> downloadCv(Long id) {
        Applicant resultApplicant = applicantRepository.findById(id).get();
        String cvName = resultApplicant.getCv();
        Resource cv = fileStorageService.loadFileAsResource(cvName);
        log.info("CV downloaded successfully!");
        return Optional.of(cv);
    }
}