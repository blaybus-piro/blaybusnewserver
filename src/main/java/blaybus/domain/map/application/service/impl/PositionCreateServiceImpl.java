package blaybus.domain.map.application.service.impl;

import blaybus.domain.map.application.exception.GeocodeAPIConnectException;
import blaybus.domain.map.application.service.PositionCreateService;
import blaybus.domain.map.domain.entity.Position;
import blaybus.domain.map.domain.repository.PositionRepository;
import blaybus.domain.map.infra.feignclient.GeocodingFeignClient;
import blaybus.domain.map.presentation.dto.response.GeocodingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionCreateServiceImpl implements PositionCreateService {

    private final GeocodingFeignClient geocodingFeignClient;
    private final PositionRepository positionRepository;
    @Value("${google.map.api-key}")
    private String apiKey;

    @Override
    public void createPosition(String name, String address) {
        GeocodingResponse geocodingResponse = geocodingFeignClient.geocode(address, apiKey);
        if(geocodingResponse.results().isEmpty()) {
            throw new GeocodeAPIConnectException();
        }
        Position position = Position.builder()
                .name(name)
                .address(address)
                .latitude(geocodingResponse.results().get(0).geometry().location().lat())
                .longitude(geocodingResponse.results().get(0).geometry().location().lng())
                .build();

        positionRepository.save(position);
    }

}
