package finalmission.controller.dto.request;

import finalmission.domain.Crew;
import finalmission.domain.Line;
import finalmission.domain.Tier;

public record CrewCreateRequest(
        String name,
        String tier,
        String line
) {

    public Crew toCrew() {
        return Crew.builder()
                .name(name)
                .tier(Tier.valueOf(tier))
                .line(Line.valueOf(line))
                .build();
    }
}
