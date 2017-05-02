
public class ItemHeuristic extends Item {

	private double heuristic;

	public ItemHeuristic(String name, int classNum, double weight, double cost, double resale){
		super(name, classNum, weight, cost, resale);
		
		this.heuristic = 1 * (resale - cost) + 2 * ((resale - cost) / weight); 
	}

	public double getHeuristic() {
		return heuristic;
	}

	@Override
	public int compareTo(Item other) {
		/* For now, higher profit = less then other Item so that you can sort easily
		and keep removing first item*/

		if (this.getHeuristic() - other.getHeuristic() > 0) {
			return -1;
		} else if (this.getHeuristic() - other.getHeuristic() == 0) {
			return 0;
		} else {
			return 1;
		}
		
	}
}
