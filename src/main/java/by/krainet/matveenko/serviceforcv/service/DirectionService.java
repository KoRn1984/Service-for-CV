package by.krainet.matveenko.serviceforcv.service;

import by.krainet.matveenko.serviceforcv.service.dto.DirectionDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DirectionService {

    DirectionDto findById(Long id);

    List<DirectionDto> findDirectionsByNameContainingIgnoreCase(String name, Pageable paging);

    DirectionDto saveDirection(DirectionDto direction);
}