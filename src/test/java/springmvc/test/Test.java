package springmvc.test;

public class Test {

	public static void main(String[] args) {
		try {
			Integer.parseInt("hello");
			System.out.println(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(2);
		}
		System.out.println(3);
	}
}
