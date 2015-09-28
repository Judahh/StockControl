package core;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="stock")
@SessionScoped
public class Stock {
	private List<Item> item;

	public Stock() {
		item=new ArrayList<>();
	}

	public Stock(List<Item> item) {
		this.item = item;
	}
	
	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
}