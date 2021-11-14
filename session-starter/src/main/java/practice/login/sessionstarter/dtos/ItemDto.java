package practice.login.sessionstarter.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.login.sessionstarter.domain.items.Item;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/11/14
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemDto {
    private Long    id;
    private String  itemName;
    private Integer price;
    private Integer quantity;

    @Builder
    private ItemDto(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public static ItemDto toCreate(String itemName, Integer price, Integer quantity) {
        return ItemDto.builder()
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();
    }

    public static ItemDto toUpdate(String itemName, Integer price, Integer quantity) {
        return ItemDto.builder()
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();
    }

    public static ItemDto toRead(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();
    }
}
