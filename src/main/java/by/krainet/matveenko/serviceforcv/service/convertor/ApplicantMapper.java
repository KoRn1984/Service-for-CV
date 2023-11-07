package by.krainet.matveenko.serviceforcv.service.convertor;

import by.krainet.matveenko.serviceforcv.models.Applicant;
import by.krainet.matveenko.serviceforcv.service.dto.ApplicantDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicantMapper {
    Applicant dtoToModel(ApplicantDto applicantDto);

    ApplicantDto modelToDto(Applicant applicant);

    List<ApplicantDto> toListDto(List<Applicant> applicants);
}