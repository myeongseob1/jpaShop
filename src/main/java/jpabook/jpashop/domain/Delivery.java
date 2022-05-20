package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;


    @Enumerated(EnumType.STRING) // 중간에 뭔가 들어가도 상관이없음(ORDINAL 쓰면 중간에 뭐가 들어가면 장애날수도)
    private DeliveryStatus deliveryStatus; //Ready Comp
}
