package blaybus.domain.designer.presentation.dto.Request;

import blaybus.domain.designer.domain.entity.Area;
import blaybus.domain.designer.domain.entity.ExpertField;
import blaybus.domain.designer.domain.entity.Type;
import jakarta.validation.constraints.NotNull;

public record DesignerRequestDTO(
        @NotNull String id,                 // 디자이너 ID
        @NotNull String name,               // 디자이너 이름
        @NotNull String profile,            // 프로필 이미지
        @NotNull Area area,                 // 활동 지역
        @NotNull ExpertField expertField,   // 전문 분야
        @NotNull String introduce,          // 자기소개
        @NotNull String portfolio,          // 포트폴리오 URL
        @NotNull Type type,         // 상담 유형 (ONLINE, OFFLINE, BOTH)
        @NotNull int offlinePrice,          // 오프라인 가격
        @NotNull int onlinePrice            // 온라인 가격
) {}