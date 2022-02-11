package chaos.engineering.dto;

import chaos.engineering.domain.Hello;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class HelloRequest {
    private String name;

    public static HelloRequest from(final String name) {
        return new HelloRequest(name);
    }

    public Hello toEntity() {
        return Hello.from(name);
    }
}
