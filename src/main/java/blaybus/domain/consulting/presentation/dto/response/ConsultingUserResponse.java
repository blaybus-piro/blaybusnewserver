package blaybus.domain.consulting.presentation.dto.response;

import java.time.LocalDateTime;

public record ConsultingUserResponse (
        Long consultingId,

        String designerId,
        String designerName,
        String designerProfile,
        String pay,
        String type,    // 대면, 비대면
        String status,  // 4가지 상태
        String meetLink,
        LocalDateTime startTime // 예약시간
) {
    public static ConsultingUserResponse of(Long consultingId,String designerId, String designerName, String designerProfile, String pay, String type, String status, String meetLink, LocalDateTime startTime) {
        return new ConsultingUserResponse(consultingId,designerId ,designerName, designerProfile, pay, type, status, meetLink, startTime);
    }
}
