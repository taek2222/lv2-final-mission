package finalmission.controller;

import finalmission.domain.Member;

public record MemberResponse(
        Long id,
        String nickname,
        String email
) {
    public MemberResponse(Member member) {
        this(
                member.getId(),
                member.getNickname(),
                member.getEmail()
        );
    }
}
