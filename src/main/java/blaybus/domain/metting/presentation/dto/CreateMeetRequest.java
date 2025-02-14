package blaybus.domain.metting.presentation.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 미팅 생성 요청 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMeetRequest {
    // 예: "주간 회의" 등의 미팅 제목
    private String title;

    // 미팅 설명
    private String desc;

    // 시작 일시 (ISO8601 문자열 등, 필요에 따라 LocalDateTime 등으로 변경)
    private String startDateTime;

    // 종료 일시 (ISO8601 문자열 등, 필요에 따라 LocalDateTime 등으로 변경)
    private String endDateTime;
}
