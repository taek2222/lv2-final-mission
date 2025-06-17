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
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].room").value("어드레스룸"))
                .andExpect(jsonPath("$[0].date").value("2025-06-15"))
                .andExpect(jsonPath("$[0].startTime").value("19:00"))
                .andExpect(jsonPath("$[0].endTime").value("20:00"))
                .andExpect(jsonPath("$[1].id").value(1))
                .andExpect(jsonPath("$[1].room").value("백스윙룸"))
                .andExpect(jsonPath("$[1].date").value("2025-06-16"))
                .andExpect(jsonPath("$[1].startTime").value("20:00"))
                .andExpect(jsonPath("$[1].endTime").value("21:00"))
        ;
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
