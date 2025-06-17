package finalmission.service;

import finalmission.controller.ReservationResponses;
import finalmission.controller.dto.ReservationRequest;
import finalmission.controller.dto.ReservationResponse;
import finalmission.domain.Member;
import finalmission.domain.Reservation;
import finalmission.domain.Room;
import finalmission.infrastructure.MailClient;
import finalmission.repository.ReservationRepository;
import finalmission.repository.RoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final MailClient mailClient;

    public ReservationResponses getAllReservation() {
        List<ReservationResponse> responses = reservationRepository.findAll().stream()
                .map(ReservationResponse::new)
                .toList();
        return new ReservationResponses(responses);
    }

    public ReservationResponse registerReservation(Member member, ReservationRequest request) {
        Room room = getRoomByRoomId(request);
        Reservation reservation = request.toReservation(member, room);
        reservationRepository.save(reservation);

        mailClient.sendReservationMail(reservation);
        return new ReservationResponse(reservation);
    }

    private Room getRoomByRoomId(ReservationRequest request) {
        return roomRepository.findById(request.roomId())
                .orElseThrow(() -> new RuntimeException("서버의 문제가 발생했습니다."));// todo : 에러 커스텀 적용
    }
}
