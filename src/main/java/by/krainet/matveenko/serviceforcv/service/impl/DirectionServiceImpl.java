package by.krainet.matveenko.serviceforcv.service.impl;

import by.krainet.matveenko.serviceforcv.models.Direction;
import by.krainet.matveenko.serviceforcv.repository.DirectionRepository;
import by.krainet.matveenko.serviceforcv.service.DirectionService;
import by.krainet.matveenko.serviceforcv.service.convertor.DirectionMapper;
import by.krainet.matveenko.serviceforcv.service.dto.DirectionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class DirectionServiceImpl implements DirectionService {
    private final DirectionRepository directionRepository;
    private final DirectionMapper directionMapper;

    @Override
    public DirectionDto findById(Long id) {
        return Optional.of(getById(id)).map(directionMapper::modelToDto).get();
    }

    private Direction getById(Long id) {
        return directionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Direction with id: " + id + " not found!"));
    }

    @Override
    public List<DirectionDto> findDirectionsByNameContainingIgnoreCase(String name, Pageable paging) {
        Page<Direction> page = name == null
                ? directionRepository.findAll(paging)
                : directionRepository.findDirectionsByNameContainingIgnoreCase(name, paging);
        return directionMapper.toListDto(page.getContent());
    }

    @Override
    public DirectionDto saveDirection(DirectionDto direction) {
        return directionMapper.modelToDto(directionRepository.save(directionMapper.dtoToModel(direction)));
    }
}