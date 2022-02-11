package chaos.engineering.dto;

import chaos.engineering.domain.GoodBye;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class GoodByeRequest {
    private String name;

    public static GoodByeRequest from(final String name) {
        return new GoodByeRequest(name);
    }

    public GoodBye toEntity() {
        return GoodBye.from(name);
    }
}
