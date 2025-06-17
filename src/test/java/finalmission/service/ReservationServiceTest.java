package finalmission.service;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import com.fasterxml.jackson.core.JsonProcessingException;
import finalmission.controller.dto.ReservationRequest;
import finalmission.controller.dto.ReservationUpdateRequest;
import finalmission.domain.Member;
import finalmission.domain.Reservation;
import finalmission.domain.Room;
import finalmission.infrastructure.MailClient;
import finalmission.infrastructure.sms.CoolSmsClient;
import finalmission.repository.MemberRepository;
import finalmission.repository.ReservationRepository;
import finalmission.repository.RoomRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoomRepository roomRepository;

    @MockitoBean
    private MailClient mailClient;

    @MockitoBean
    private CoolSmsClient coolSmsClient;

    @Test
    void 같은날_3개_이상_회의실을_예약할_경우_예외가_발생한다() throws JsonProcessingException {
        // given
        Member member = generateMember();
        Room room = generateRoom();
        LocalDate date = LocalDate.of(2025, 4, 23);

        doNothing().when(mailClient)
                .sendReservationMail(any(Reservation.class));

        doNothing().when(coolSmsClient)
                .sendReservationSms(any(Reservation.class));

        reservationRepository.save(new Reservation(member, room, date, LocalTime.of(16, 0), LocalTime.of(17, 0)));
        reservationRepository.save(new Reservation(member, room, date, LocalTime.of(17, 0), LocalTime.of(18, 0)));

        ReservationRequest request = new ReservationRequest(room.getId(), date, LocalTime.of(14, 0),
                LocalTime.of(15, 0));

        // when && then
        assertThatCode(() -> reservationService.registerReservation(member, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 날 3개 이상 회의실을 예약할 수 없습니다.");
    }

    @Test
    void 수정_희망_날짜에_2개_이상의_예약이_존재할_경우_예외가_발생한다() throws JsonProcessingException {
        // given
        Member member = generateMember();
        Room room = generateRoom();
        LocalDate date = LocalDate.of(2025, 4, 23);

        doNothing().when(mailClient)
                .sendReservationMail(any(Reservation.class));

        doNothing().when(coolSmsClient)
                .sendReservationSms(any(Reservation.class));

        reservationRepository.save(new Reservation(member, room, date, LocalTime.of(16, 0), LocalTime.of(17, 0)));
        reservationRepository.save(new Reservation(member, room, date, LocalTime.of(17, 0), LocalTime.of(18, 0)));
        Reservation updateReservation = reservationRepository.save(
                new Reservation(member, room, LocalDate.of(2025, 4, 24), LocalTime.of(18, 0), LocalTime.of(19, 0)));

        ReservationUpdateRequest updateRequest = new ReservationUpdateRequest(room.getId(), date,
                LocalTime.of(18, 0), LocalTime.of(19, 0));

        // when && then
        assertThatCode(() -> reservationService.updateReservation(updateReservation.getId(), updateRequest, member))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 날 3개 이상 회의실을 예약할 수 없습니다.");
    }

    private Room generateRoom() {
        Room room = new Room("테스트방");
        roomRepository.save(room);
        return room;
    }

    private Member generateMember() {
        Member member = new Member("nickname", "email@email.com", "password", "01012345456");
        memberRepository.save(member);
        return member;
    }
}
