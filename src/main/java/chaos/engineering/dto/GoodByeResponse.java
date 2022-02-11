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
public class GoodByeResponse {
    private Long id;
    private String name;

    public static GoodByeResponse of(final Long id, final String name) {
        return new GoodByeResponse(id, name);
    }

    public static GoodByeResponse from(final GoodBye saveGoodBye) {
        return GoodByeResponse.of(saveGoodBye.getId(), saveGoodBye.getName());
    }
}
