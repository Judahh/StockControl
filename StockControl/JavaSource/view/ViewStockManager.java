package view;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import core.ItemBasic;
import core.StockBasic;
import core.StockAdapter;
import core.StockManagerAdapter;
import core.StockManagerBasic;
import core.persistence.database.DatabaseStockControl;

@ManagedBean(name="viewStockManager")
@ViewScoped
public class ViewStockManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5842041905574114044L;
	/**
	 * 
	 */

	private StockManagerAdapter stockManager;

	public ViewStockManager() {
		
		this.stockManager=new StockManagerBasic(new StockBasic(new ArrayList<>()), new DatabaseStockControl());
	}
	
	public StockAdapter getStock() {
		return stockManager.getStock();
	}

	public void setStock(StockBasic stock) {
		this.stockManager.setStock(stock);
	}
	
	public void updateData() {
		this.stockManager.updateData();
	}
	
	public void addItem(String name,String category, int quantity) {
		stockManager.addItem(name, category, quantity);
	}
	
	public void addItem(ItemBasic item) {
		stockManager.addItem(item);
	}
	
	public void removeItem(ItemBasic item) {
		stockManager.removeItem(item);
	}
	
	public void saveItem(ItemBasic item) {
		stockManager.saveItem(item);
	}
}
