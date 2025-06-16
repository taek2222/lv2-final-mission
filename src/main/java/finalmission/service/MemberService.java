package finalmission.service;

import finalmission.controller.dto.MemberLoginRequest;
import finalmission.controller.dto.MemberResponse;
import finalmission.controller.dto.MemberSignupRequest;
import finalmission.domain.Member;
import finalmission.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse signup(MemberSignupRequest request) {
        Member member = request.toMember();
        memberRepository.save(member);
        return new MemberResponse(member);
    }

    public Member validateLoginAndReturnMember(MemberLoginRequest request) {
        Member member = getMemberByEmail(request.email());
        validatePassword(request, member);
        return member;
    }

    private Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일 및 비밀번호가 일치하지 않습니다."));
    }

    private void validatePassword(MemberLoginRequest request, Member member) {
        if (!member.isSamePassword(request.password())) {
            throw new IllegalArgumentException("이메일 및 비밀번호가 일치하지 않습니다.");
        }
    }
}
