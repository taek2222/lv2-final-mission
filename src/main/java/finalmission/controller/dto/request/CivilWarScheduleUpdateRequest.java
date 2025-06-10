package finalmission.controller.dto.request;

import java.time.LocalDateTime;

public record CivilWarScheduleUpdateRequest(
        Long id,
        LocalDateTime startSchedule,
        LocalDateTime endSchedule
) {

}
