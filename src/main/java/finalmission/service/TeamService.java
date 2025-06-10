package finalmission.service;

import finalmission.controller.dto.request.TeamCreateRequest;
import finalmission.controller.dto.response.TeamDetailResponse;
import finalmission.domain.CivilWarSchedule;
import finalmission.domain.Crew;
import finalmission.domain.Team;
import finalmission.domain.Tier;
import finalmission.domain.repository.CivilWarScheduleRepository;
import finalmission.domain.repository.CrewRepository;
import finalmission.domain.repository.TeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final CivilWarScheduleRepository civilWarScheduleRepository;

    private final CrewRepository crewRepository;
    private final TeamRepository teamRepository;
    private final SmsService smsService;

    public List<TeamDetailResponse> getAllTeam() {
        List<Team> teams = teamRepository.findAll();
    }

    public TeamDetailResponse saveTeam(TeamCreateRequest request) {
        // 리더 탐색
        Crew leader = getCrewById(request.leaderId());

        // 팀원 탐색
        List<Crew> crews = request.crewIds().stream()
                .map(this::getCrewById)
                .toList();

        // 평균 티어 계산
        int totalWeight = crews.stream()
                .mapToInt(Crew::getTierWeight)
                .sum() + leader.getTierWeight();

        int averageTierWeight = totalWeight / 5;
        Tier averageTier = Tier.findByWeight(averageTierWeight);

        // 가능한 스케줄
        List<CivilWarSchedule> possibleSchedule = request.possibleDateTimeIds().stream()
                .map(this::getCivilWarScheduleById)
                .toList();

        Team saveTeam = Team.builder()
                .name(request.name())
                .leader(leader)
                .phoneNumber(request.phoneNumber())
                .crews(crews)
                .averageTier(averageTier)
                .possibleSchedule(possibleSchedule)
                .build();

        teamRepository.save(saveTeam);
        smsService.sendTeamCreateMessage(saveTeam.getPhoneNumber());
        return new TeamDetailResponse(saveTeam);
    }

    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }

    private Crew getCrewById(Long crewId) {
        return crewRepository.findById(crewId)
                .orElseThrow(RuntimeException::new);
    }

    private CivilWarSchedule getCivilWarScheduleById(Long civilWarScheduleId) {
        return civilWarScheduleRepository.findById(civilWarScheduleId)
                .orElseThrow(RuntimeException::new);
    }
}
