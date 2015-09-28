package view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import core.Item;
import core.Stock;
import core.StockManager;

@ManagedBean(name="viewStockManager")
@ViewScoped
public class ViewStockManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StockManager stockManager;

	public ViewStockManager() {
		this.stockManager=new StockManager();
	}
	
	public Stock getStock() {
		return stockManager.getStock();
	}

	public void setStock(Stock stock) {
		this.stockManager.setStock(stock);
	}
	
	public void addItem(String name,String category, int quantity) {
		stockManager.addItem(name, category, quantity);
	}
	
	public void addItem(Item item) {
		stockManager.addItem(item);
	}
	
	public void removeItem(Item item) {
		stockManager.removeItem(item);
	}
	
	public void saveItem(Item item) {
		stockManager.saveItem(item);
	}
}
