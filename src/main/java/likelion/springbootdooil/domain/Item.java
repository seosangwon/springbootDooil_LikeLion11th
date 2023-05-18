package likelion.springbootdooil.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id @GeneratedValue // PK지정, GeneartedValue로 Item생성 시 PK번호 증가
    @Column(name="order_item_id")
    private Long id;

    private String brand;

    private String name;

    private String price;

    private int StockQuantity;

}
