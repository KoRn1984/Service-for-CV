package by.krainet.matveenko.serviceforcv.service.impl;

import by.krainet.matveenko.serviceforcv.models.Direction;
import by.krainet.matveenko.serviceforcv.models.Test;
import by.krainet.matveenko.serviceforcv.repository.TestRepository;
import by.krainet.matveenko.serviceforcv.service.TestService;
import by.krainet.matveenko.serviceforcv.service.convertor.DirectionMapper;
import by.krainet.matveenko.serviceforcv.service.convertor.TestMapper;
import by.krainet.matveenko.serviceforcv.service.dto.TestDto;
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
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;
    private final DirectionMapper directionMapper;


    @Override
    public TestDto findById(Long id) {
        return Optional.of(getById(id)).map(testMapper::modelToDto).get();
    }

    private Test getById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Test with id: " + id + " not found!"));
    }

    @Override
    public List<TestDto> findTestsByNameContainingIgnoreCase(String name, Pageable paging) {
        Page<Test> page = name == null
                ? testRepository.findAll(paging)
                : testRepository.findTestsByNameContainingIgnoreCase(name, paging);
        return testMapper.toListDto(page.getContent());
    }

    @Override
    public TestDto saveTest(TestDto test) {
        List<Direction> directionMapperListModel = directionMapper.toListModel(test.getDirections());
        Test testModel = testMapper.dtoToModel(test);
        testModel.setDirections(directionMapperListModel);
        return testMapper.modelToDto(testRepository.save(testModel));
    }
}