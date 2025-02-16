package blaybus.domain.designer.application.service;

import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.designer.domain.repository.DesignerRepository;
import blaybus.domain.designer.presentation.dto.DesignerDTO;
import blaybus.domain.consulting.domain.entity.Consulting.ConsultingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DesignerService {

    private final DesignerRepository designerRepository;

    // **디자이너 단건 조회**
    public DesignerDTO getDesignerById(String id) {
        Designer designer = designerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 디자이너를 찾을 수 없습니다: " + id));
        return DesignerDTO.fromEntity(designer);
    }

    // **온라인 상담 가능 디자이너 목록 조회 (정렬은 Repository에서 JPQL 사용)**
    public List<DesignerDTO> getOnlineConsultingDesigners(ConsultingType consultingType, String sortOrder) {
        return designerRepository.findOnlineConsultingDesigners(consultingType, sortOrder)
                .stream()
                .map(DesignerDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // **오프라인 상담 가능 디자이너 목록 조회 (정렬은 Repository에서 JPQL 사용)**
    public List<DesignerDTO> getOfflineConsultingDesigners(ConsultingType consultingType, String sortOrder) {
        return designerRepository.findOfflineConsultingDesigners(consultingType, sortOrder)
                .stream()
                .map(DesignerDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
