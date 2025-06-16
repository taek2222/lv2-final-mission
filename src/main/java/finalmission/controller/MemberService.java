package finalmission.controller;

import finalmission.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse signupMember(MemberSignupRequest request) {
        Member member = request.toMember();
        memberRepository.save(member);
        return new MemberResponse(member);
    }
}
