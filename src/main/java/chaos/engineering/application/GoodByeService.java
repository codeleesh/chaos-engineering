package chaos.engineering.application;

import chaos.engineering.domain.GoodBye;
import chaos.engineering.domain.GoodByeRepository;
import chaos.engineering.dto.GoodByeRequest;
import chaos.engineering.dto.GoodByeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodByeService {
    private final GoodByeRepository repository;

    public GoodByeResponse goodBye(final GoodByeRequest request) {
        final GoodBye saveGoodBye = repository.save(request.toEntity());
        return GoodByeResponse.from(saveGoodBye);
    }
}
