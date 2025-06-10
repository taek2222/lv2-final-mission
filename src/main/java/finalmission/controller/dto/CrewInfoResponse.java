package finalmission.controller.dto;

import finalmission.domain.Crew;

public record CrewInfoResponse(
        Long id,
        String name,
        String tier,
        String line
) {

    public CrewInfoResponse(Crew crew) {
        this(crew.getId(), crew.getName(), crew.getTier(), crew.getLine());
    }
}
