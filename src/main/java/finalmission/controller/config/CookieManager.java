package finalmission.controller.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import org.springframework.stereotype.Component;

@Component
public class CookieManager {

    public void setTokenCookie(HttpServletResponse response, String token) {
        Cookie cookie = generateCookie(token);
        response.addCookie(cookie);
    }

    public Cookie generateCookie(String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    public String extractToken(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("token"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new RuntimeException("다시 로그인해 주세요.")); // todo : 에러 메시지 타입
    }
}
