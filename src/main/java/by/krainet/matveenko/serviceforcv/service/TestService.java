package by.krainet.matveenko.serviceforcv.service;

import by.krainet.matveenko.serviceforcv.service.dto.TestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestService {
    TestDto findById(Long id);

    List<TestDto> findTestsByNameContainingIgnoreCase(String name, Pageable paging);

    TestDto saveTest(TestDto test);
}
