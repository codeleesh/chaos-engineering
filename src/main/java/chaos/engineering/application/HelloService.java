package chaos.engineering.application;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String hello() {
        return "Hello";
    }
}
