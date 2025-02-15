package blaybus.domain.pay.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlaybusPayTid {

    @Id
    private String id; // 주문 ID

    private String tid; // 결제 트랜잭션 ID
}
