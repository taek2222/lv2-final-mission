package finalmission.controller.dto.request;

import java.util.List;

public record TeamCreateRequest(
        String name,
        Long leaderId,
        String phoneNumber,
        List<Long> crewIds,
        List<Long> possibleDateTimeIds
) {
}
