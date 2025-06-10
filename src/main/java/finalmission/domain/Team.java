package finalmission.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "leader_id")
    private Crew leader;

    private String phoneNumber;

    @OneToMany
    @JoinColumn
    private List<Crew> crews;

    private Tier averageTier;

    @OneToMany
    @JoinColumn
    private List<CivilWarSchedule> possibleSchedule;

    public String getAverageTier() {
        return averageTier.getName();
    }
}
