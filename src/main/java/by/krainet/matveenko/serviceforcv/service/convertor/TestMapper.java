package by.krainet.matveenko.serviceforcv.service.convertor;

import by.krainet.matveenko.serviceforcv.models.Test;
import by.krainet.matveenko.serviceforcv.service.dto.TestDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestMapper {
    Test dtoToModel(TestDto testDto);

    TestDto modelToDto(Test test);

    List<TestDto> toListDto(List<Test> tests);
}