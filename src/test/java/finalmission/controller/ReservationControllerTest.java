package finalmission.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import finalmission.controller.dto.ReservationRequest;
import finalmission.domain.Reservation;
import finalmission.infrastructure.MailClient;
import jakarta.servlet.http.Cookie;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest extends BaseCookie {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MailClient mailClient;

    @Test
    void 모든_예약_현황을_반환한다() throws Exception {
        // given
        Cookie cookie = createFixtureCookie();

        // when && then
        mockMvc.perform(get("/reservations")
                        .cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservations[0].id").value(1))
                .andExpect(jsonPath("$.reservations[0].room").value("어드레스룸"))
                .andExpect(jsonPath("$.reservations[0].date").value("2025-06-15"))
                .andExpect(jsonPath("$.reservations[0].startTime").value("19:00"))
                .andExpect(jsonPath("$.reservations[0].endTime").value("20:00"))
                .andExpect(jsonPath("$.reservations[1].id").value(2))
                .andExpect(jsonPath("$.reservations[1].room").value("백스윙룸"))
                .andExpect(jsonPath("$.reservations[1].date").value("2025-06-16"))
                .andExpect(jsonPath("$.reservations[1].startTime").value("20:00"))
                .andExpect(jsonPath("$.reservations[1].endTime").value("21:00"))
        ;
    }

    @Test
    void 예약_상세보기_정보를_반환한다() throws Exception {
        // given
        Cookie cookie = createFixtureCookie();
        long reservationId = 1L;

        // when && then
        mockMvc.perform(get("/reservations/" + reservationId)
                        .cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nickname").value("테스트1"))
                .andExpect(jsonPath("$.room.id").value(1))
                .andExpect(jsonPath("$.room.name").value("어드레스룸"))
                .andExpect(jsonPath("$.date").value("2025-06-15"))
                .andExpect(jsonPath("$.startTime").value("19:00"))
                .andExpect(jsonPath("$.endTime").value("20:00"))
        ;
    }

    @Test
    void 사용자가_아닌_경우_예약_상세_죄회시_예외가_발생한다() throws Exception {
        // given
        Cookie cookie = createFixtureCookie();
        long reservationId = 3L;

        // when && then
        mockMvc.perform(get("/reservations/" + reservationId)
                        .cookie(cookie))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("다른 사용자 예약을 상세 열람할 수 없습니다."));
    }

    @Test
    void 사용자가_아닌_경우_예약_죄회시_예외가_발생한다() throws Exception {
        // when && then
        mockMvc.perform(get("/reservations"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void 성공적으로_회의실을_예약한_후_정보를_반환한다() throws Exception {
        // given
        Cookie cookie = createFixtureCookie();

        Long roomId = 1L;
        LocalDate date = LocalDate.of(2025, 6, 15);
        LocalTime startTime = LocalTime.of(18, 10);
        LocalTime endTime = LocalTime.of(21, 10);

        ReservationRequest request = new ReservationRequest(roomId, date, startTime, endTime);

        doNothing().when(mailClient)
                .sendReservationMail(any(Reservation.class));

        // when && then
        mockMvc.perform(post("/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .cookie(cookie))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.room").value("어드레스룸"))
                .andExpect(jsonPath("$.date").value("2025-06-15"))
                .andExpect(jsonPath("$.startTime").value("18:10"))
                .andExpect(jsonPath("$.endTime").value("21:10"));
    }
}
