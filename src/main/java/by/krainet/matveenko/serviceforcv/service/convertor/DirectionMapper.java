package by.krainet.matveenko.serviceforcv.service.convertor;

import by.krainet.matveenko.serviceforcv.models.Direction;
import by.krainet.matveenko.serviceforcv.service.dto.DirectionDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectionMapper {
    Direction dtoToModel(DirectionDto directionDto);

    DirectionDto modelToDto(Direction direction);

    List<DirectionDto> toListDto(List<Direction> directions);

    default List<Direction> toListModel(List<DirectionDto> directionDto) {
        if (directionDto == null) {
            return null;
        }
        List<Direction> list = new ArrayList<>();
        for (DirectionDto directionDtos : directionDto) {
            list.add(dtoToModel(directionDtos));
        }
        return list;
    }
}