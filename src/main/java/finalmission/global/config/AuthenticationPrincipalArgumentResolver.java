package finalmission.global.config;

import finalmission.controller.config.CookieManager;
import finalmission.domain.Member;
import finalmission.infrastructure.JwtTokenProvider;
import finalmission.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final CookieManager cookieManager;
    private final JwtTokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isAuthenticationAnnotation = parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
        boolean isMemberParameter = Member.class.isAssignableFrom(parameter.getParameterType());
        return isAuthenticationAnnotation && isMemberParameter;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        String token = cookieManager.extractToken(request);
        Long memberId = tokenProvider.extractMemberId(token);

        // todo : 에러 메시지 타입
        return memberRepository.findById(memberId)
                .orElseThrow(RuntimeException::new);
    }
}
