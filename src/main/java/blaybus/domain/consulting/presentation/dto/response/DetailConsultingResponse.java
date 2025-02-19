package blaybus.domain.consulting.presentation.dto.response;

import java.time.LocalDateTime;

public record DetailConsultingResponse(
        Long consultingId,
        String name,
        String email,
        String designerId,
        String designerName,
        String designerProfile,
        String pay,
        String type,    // 대면, 비대면
        String status,  // 4가지 상태
        String meetLink,
        LocalDateTime startTime, // 예약시간

        String money
) {
    public static DetailConsultingResponse of(Long consultingId, String name, String email, String designerId, String designerName, String designerProfile,
                                              String pay, String type, String status,
                                              String meetLink, LocalDateTime startTime, String money) {
        return new DetailConsultingResponse(consultingId, name, email, designerId, designerName, designerProfile, pay, type, status, meetLink, startTime, money);
    }
}
