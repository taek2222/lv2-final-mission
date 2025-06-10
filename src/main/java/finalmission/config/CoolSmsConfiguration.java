package finalmission.config;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoolSmsConfiguration {

    @Bean
    public DefaultMessageService messageService() {
        return NurigoApp.INSTANCE.initialize("NCSUJFPFAASRFHON", "DNSBI5UOKYLUKWFRBYSVHRIKMUWDSXOT",
                "https://api.coolsms.co.kr");
    }
}
