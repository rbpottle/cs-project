public class ClassItem implements Comparable<ClassItem>{

	private int classNum;
	private double profit;

	public ClassItem(int classNum, double profit) {
		this.classNum = classNum;
		this.profit = profit;
		/*profit is generic for whatever huerstic is passed in*/
	}

	public int getClassNum() {
		return classNum;
	}

	public double getProfit() {
		return profit;
	}

	@Override
	public int compareTo(ClassItem other) {
		/*Still ordered same as Item*/
		if (this.getProfit() > other.getProfit()) {
			return -1;
		} else if (this.getProfit() == other.getProfit()) {
			return 0;
		} else {
			return 1;
		}
	}


}