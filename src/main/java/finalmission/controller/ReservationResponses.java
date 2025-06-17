package finalmission.controller;

import finalmission.controller.dto.ReservationResponse;
import java.util.List;

public record ReservationResponses(
        List<ReservationResponse> reservations
) {
}
