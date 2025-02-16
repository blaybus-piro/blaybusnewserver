package blaybus.domain.pay.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayTid {

    @Id
    private String id; // 주문 ID

    private String tid; // 결제 트랜잭션 ID
}
