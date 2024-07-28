package javaB13.services;
import javaB13.dto.requests.test.PassTestRequest;
import javaB13.dto.requests.test.TestRequest;
import javaB13.dto.responses.SimpleResponse;
import javaB13.dto.responses.test.TestResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {
    SimpleResponse save(TestRequest request);

    SimpleResponse deleteById(Long testId);

    TestResponse getById(Long testId);

    List<TestResponse> getAll();

    SimpleResponse update(Long id, TestRequest request);

    SimpleResponse submitTest(PassTestRequest request, Authentication authentication);
}
