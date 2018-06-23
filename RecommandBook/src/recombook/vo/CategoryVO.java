package recombook.vo;

public class CategoryVO implements VO{

	private int id;
	
	private int parent_id;
	
	private String category_key;
	
	private String category_value;
	
	private String category_brief;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getCategory_key() {
		return category_key;
	}

	public void setCategory_key(String category_key) {
		this.category_key = category_key;
	}

	public String getCategory_value() {
		return category_value;
	}

	public void setCategory_value(String category_value) {
		this.category_value = category_value;
	}

	public String getCategory_brief() {
		return category_brief;
	}

	public void setCategory_brief(String category_brief) {
		this.category_brief = category_brief;
	}
	
}
