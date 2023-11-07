package by.krainet.matveenko.serviceforcv.service.convertor;

import by.krainet.matveenko.serviceforcv.models.ApplicantTest;
import by.krainet.matveenko.serviceforcv.service.dto.ApplicantTestDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicantTestMapper {
    ApplicantTest dtoToModel(ApplicantTestDto applicantTestDto);

    ApplicantTestDto modelToDto(ApplicantTest applicantTest);

    List<ApplicantTestDto> toListDto(List<ApplicantTest> applicantTests);
}