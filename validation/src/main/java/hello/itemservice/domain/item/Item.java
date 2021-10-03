package hello.itemservice.domain.item;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000",message = "������ 10000���� �Ѿ�� �մϴ�.")
public class Item {

//	@NotNull(groups = UpdateCheck.class) // ���� �䱸���� �߰�
    private Long id;
    
//	@NotNull(groups = {UpdateCheck.class, SaveCheck.class}) 
    private String itemName;
    
//	@NotNull(groups = {UpdateCheck.class, SaveCheck.class})
//    @Range(min = 1000, max = 1000000)
    private Integer price;

//	@NotNull(groups = {UpdateCheck.class, SaveCheck.class})
//    @Max(value = 9999, groups = {SaveCheck.class}) 
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
