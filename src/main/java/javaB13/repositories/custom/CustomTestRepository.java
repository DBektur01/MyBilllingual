package javaB13.repositories.custom;

import  javaB13.dto.responses.test.TestResponse;

import java.util.List;
import java.util.Optional;

public interface CustomTestRepository {
    List<TestResponse> getAll();

    Optional<TestResponse> getById(Long id);
}
