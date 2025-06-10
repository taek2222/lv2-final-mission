package finalmission.controller.dto.response;

import finalmission.domain.CivilWarSchedule;
import java.time.LocalDateTime;

public record CivilWarScheduleInfoResponse(
        Long id,
        LocalDateTime start,
        LocalDateTime end
) {

    public CivilWarScheduleInfoResponse(CivilWarSchedule civilWarDateTime) {
        this(
                civilWarDateTime.getId(),
                civilWarDateTime.getStartSchedule(),
                civilWarDateTime.getEndSchedule()
        );
    }
}
