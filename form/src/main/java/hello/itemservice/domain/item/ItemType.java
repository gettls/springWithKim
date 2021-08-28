package hello.itemservice.domain.item;

public enum ItemType {

	BOOK("����"), FOOD("����"), ETC("��Ÿ");
	
	private final String description;
	
	private ItemType(String description) {
		this.description = description;
	}
}
