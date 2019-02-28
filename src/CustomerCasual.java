import java.util.*;


// casual customer: always rents 1-2 tools for 1-2 days
public class CustomerCasual extends Customer {
	public CustomerCasual(Store store, String name, int customerID) {
		super(store,name,customerID);
	}
	public void attemptRental(int day) {
		List<Tool> inv = store.inventory();
		int toolsWanted = (int)(2*Math.random()+1);
		if (toolsWanted > inv.size() || numToolsRented()+toolsWanted>3) return; // not enough tools to rent, or exceeds rent limit of 3
		int duration = (int)(2*Math.random()+1);
		Rental r = new Rental(selectRandomTools(inv,toolsWanted),this,day,duration);
		store.takeRental(r);
		addRental(r);
	}
}
