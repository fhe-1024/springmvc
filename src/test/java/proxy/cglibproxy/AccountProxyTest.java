package proxy.cglibproxy;

public class AccountProxyTest {
	public static void main(String[] args) {
		// 下面是用cglib的代理
        // 1.支持实现接口的类
		Account account = (Account) new AccountCglibProxyFactory().getInstance(new AccountImpl());
		account.queryAccount();
		account.updateAccount();
		// 2.支持未实现接口的类
		Person person = (Person) new AccountCglibProxyFactory().getInstance(new Person());
		person.show();
	}
}
