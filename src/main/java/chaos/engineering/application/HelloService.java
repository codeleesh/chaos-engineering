package chaos.engineering.application;

import chaos.engineering.domain.Hello;
import chaos.engineering.domain.HelloRepository;
import chaos.engineering.dto.HelloRequest;
import chaos.engineering.dto.HelloResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final HelloRepository repository;

    public HelloResponse hello(final HelloRequest request) {
        final Hello saveHello = repository.save(request.toEntity());
        return HelloResponse.from(saveHello);
    }
}
