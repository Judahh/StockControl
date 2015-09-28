package core;

import core.persistence.PersistentDataManagerAdapter;
import core.persistence.database.DatabaseStockControl;

public class StockManager {
	private Stock stock;
	private PersistentDataManagerAdapter persistentDataManagerAdapter;

	public StockManager() {
		stock=new Stock();
		persistentDataManagerAdapter = new DatabaseStockControl();
		addItem("nameFasdf", "categoryFassdfsdff", 0);
	}
	
	public Stock getStock() {
		//updateData();
		return stock;
	}

	public void setStock(Stock stock) {
		persistentDataManagerAdapter.clearItemList();
		for (Item item : stock.getItem()) {
			persistentDataManagerAdapter.insertItem(item);
		}
		updateData();
	}
	
	public void addItem(String name,String category, int quantity) {
		Item item =new Item(name, category, quantity);
		persistentDataManagerAdapter.insertItem(item);
		updateData();
	}
	
	public void updateData() {
		stock.setItem(persistentDataManagerAdapter.getItemList());
	}
	
	public void addItem(Item item) {
		persistentDataManagerAdapter.insertItem(item);
		updateData();
	}
	
	public void removeItem(Item item) {
		persistentDataManagerAdapter.removeItem(item);
		updateData();
	}
	
	public void saveItem(Item item) {
		persistentDataManagerAdapter.updateItem(item);
		updateData();
	}
}
