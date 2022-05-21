package jpabook.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;


//값 타입의 경우 변경이 불가능하게 setter을 없애고 처음에 생성할때만 값을 설정할수 있게 생성자에 설계
//자바 기본생성자는 protected로 설정해두는것이 public 보다는 안전하다
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { //직접 호출 하지 않도록 protected로
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
