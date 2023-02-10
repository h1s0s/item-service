package hello.itemservice.domain.item;

import lombok.Data;

@Data//@Data는 조금 위험함 실무에서는 getter,setter만
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
