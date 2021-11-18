package practice.login.interceptorstarter.web.item.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import practice.login.interceptorstarter.domain.items.ItemDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    //수정에서는 수량은 자유롭게 변경할 수 있다.
    private Integer quantity;

    public ItemDto toDto() {
        return ItemDto.toUpdate(itemName, price, quantity);
    }
}