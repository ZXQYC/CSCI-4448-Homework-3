import java.util.*;

// represents an instance where a customer makes a rental
public class Rental {
	private List<Tool> tools; // list of tools rented
	private Customer customer;
	private int startDay;
	private int days;
	private int endDay;
	private int cost;
	public Rental(List<Tool> tools, Customer customer, int startDay, int days) {
		this.tools = tools;
		this.customer = customer;
		this.startDay = startDay;
		this.days = days;
		this.endDay = startDay+days; // calculate end day
		this.cost = calculateCost();
	}
	private int calculateCost() {
		int c = 0;
		for (Tool t : tools) {
			c += t.getCost();
		}
		return c*days;
	}
	public List<Tool> tools() {return tools;}
	public int getCost() {return cost;}
	public int returnDay() {return endDay;}
	public int numTools() {return tools.size();}
	public String toString() { // returns formatted string with detailed info for the rental
		return "Rental by "+customer
				+"\n - Tools: "+tools.toString()
				+"\n - Start Day: "+Integer.toString(startDay)
				+"\n - Rental Duration (days): "+Integer.toString(days)
				+"\n - Cost of Rental ($): "+Integer.toString(cost);
	}
}
