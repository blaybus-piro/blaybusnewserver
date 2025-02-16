package blaybus.location;

import blaybus.domain.map.application.service.PositionCreateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LocationTest {

    @Autowired
    private PositionCreateService positionCreateService;

    @Test
    public void test() throws Exception {
        positionCreateService.createPosition("서울대학교");
    }
}
