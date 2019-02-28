import java.util.List;


// regular customer: always rents 1-3 tools for 3-5 days
public class CustomerRegular extends Customer {
	public CustomerRegular(Store store, String name, int customerID) {
		super(store,name,customerID);
	}
	public void attemptRental(int day) {
		List<Tool> inv = store.inventory();
		int toolsWanted = (int)(3*Math.random()+1);
		if (toolsWanted > inv.size() || numToolsRented()+toolsWanted>3) return; // not enough tools to rent, or exceeds rent limit of 3
		int duration = (int)(3*Math.random()+3);
		Rental r = new Rental(selectRandomTools(inv,toolsWanted),this,day,duration);
		store.takeRental(r);
		addRental(r);
	}
}
