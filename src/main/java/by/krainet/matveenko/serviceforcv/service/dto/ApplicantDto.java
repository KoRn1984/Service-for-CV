package by.krainet.matveenko.serviceforcv.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Schema(description = "Сущность кандидата")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicantDto {
    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @Schema(description = "Фамилия", example = "Иванов")
    private String surname;
    @Schema(description = "Имя", example = "Иван")
    private String name;
    @Schema(description = "Отчество", example = "Иванович")
    private String patronymic;
    @Schema(description = "Фотография")
    private String photo;
    @Schema(description = "Описание", example = "Senior Java Developer")
    private String level;
    @Schema(description = "Резюме")
    private String cv;
    @Builder.Default
    @Schema(description = "Список возможных направлений")
    private List<DirectionDto> directions;
}
