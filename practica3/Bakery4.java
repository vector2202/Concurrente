public class Bakery4 {
	private volatile boolean[] flag = new boolean[4];
	private volatile int[] label = new int[4];

	public Bakery4() {
		for (int i = 0; i < 4; i++) {
			flag[i] = false;
			label[i] = -1;
		}
	}

	private int get_max() {
		int max = 0;
		for (int i = 0; i < 4; i++) {
			if (label[i] > max) {
				max = label[i];
			}
		}
		return max;
	}

	public void lock() {
		Thread currentThread = Thread.currentThread();
		long id = currentThread.getId();
		int i = (int) (id % 4);

		flag[i] = true;
		label[i] = this.get_max() + 1;
		for (int k = 0; k < 4; k++) {
			if (k != i) {
				

				while (flag[k] && (label[k] < label[i]  || (label[k] == label[i]  && k < i))) {
				}
				;
			}
		}
	}

	public void unlock() {
		Thread currentThread = Thread.currentThread();
		long id = currentThread.getId();
		int i = (int) (id % 4);
		flag[i] = false;
	}
}
