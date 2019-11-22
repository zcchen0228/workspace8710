package security;

public class ClassB {
	void foo(double position, String buffer) {
		int x = m1(10);
		int y = m2(10 + x);
		m2(10 + y);
	}

	public int m1(int x) {
		return x;
	}

	public int m2(int x) {
		return x;
	}

	void barB1(int x, int z) {
		int y = m1(x) + m2(10 + x);
		System.out.println(10 + y);
	}

	int barB2(int x, String str) {
		int y = m1(x) + m2(10 + x);
		System.out.println(10 + y);
		return x;
	}
}
