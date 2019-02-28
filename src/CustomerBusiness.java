import java.util.List;


// business customer: always rents 3 tools for 7 days
public class CustomerBusiness extends Customer {
	public CustomerBusiness(Store store, String name, int customerID) {
		super(store,name,customerID);
	}
	public void attemptRental(int day) {
		List<Tool> inv = store.inventory();
		if (3 > inv.size() || numToolsRented()>0) return; // not enough tools to rent, or exceeds rent limit of 3
		Rental r = new Rental(selectRandomTools(inv,3),this,day,7);
		store.takeRental(r);
		addRental(r);
	}
}
