package by.krainet.matveenko.serviceforcv.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Schema(description = "Сущность теста кандидата")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicantTestDto {
    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @Schema(description = "Кандидат")
    private ApplicantDto applicant;
    @Schema(description = "Тест")
    private TestDto test;
    @Builder.Default
    @Schema(description = "История результатов сдачи тестов (дата, оценка)")
    private Map<LocalDate, String> history;
}
