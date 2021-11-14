package practice.login.sessionstarter.web.item.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import practice.login.sessionstarter.domain.items.ItemDto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemSaveForm {

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(value = 9999)
    private Integer quantity;

    public ItemDto toDto() {
        return ItemDto.toCreate(itemName, price, quantity);
    }
}
