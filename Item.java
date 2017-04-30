
public class Item implements Comparable<Item>{

	private String name;
	private int classNum;
	private double weight;
	private double cost;
	private double resale;

	public Item(String name, int classNum, double weight, double cost, double resale){
		this.name = name;
		this.classNum = classNum;
		this.weight = weight;
		this.cost = cost;
		this.resale = resale;
	}

	public String getName() {
		return name;
	}

	public int getClassNum() {
		return classNum;
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

	public double getPCR() {
		return (resale - cost)/weight;
	}

	@Override
	public int compareTo(Item other) {
		/* For now, higher profit = less then other Item so that you can sort easily
		and keep removing first item*/
		
		
		if (this.getPCR() - other.getPCR() > 0) {
			return -1;
		} else if (this.getPCR() - other.getPCR() == 0) {
			return 0;
		} else {
			return 1;
		}
		
		/*
		if (this.getProfit() - other.getProfit() > 0) {
			return -1;
		} else if (this.getProfit() - other.getProfit() == 0) {
			return 0;
		} else {
			return 1;
		}
		*/
	}
}