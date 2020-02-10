package cartSystemExample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author kevinbusch
 *
 */

//constructor if AppSystem initializes the system reads through 
//the file and adds it to hashMap
public abstract class TheSystem {
	private HashMap<String, Item> itemCollection;

	@SuppressWarnings("resource")
	TheSystem() throws IOException {
		itemCollection = new HashMap<String, Item>();
		if(getClass().getSimpleName().equals("AppSystem")) {
			String line;
			String[] itemInfo;
			BufferedReader br = new BufferedReader(new FileReader("./src/sample.txt"));
			// reads one line at a time
			while ((line = br.readLine()) != null) {
				// line is split on double spaces 
				// and added to string array
			    itemInfo = line.split("\\s{2,}");
			    //iterate through string array and add strings 
			    //to match item attributes
			    for (int i = 0; i < itemInfo.length-3; i+=3 ) {
					Item it = new Item();
					it.setItemName(itemInfo[i]);
					it.setItemDesc(itemInfo[i+1]);
					it.setItemPrice(Double.valueOf(itemInfo[i+2]));
					it.setAvailableQuantity(Integer.parseInt(itemInfo[i+3]));
					itemCollection.put(it.getItemName(), it);	
				}
				}
			}
			
		}
	

	/**
	 * @param item
	 * @return
	 */
	// method checks if item has enough in stock vs the quantity asking for 
	public Boolean checkAvailability(Item item) {
		if (item.getQuantity() >= item.getAvailableQuantity()) {
			System.out.println("System is unable to add " + item.getItemName() + " to the cart. System only has "
					+ item.getAvailableQuantity() + item.getItemName() + "s.");
			return false;
		} else {
			return true;
		}
	}

	// method adds object item to hashMap
	public Boolean add(Item item) {
		if (item != null) {
			// if item is in hashMap already the 
			// quantity is incremented
			if (itemCollection.containsKey(item.getItemName())) {
				item.setQuantity(item.getQuantity()+1);
				return true;
			} else {
				//else it is just added
				itemCollection.put(item.getItemName(), item);
				return true;
			}
		} else {
			return false;
		}
	}
	// removes item from hashMap
	public Item remove(String itemName) {
		if (itemCollection.get(itemName) != null) {
			return itemCollection.remove(itemName);
			

		} else {
			return null;
		}
	}

	public HashMap<String, Item> getItemCollection() {
		return itemCollection;
	}

	public void setItemCollection(HashMap<String, Item> itemCollection) {
		this.itemCollection = itemCollection;
	}

	@Override
	public String toString() {
		return "TheSystem [itemCollection=" + itemCollection + "]";
	}


	public abstract void display() throws IOException;
}
