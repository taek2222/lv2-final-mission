package finalmission.controller;

import finalmission.controller.dto.ReservationRequest;
import finalmission.domain.Member;
import finalmission.domain.Reservation;
import finalmission.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationResponse registerReservation(Member member, ReservationRequest request) {
        Room room = getRoomByRoomId(request);
        Reservation reservation = request.toReservation(member, room);
        reservationRepository.save(reservation);
        return new ReservationResponse(reservation);
    }

    private Room getRoomByRoomId(ReservationRequest request) {
        return roomRepository.findById(request.roomId())
                .orElseThrow(() -> new RuntimeException("서버의 문제가 발생했습니다."));// todo : 에러 커스텀 적용
    }
}
