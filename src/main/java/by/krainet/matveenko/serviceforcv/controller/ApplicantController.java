package by.krainet.matveenko.serviceforcv.controller;

import by.krainet.matveenko.serviceforcv.service.ApplicantService;
import by.krainet.matveenko.serviceforcv.service.dto.ApplicantDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Tag(name="Applicant Controller", description="Кандидаты")
@Log4j2
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;

    @Operation(summary = "Поиск кандидата по Id", description = "Позволяет найти кандидата по его идентификатору")
    @GetMapping("/applicant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApplicantDto> getApplicantById(@PathVariable Long id) {
        return ResponseEntity.ok().body(applicantService.findById(id));
    }

    @Operation(summary = "Вывод всех кандидатов", description = "Позволяет вывести весь список кандидатов")
    @GetMapping("/applicants")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ApplicantDto>> getApplicant(
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return ResponseEntity.ok().body(applicantService.findApplicantsByNameContainingIgnoreCase(surname, name, PageRequest.of(page, size)));
    }

    @Operation(summary = "Добавление кандидата", description = "Позволяет добавить кандидата в сервис")
    @PostMapping("/applicant")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicantDto> createApplicant(@RequestBody ApplicantDto applicant) throws URISyntaxException {
        ApplicantDto result = applicantService.saveApplicant(applicant);
        log.info("Applicant created!");
        return ResponseEntity.created(new URI("/api/v1/applicants/" + result.getId())).body(result);
    }

    @Operation(summary = "Редактирование кандидата", description = "Позволяет отредактировать кандидата в сервисе")
    @PutMapping("/applicant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApplicantDto> updateApplicant(@PathVariable Long id, @RequestBody ApplicantDto applicant) {
        log.info("Applicant edited!");
        return ResponseEntity.ok().body(applicantService.saveApplicant(applicant));
    }

    @Operation(summary = "Загрузка фотографии кандидата",
               description = "Позволяет загрузить фотографию кандидата в сервис")
    @PostMapping("/applicant/{id}/upload_photo")
    public ResponseEntity<ApplicantDto> uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        return ResponseEntity.ok().body(applicantService.uploadPhoto(id, file).get());
    }

    @Operation(summary = "Скачивание фотографии кандидата",
            description = "Позволяет скачать фотографию кандидата из сервиса")
    @GetMapping("/applicant/{id}/download_photo")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = applicantService.downloadPhoto(id).get();
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Operation(summary = "Загрузка резюме кандидата",
            description = "Позволяет загрузить резюме кандидата в сервис")
    @PostMapping("/applicant/{id}/upload_cv")
    public ResponseEntity<ApplicantDto> uploadCV(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        return ResponseEntity.ok().body(applicantService.uploadCv(id, file).get());
    }

    @Operation(summary = "Скачивание резюме кандидата",
            description = "Позволяет скачать резюме кандидата из сервиса")
    @GetMapping("/applicant/{id}/download_cv")
    public ResponseEntity<Resource> downloadCV(@PathVariable Long id, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = applicantService.downloadCv(id).get();
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}