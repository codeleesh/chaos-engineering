package chaos.engineering.controller;

import chaos.engineering.application.HelloService;
import chaos.engineering.dto.HelloRequest;
import chaos.engineering.dto.HelloResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService service;

    @PostMapping("hello")
    public ResponseEntity<HelloResponse> hello(@RequestBody final HelloRequest request) {
        final HelloResponse hello = service.hello(request);
        return ResponseEntity.ok(hello);
    }
}
