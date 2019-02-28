import java.util.*;

// represents a customer of the store
public abstract class Customer {
	protected Store store;
	private String name;
	private int customerID;
	private List<Rental> activeRentals = new ArrayList<Rental>();
	public Customer(Store store, String name, int customerID) {
		this.store = store;
		this.name = name;
		this.customerID = customerID;
	}
	public String toString() { // returns string containing customer's name followed by id in parentheses
		return name+" ("+Integer.toString(customerID)+")";
	}
	public int numToolsRented() { // calculates number of tools currently rented
		int a = 0;
		for (Rental r : activeRentals) {
			a += r.numTools();
		}
		return a;
	}
	public void newDay(int day) { // makes customer return all rentals due this day
		List<Rental> finishedRentals = new ArrayList<Rental>(); // list of rentals due
		for (Rental r : activeRentals) if (r.returnDay() == day) { // return each rental due today and add to due list
			store.returnRental(r);
			finishedRentals.add(r);
		}
		for (Rental r : finishedRentals) { // remove due list from list of active rentals
			activeRentals.remove(r);
		}
	}
	// helper function for subclasses: selects random tools from the store inventory
	// requires that num is not greater than tools.size()
	protected List<Tool> selectRandomTools(List<Tool> tools, int num) {
		Collections.shuffle(tools);
		return tools.subList(0, num);
	}
	// helper for adding a rental
	protected void addRental(Rental r) {
		activeRentals.add(r);
	}
	// attempt to make a rental if possible: needs to be implemented by subclass
	public abstract void attemptRental(int day);
}
