package likelion.springbootdooil.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data // ctrl shift enter 는 자동완성
public class Member {
    @Id @GeneratedValue // id는 자카르타
    private Long id;
    private String name;

    private String city;
    private String state;
    private String street;
    private String zipcode;



}
