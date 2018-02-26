package ttl.sf.app;

public class Outer {

	
	private int val;

	public static void main(String[] args) {
		//Inner inner = new Inner();
		
		Outer outer = new Outer();
		outer.val++;
	}
	
	
	public void doStuff() {
		Inner inner = new Inner();
		inner.foo();
	}
	
	public static class Inner {
		public void foo() {
			System.out.println("Foo called");
		}
	}
}
