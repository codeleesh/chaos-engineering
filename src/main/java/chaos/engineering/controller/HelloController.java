package chaos.engineering.controller;

import chaos.engineering.application.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService service;

    @GetMapping("index")
    public String hello() {
        return service.hello();
    }
}
