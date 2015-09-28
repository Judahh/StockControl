package core;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="item")
@SessionScoped
public class Item {
	private String name;
	private int quantity;
	private int identifier;
	private String category;

	public Item() {
	}

	public Item(String name, String category, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.category = category;
		this.identifier = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
}