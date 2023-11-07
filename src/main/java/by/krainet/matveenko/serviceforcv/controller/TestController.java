package by.krainet.matveenko.serviceforcv.controller;

import by.krainet.matveenko.serviceforcv.service.TestService;
import by.krainet.matveenko.serviceforcv.service.dto.TestDto;
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

@Tag(name="Test Controller", description="Тесты")
@Log4j2
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @Operation(summary = "Поиск теста по Id", description = "Позволяет найти тест по его идентификатору")
    @GetMapping("/test/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TestDto> getTestById(@PathVariable Long id) {
        return ResponseEntity.ok().body(testService.findById(id));
    }

    @Operation(summary = "Вывод всех тестов", description = "Позволяет вывести весь список тестов")
    @GetMapping("/tests")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TestDto>> getTest(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return ResponseEntity.ok().body(testService.findTestsByNameContainingIgnoreCase(name, PageRequest.of(page, size)));
    }

    @Operation(summary = "Добавление теста", description = "Позволяет добавить тест в сервис")
    @PostMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TestDto> createTest(@RequestBody TestDto test) throws URISyntaxException {
        TestDto result = testService.saveTest(test);
        log.info("Test created!");
        return ResponseEntity.created(new URI("/api/v1/tests/" + result.getId())).body(result);
    }

    @Operation(summary = "Редактирование теста", description = "Позволяет отредактировать тест в сервисе")
    @PutMapping("/test/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TestDto> updateTest(@PathVariable Long id, @RequestBody TestDto test) {
        log.info("Test edited!");
        return ResponseEntity.ok().body(testService.saveTest(test));
    }
}