package by.krainet.matveenko.serviceforcv.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Сущность теста")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDto {
    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @Schema(description = "Название теста")
    private String name;
    @Schema(description = "Описание теста")
    private String description;
    @Schema(description = "Список применимых направлений")
    private List<DirectionDto> directions;
}