package by.krainet.matveenko.serviceforcv.controller;

import by.krainet.matveenko.serviceforcv.service.DirectionService;
import by.krainet.matveenko.serviceforcv.service.dto.DirectionDto;
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

@Tag(name="Direction Controller", description="Направления")
@Log4j2
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class DirectionController {
    private final DirectionService directionService;

    @Operation(summary = "Поиск направления по Id",
               description = "Позволяет найти необходимое направление по его идентификатору")
    @GetMapping("/direction/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DirectionDto> getDirectionById(@PathVariable Long id) {
        return ResponseEntity.ok().body(directionService.findById(id));
    }

    @Operation(summary = "Вывод всех направлений", description = "Позволяет вывести весь список направлений")
    @GetMapping("/directions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DirectionDto>> getDirection(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return ResponseEntity.ok().body(directionService.findDirectionsByNameContainingIgnoreCase(name, PageRequest.of(page, size)));
    }

    @Operation(summary = "Добавление направления", description = "Позволяет добавить напрвление в сервис")
    @PostMapping("/direction")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DirectionDto> createDirection(@RequestBody DirectionDto direction) throws URISyntaxException {
        DirectionDto result = directionService.saveDirection(direction);
        log.info("Direction created!");
        return ResponseEntity.created(new URI("/api/v1/directions/" + result.getId())).body(result);
    }

    @Operation(summary = "Редактирование направления", description = "Позволяет отредактировать направление в сервисе")
    @PutMapping("/direction/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DirectionDto> updateDirection(@PathVariable Long id, @RequestBody DirectionDto direction) {
        log.info("Direction edited!");
        return ResponseEntity.ok().body(directionService.saveDirection(direction));
    }
}