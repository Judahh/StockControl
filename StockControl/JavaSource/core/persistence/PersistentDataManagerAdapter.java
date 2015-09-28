package core.persistence;

import java.util.ArrayList;

import core.Item;

public interface PersistentDataManagerAdapter {
	public void updateItem(Item item);

	public ArrayList<Item> getItemList();
	
	public void clearItemList();

	public void insertItem(Item item);

	public void removeItem(Item item);
}
