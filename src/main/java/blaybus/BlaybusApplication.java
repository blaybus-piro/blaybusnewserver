package blaybus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BlaybusApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlaybusApplication.class, args);
    }

}
