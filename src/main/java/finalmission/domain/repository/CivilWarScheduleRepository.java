package finalmission.domain.repository;

import finalmission.domain.CivilWarSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CivilWarScheduleRepository extends JpaRepository<CivilWarSchedule, Long> {
}
