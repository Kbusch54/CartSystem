package cartSystemExample;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author kevinbusch
 *
 */
public class CartSystem extends TheSystem {

	CartSystem() throws IOException {
		getItemCollection();
	}

	// method allows for double to have two decimal places for cleaner look
	/**
	 * @param x
	 * @param numberofDecimals
	 * @return
	 */
	private static BigDecimal truncateDecimal(double x, int numberofDecimals) {
		if (x > 0) {
			return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
		} else {
			return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
		}
	}

	// iterates through hashMap and casts item to object return
	// prints to console items
	// calculates subtotal, tax, and total
	/**
	 *
	 */
	@Override
	public void display() {
		Double subTotal = 0.0;
		for (Map.Entry mapElement : getItemCollection().entrySet()) {
			Item it = new Item();

			it = (Item) mapElement.getValue();

			subTotal += it.getQuantity() * it.getItemPrice();
			System.out.println(mapElement.getValue());
		}

		Double tax = subTotal * .05;

		System.out.println("Sub Total: $" + truncateDecimal(subTotal, 2));
		System.out.println("Tax: $" + truncateDecimal(tax, 2));
		System.out.println("Total: $" + truncateDecimal((subTotal + tax), 2));
	}

	// this method will decrement quantity of cart hashMap item specified
	// or remove it if quantity is 1
	@Override
	public Item remove(String itemName) {
		if (getItemCollection().get(itemName).getQuantity() <= 1) {
			if (getItemCollection().get(itemName) != null) {
				return getItemCollection().remove(itemName);

			} else {
				return null;
			}
		} else {
			getItemCollection().get(itemName).setQuantity((getItemCollection().get(itemName).getQuantity() - 1));
			return getItemCollection().get(itemName);
		}
	}

}
