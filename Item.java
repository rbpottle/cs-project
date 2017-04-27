public class Item extends Comparable<Item>{

	private String name;
	private int class;
	private double weight;
	private double cost;
	private double resale;

	public Item(String name, int class, double weight, double cost, double resale){
		this.name = name;
		this.class = class;
		this.weight = weight;
		this.cost = cost;
		this.resale = resale;
	}

	public String getName() {
		return name;
	}

	public int getClass() {
		return class;
	}

	public double getWeight() {
		return weight;
	}

	public double getCost() {
		return cost;
	}

	public double getResale() {
		return resale;
	}

	public double getProfit() {
		return resale - cost;
	}

	@Override
	public int compareTo(Item other) {
		return this.getProfit() - other.getProfit();
	}
}