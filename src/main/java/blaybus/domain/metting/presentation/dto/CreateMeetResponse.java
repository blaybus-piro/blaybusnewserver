package blaybus.domain.metting.presentation.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 미팅 생성 응답 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMeetResponse {
    // 생성된 Google Meet 링크
    private String meetLink;
}
