
public class ItemProfitGreedy extends Item {

	private String name;
	private int classNum;
	private double weight;
	private double cost;
	private double resale;

	public ItemProfitGreedy(String name, int classNum, double weight, double cost, double resale){
		super(name, classNum, weight, cost, resale);

	}

	

	@Override
	public int compareTo(Item other) {
		/* For now, higher profit = less then other Item so that you can sort easily
		and keep removing first item*/
		

		if (this.getProfit() - other.getProfit() > 0) {
			return -1;
		} else if (this.getProfit() - other.getProfit() == 0) {
			return 0;
		} else {
			return 1;
		}
		
	}
}
