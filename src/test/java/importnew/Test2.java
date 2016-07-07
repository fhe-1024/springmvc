package importnew;

public class Test2 {
	public static void main(String[] args) {
		A1 a = new B1();
		a.say();
	}
}

class A1 {
	public void say() {
		System.out.println(11);
	}
}

class B1 extends A1 {

	@Override
	public void say() {
		System.out.println(22);
	}

}