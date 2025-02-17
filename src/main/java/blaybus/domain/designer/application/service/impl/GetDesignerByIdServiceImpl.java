package blaybus.domain.designer.application.service.impl;

import blaybus.domain.designer.application.service.GetDesignerByIdService;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.designer.domain.repository.DesignerRepository;
import blaybus.domain.designer.presentation.dto.response.DesignerResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDesignerByIdServiceImpl implements GetDesignerByIdService {

    private final DesignerRepository designerRepository;

    @Override
    public DesignerResponseDTO execute(String id) {
        Designer designer = designerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("디자이너를 찾을 수 없습니다: " + id));
        return new DesignerResponseDTO(
                designer.getId(),
                designer.getName(),
                designer.getProfile(),
                designer.getArea(),
                designer.getExpertField(),
                designer.getIntroduce(),
                designer.getPortfolio(),
                designer.getType(),
                designer.getOfflinePrice(),
                designer.getOnlinePrice()
        );
    }
}

