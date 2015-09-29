package core;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="stock")
@SessionScoped
public class StockBasic implements StockAdapter{
	private List<ItemAdapter> item;

//	public Stock() {
//		item=new ArrayList<>();
//	}

	public StockBasic(List<ItemAdapter> item) {
		this.item = item;
	}
	
	public List<ItemAdapter> getItem() {
		return item;
	}

	public void setItem(List<ItemAdapter> item) {
		this.item = item;
	}
}