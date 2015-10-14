package core;

import java.util.ArrayList;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;

import core.persistence.PersistentDataManagerAdapter;
import core.persistence.database.DatabaseStockControl;

@Default
@Model
@Named
public class StockManagerBasic implements StockManagerAdapter{
	private StockAdapter stock;
	private PersistentDataManagerAdapter persistentDataManagerAdapter;
	
	public StockManagerBasic() {
		this.stock=new StockBasic(new ArrayList<>());
		this.persistentDataManagerAdapter=new DatabaseStockControl();
	}
	
	public StockManagerBasic(StockAdapter stock, PersistentDataManagerAdapter persistentDataManager) {
		this.stock=stock;
		this.persistentDataManagerAdapter=persistentDataManager;
	}
	
	public StockAdapter getStock() {
		return stock;
	}

	public void setStock(StockAdapter stock) {
		persistentDataManagerAdapter.clearItemList();
		for (ItemAdapter item : stock.getItem()) {
			persistentDataManagerAdapter.insertItem(item);
		}
		updateData();
	}
	
	public void addItem(String name,String category, int quantity) {
		ItemAdapter item =new ItemBasic(name, category, quantity);
		persistentDataManagerAdapter.insertItem(item);
		updateData();
	}
	
	public void updateData() {
		stock.setItem(persistentDataManagerAdapter.getItemList());
	}
	
	public void addItem(ItemAdapter item) {
		persistentDataManagerAdapter.insertItem(item);
		updateData();
	}
	
	public void removeItem(ItemAdapter item) {
		persistentDataManagerAdapter.removeItem(item);
		updateData();
	}
	
	public void saveItem(ItemAdapter item) {
		persistentDataManagerAdapter.updateItem(item);
		updateData();
	}
}
