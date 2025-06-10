package finalmission.controller.dto.response;

import finalmission.domain.CivilWarSchedule;
import finalmission.domain.Crew;
import finalmission.domain.Team;
import java.util.List;

public record TeamInfoResponse(
        Long id,
        String name,
        CrewInfoResponse leader,
        String phoneNumber,
        List<CrewInfoResponse> crews,
        String averageTier,
        List<CivilWarScheduleInfoResponse> possibleSchedule
) {

    public TeamInfoResponse(Team team) {
        this(
                team.getId(),
                team.getName(),
                new CrewInfoResponse(team.getLeader()),
                team.getPhoneNumber(),
                buildCrewInfoResponses(team.getCrews()),
                team.getAverageTier(),
                buildCivilWarScheduleInfoResponse(team.getPossibleSchedule())
        );
    }

    private static List<CrewInfoResponse> buildCrewInfoResponses(List<Crew> crews) {
        return crews.stream()
                .map(CrewInfoResponse::new)
                .toList();
    }

    private static List<CivilWarScheduleInfoResponse> buildCivilWarScheduleInfoResponse(
            List<CivilWarSchedule> civilWarSchedules) {
        return civilWarSchedules.stream()
                .map(CivilWarScheduleInfoResponse::new)
                .toList();
    }
}
