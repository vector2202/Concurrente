public class Peterson {
	private volatile boolean[] flag = new boolean[4];
	private volatile int[] victim = new int[4];

	public Peterson() {
		for (int i = 0; i < 4; i++) {
			flag[i] = false;
		}
		for (int i = 0; i < 3; i++) {
			victim[i] = -1;
		}
	}

	public void lock() {
		Thread currentThread = Thread.currentThread();
		long id = currentThread.getId();
		int i = (int) (id % 4);
		for (int k = 0; k < 4; k++) {
			if (k != i) {
				flag[i] = true;
				victim = i;
				while (flag[k] && victim == i) {};
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
