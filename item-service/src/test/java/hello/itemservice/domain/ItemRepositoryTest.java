package hello.itemservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

	
	ItemRepository itemRepository = new ItemRepository(); 
	
	@AfterEach
	void afterEach() {
		itemRepository.clearStore(); 
	}
	
	
	@Test
	void save() {
		// given
		Item item = new Item("itemA", 1000, 10);
		
		// when
		itemRepository.save(item);
		
		// then
		Item findItem = itemRepository.findById(item.getId());
		assertThat(findItem.getId()).isEqualTo(item.getId());
	}
	
	@Test
	void findAll() {
		// given
		Item itemA = new Item("itemA", 1000, 10);
		Item itemB = new Item("itemB", 1000, 10);
		
		// when
		itemRepository.save(itemA);
		itemRepository.save(itemB);
		
		// then
		List<Item> result = itemRepository.findAll();
		assertThat(result.size()).isEqualTo(2);
		assertThat(result).contains(itemA, itemB);
	}
	
	
	@Test
	void updateItem() {
		// given
		Item itemA = new Item("itemA", 1000, 10);
		Item savedItem = itemRepository.save(itemA);
		Long itemId = savedItem.getId();
		//when
		Item updateParam = new Item("itemB", 10000, 15);
		itemRepository.update(itemId, updateParam);
		
		//then
		Item findItem = itemRepository.findById(itemId);
		
		assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
		assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
		assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
		
	}
}
