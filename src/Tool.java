
// represents a tool
public class Tool {
	private ToolType type; // ToolType strategy object for this tool (contains cost and tooltype name)
	private boolean isRented = false;
	private String name;
	public Tool(String name, ToolType type) {
		this.name = name;
		this.type = type;
	}
	public int getCost() {
		return type.cost();
	}
	public void rent() {
		isRented = true;
	}
	public void unrent() {
		isRented = false;
	}
	public boolean isRented() {
		return isRented;
	}
	public String toString() { // prints name of tool followed by name of tooltype
		return name+" ("+type+" tool)";
	}
}
