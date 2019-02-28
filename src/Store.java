import java.util.*;

// represents the store simulator
public class Store {
	public static void main(String[] args) {
		Store store = new Store();
		for (int i=1;i<=35;i++) { // simulate 35 days
			store.simulateDay();
		}
		store.printStatusReport();
	}
	// the 5 tool types with varying costs
	private static final List<ToolType> TOOLTYPES = new ArrayList<ToolType>(Arrays.asList(
			new ToolType("painting",1),
			new ToolType("concrete",10),
			new ToolType("plumbing",100),
			new ToolType("woodwork",1000),
			new ToolType("yardwork",10000)
	));
	private List<Rental> completedRentals = new ArrayList<Rental>();
	private List<Rental> activeRentals = new ArrayList<Rental>();
	private List<Customer> customers = new ArrayList<Customer>();
	private List<Tool> tools = new ArrayList<Tool>();
	private int profit = 0;
	private int day = 0;
	
	public Store() {
		// get the store started by generating tools and customeres
		generateTools();
		generateCustomers();
	}
	// helper for generators to create random names
	private static String generateName() {
		String s = "";
		for (int i=0;i<6;i++) {
			s += Character.toString("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int)(Math.random()*26)));
		}
		return s;
	}
	// helper for generator to create random ids
	private static int generateID() {
		return (int)(Math.random()*1000000);
	}
	// make 20 random tools
	private void generateTools() {
		for (int i=0;i<20;i++) {
			tools.add(new Tool(generateName(),TOOLTYPES.get((int)(Math.random()*5))));
		}
	}
	// make 10 random customers
	private void generateCustomers() {
		// TODO
		for (int i=0;i<10;i++) {
			if (Math.random()<1./3) {
				customers.add(new CustomerCasual(this,generateName(),generateID()));
			}
			else if (Math.random()<1./2) {
				customers.add(new CustomerBusiness(this,generateName(),generateID()));
			}
			else {
				customers.add(new CustomerRegular(this,generateName(),generateID()));
			}
		}
	}
	// simulates a new day for the store
	public void simulateDay() {
		day++;
		// make customers respond to day change (return tools)
		for (Customer c : customers) {
			c.newDay(day);
		}
		// simulate new rentals from customers
		Collections.shuffle(customers);
		for (Customer c : customers) {
			if (Math.random()<.5) { // 50% chance a customer attempts a rental
				c.attemptRental(day);
			}
		}
	}
	// generates the current inventory (tools not rented out)
	public List<Tool> inventory() {
		List<Tool> a = new ArrayList<Tool>();
		for (Tool t : tools) if (!t.isRented()) {
			a.add(t);
		}
		return a;
	}
	// takes out a rental
	public void takeRental(Rental r) {
		for (Tool t : r.tools()) {
			t.rent();
		}
		profit += r.getCost();
		activeRentals.add(r);
	}
	// returns a rental
	public void returnRental(Rental r) {
		for (Tool t : r.tools()) {
			t.unrent();
		}
		activeRentals.remove(r);
		completedRentals.add(r);
	}
	// full status report
	public void printStatusReport() {
		// step 1: print current inventory size and all tools in inventory
		List<Tool> inv = inventory();
		System.out.println("Current tools in inventory (total "+Integer.toString(inv.size())+"):");
		System.out.println(inv);
		System.out.println();
		// step 2: print total amount of profit made
		System.out.println("Total money made: "+Integer.toString(profit));
		System.out.println("\n=====\n");
		// step 3: print all completed rentals
		System.out.println("Completed rentals:\n");
		for (Rental r : completedRentals) System.out.println(r);
		System.out.println("\n=====\n");
		// step 4: print all active rentals
		System.out.println("Active rentals:\n");
		for (Rental r : activeRentals) System.out.println(r);
	}
}