package blaybus.domain.designer.application.service.impl;

import blaybus.domain.designer.application.service.GetConsultingDesignersService;
import blaybus.domain.designer.domain.repository.DesignerRepository;
import blaybus.domain.designer.presentation.dto.response.DesignerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConsultingDesignersServiceImpl implements GetConsultingDesignersService {

    private final DesignerRepository designerRepository;

    @Override
    public List<DesignerResponseDTO> execute(String sortOrder) {
        return (sortOrder.equalsIgnoreCase("OFFLINE") ?
                designerRepository.findOfflineConsultingDesigners(sortOrder) :
                designerRepository.findOnlineConsultingDesigners(sortOrder))
                .stream()
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
                .toList();
    }
}

