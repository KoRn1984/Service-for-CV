package by.krainet.matveenko.serviceforcv.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Сущность направления")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectionDto {
    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @Schema(description = "Название направления")
    private String name;
    @Schema(description = "Описание направления")
    private String level;
}