package chaos.engineering.controller;

import chaos.engineering.application.GoodByeService;
import chaos.engineering.dto.GoodByeRequest;
import chaos.engineering.dto.GoodByeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GoodByeController {

    private final GoodByeService service;

    @PostMapping("goodbye")
    public ResponseEntity<GoodByeResponse> goodBye(@RequestBody final GoodByeRequest request) {
        final GoodByeResponse hello = service.goodBye(request);
        return ResponseEntity.ok(hello);
    }
}
