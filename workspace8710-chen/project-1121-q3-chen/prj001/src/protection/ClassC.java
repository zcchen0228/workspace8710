package protection;

public class ClassC {
	void foo(float position, String buffer) {
		int x = m1(10);
		int y = m2(10 + x);
		m2(10 + y);
	}

	int barC1(int x, int z) {
		int y = m1(x) + m2(10 + x);
		System.out.println(10 + y);
		return z;
	}

	int barC2(int x, String str) {
		int y = m1(x) + m2(10 + x);
		System.out.println(10 + y);
		return x;
	}

	public int m1(int x) {
		return x;
	}

	public int m2(int x) {
		return x;
	}
}
