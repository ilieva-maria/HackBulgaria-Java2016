package gameOfLife;

public class Pair<L, R> {

	private final L left;
	private final R right;

	public Pair() {
		left = null;
		right = null;
	}

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return left;
	}

	public R getRight() {
		return right;
	}

}