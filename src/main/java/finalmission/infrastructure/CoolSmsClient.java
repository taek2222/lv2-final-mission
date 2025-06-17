package finalmission.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import finalmission.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class CoolSmsClient {

    private final RestClient smsRestClient;
    private final ObjectMapper objectMapper;

    public void sendReservationSms(Reservation reservation) throws JsonProcessingException {
        String message = createSmsMessage(reservation);
        CoolSmsRequest request = new CoolSmsRequest(reservation.getMember().getPhoneNumber(), message);

        smsRestClient.post()
                .uri("/detail")
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectMapper.writeValueAsString(request))
                .retrieve()
                .toBodilessEntity();
    }

    private String createSmsMessage(Reservation reservation) {
        return String.format("""
                        📢 %s님 회의실 예약 정보 안내
                        
                        예약 정보
                        - 예약 회의실 이름 : %s
                        - 회의 날짜 : %s
                        - 회의 시작 시간 : %s
                        - 회의 마감 시간 : %s
                        """,
                reservation.getMember().getNickname(),
                reservation.getRoom().getName(),
                reservation.getDate(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );
    }
}
