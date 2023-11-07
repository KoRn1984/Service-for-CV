package by.krainet.matveenko.serviceforcv.controller;

import by.krainet.matveenko.serviceforcv.service.ApplicantTestService;
import by.krainet.matveenko.serviceforcv.service.dto.ApplicantTestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Tag(name="Test of Applicant Controller", description="Тесты кандидатов")
@Log4j2
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ApplicantTestController {
    private final ApplicantTestService applicantTestService;

    @Operation(summary = "Поиск теста кандидата по Id",
               description = "Позволяет найти тест определенного кандидата по его идентификатору")
    @GetMapping("/applicant_test/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApplicantTestDto> getApplicantTestById(@PathVariable Long id) {
        return ResponseEntity.ok().body(applicantTestService.findById(id));
    }

    @Operation(summary = "Вывод всех тестов кандидатов", description = "Позволяет вывести весь список тестов по кандидатам")
    @GetMapping("/applicant_tests")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ApplicantTestDto>> getApplicantTest(
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String nameTest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return ResponseEntity.ok().body(applicantTestService.findApplicantTestsByApplicantContainingIgnoreCase(surname, nameTest, PageRequest.of(page, size)));
    }

    @Operation(summary = "Добавление теста кандидата", description = "Позволяет добавить тест кандидата в сервис")
    @PostMapping("/applicant_test")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicantTestDto> createApplicantTest(@RequestBody ApplicantTestDto applicantTest) throws URISyntaxException {
        ApplicantTestDto result = applicantTestService.saveApplicantTest(applicantTest);
        log.info("Test of applicant created!");
        return ResponseEntity.created(new URI("/api/v1/applicant_tests/" + result.getId())).body(result);
    }

    @Operation(summary = "Редактирование теста кандидата", description = "Позволяет отредактировать тест кандидата в сервисе")
    @PutMapping("/applicant_test/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApplicantTestDto> updateApplicantTest(@PathVariable Long id, @RequestBody ApplicantTestDto applicantTest) {
        log.info("Test of applicant edited!");
        return ResponseEntity.ok().body(applicantTestService.saveApplicantTest(applicantTest));
    }
}