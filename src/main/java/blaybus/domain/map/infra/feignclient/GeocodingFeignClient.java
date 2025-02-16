package blaybus.domain.map.infra.feignclient;

import blaybus.domain.map.presentation.dto.response.GeocodingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geocodingClient", url = "https://maps.googleapis.com/maps/api/geocode")
public interface GeocodingFeignClient {
    @GetMapping("/json")
    GeocodingResponse geocode(@RequestParam("address") String address, @RequestParam("key") String apiKey);
}
