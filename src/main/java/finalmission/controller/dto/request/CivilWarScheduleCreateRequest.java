package finalmission.controller.dto.request;

import finalmission.domain.CivilWarSchedule;
import java.time.LocalDateTime;

public record CivilWarScheduleCreateRequest(
        LocalDateTime startSchedule,
        LocalDateTime endSchedule
) {

    public CivilWarSchedule toCivilWarSchedule() {
        return CivilWarSchedule.builder()
                .startSchedule(startSchedule)
                .endSchedule(endSchedule)
                .build();
    }
}
