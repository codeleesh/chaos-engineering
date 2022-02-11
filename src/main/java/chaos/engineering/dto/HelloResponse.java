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
public class HelloResponse {
    private Long id;
    private String name;

    public static HelloResponse of(final Long id, final String name) {
        return new HelloResponse(id, name);
    }

    public static HelloResponse from(final Hello saveHello) {
        return HelloResponse.of(saveHello.getId(), saveHello.getName());
    }
}
