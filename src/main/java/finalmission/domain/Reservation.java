package finalmission.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    public Reservation(Member member, Room room, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.member = member;
        this.room = room;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void update(Room room, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.room = room;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime(DateTimeFormatter formatter) {
        return startTime.format(formatter);
    }

    public String getEndTime(DateTimeFormatter formatter) {
        return endTime.format(formatter);
    }
}
