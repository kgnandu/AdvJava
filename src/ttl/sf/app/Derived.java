package ttl.sf.app;

public class Derived extends Base {

	public static void foo() {
		System.out.println("Derived foo");
	}
	
	public static void main(String[] args) {
		Base.foo();
		Derived.foo();
	}
}
