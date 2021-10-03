package hello.itemservice.domain.item;

public enum ItemType {

	BOOK("����"), FOOD("����"), ETC("��Ÿ");
	
	private final String description;
	
	ItemType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
