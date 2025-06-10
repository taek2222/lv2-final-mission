package finalmission.service;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final DefaultMessageService messageService;

    public void sendTeamCreateMessage(String phoneNumber) {

        Message message1 = new Message();
        message1.setFrom(phoneNumber);
        message1.setTo("01012345678");
        message1.setText("내전 팀 등록이 성공적으로 완료되었습니다.");

        messageService.sendOne(new SingleMessageSendingRequest(message1));
    }
}
