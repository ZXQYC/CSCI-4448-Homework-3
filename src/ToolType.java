
// Strategy for Tool
public class ToolType {
	private String name;
	private int cost;
	public ToolType(String name, int cost) {
		this.cost = cost;
		this.name = name;
	}
	public int cost() {return cost;}
	public String toString() {return name;}
}
