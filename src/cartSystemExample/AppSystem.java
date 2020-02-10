package cartSystemExample;
	
import java.io.IOException;
import java.util.Map;

/**
 * @author kevinbusch
 *
 */
public class AppSystem extends TheSystem {
	AppSystem() throws IOException {
		getItemCollection();
		setItemCollection(getItemCollection());
		
	}
	//iterates through hashMap and prints objects tostring()
	@Override
	public void display() throws IOException {
		
		for (Map.Entry mapElement : getItemCollection().entrySet()) { 

            System.out.println( mapElement.getValue());

		}
	}



	// adds item chekcing if item already exists 
	// if not then adds user input to hashMap
	/**
	 *
	 */
	@Override
	public Boolean add(Item item) {
		if (item != null) {
			if (getItemCollection().containsValue(item)) {
				System.out.println(item.getItemName() + " is already in the App System");
				return false;
			} else {
				getItemCollection().put(item.getItemName(), item);
				return true;
			}
		} else {
			return false;
		}
	}
}
