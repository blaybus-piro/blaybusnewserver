package blaybus.domain.designer.application.service;

import blaybus.domain.designer.domain.repository.DesignerRepository;
import blaybus.domain.designer.presentation.dto.Reponse.DesignerResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetDesignerByIdService {

    private final DesignerRepository designerRepository;

    public DesignerResponseDTO execute(String id) {
        return designerRepository.findById(id)
                .map(designer -> new DesignerResponseDTO(
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
                ))
                .orElseThrow(() -> new EntityNotFoundException("디자이너를 찾을 수 없습니다: " + id));
    }
}