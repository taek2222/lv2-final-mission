package finalmission.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ReservationTest {

    @ParameterizedTest
    @CsvSource({
            "12:15, 12:30",
            "12:00, 12:43"
    })
    void 예약_시간이_10분_단위가_아닌_경우_예외가_발생한다(LocalTime startTime, LocalTime endTime) {
        // given
        Member member = new Member();
        Room room = new Room();
        LocalDate date = LocalDate.MAX;

        // when && then
        assertThatCode(() -> new Reservation(member, room, date, startTime, endTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("예약 시간은 10분 단위만 가능합니다.");
    }
}
