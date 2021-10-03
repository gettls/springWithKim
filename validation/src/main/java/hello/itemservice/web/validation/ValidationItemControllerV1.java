package hello.itemservice.web.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/validation/v1/items")
@RequiredArgsConstructor
public class ValidationItemControllerV1 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v1/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v1/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v1/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes, Model model) {
        
    	// ���� ���� ����� ����
    	Map<String, String> errors = new HashMap<>();
    	
    	// ���� ����
    	if(!StringUtils.hasText(item.getItemName())) {
    		errors.put("itemName", "��ǰ �̸��� �ʼ� �Դϴ�.");
    	}
    	if(item.getPrice()==null || item.getPrice()<1000 || item.getPrice()>1000000) {
    		errors.put("price", "������ 1,000 ~ 1,000,000 ���� ����մϴ�.");
    	}
    	if(item.getQuantity()==null || item.getQuantity()>=9999) {
    		errors.put("quantity", "������ �ִ� 9,999 ���� ����մϴ�.");
    	}
    	
    	// Ư�� �ʵ尡 �ƴ� ���� �� ����
    	if (item.getPrice() != null && item.getQuantity() != null) {
    		int resultPrice = item.getPrice() * item.getQuantity();
    		if (resultPrice < 10000) {
    			errors.put("globalError", "���� * ������ ���� 10,000�� �̻��̾�� �մϴ�. ���� ��= " + resultPrice);
    		}
    	}
    	
    	// ������ �����ϸ� �ٽ� �Է� ������
    	if(!errors.isEmpty()) {
    		log.info("errors = {}", errors);
    		model.addAttribute("errors", errors);
    		return "validation/v1/addForm";
    	}
    	
    	// ���� ����
    	Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v1/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v1/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v1/items/{itemId}";
    }

}

