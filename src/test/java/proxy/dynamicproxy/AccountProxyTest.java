package proxy.dynamicproxy;

public class AccountProxyTest {
	public static void main(String[] args) {
		// 下面使用JDK的代理类，一个代理就可以代理很多接口
		Account account = (Account) new AccountProxyFactory().bind(new AccountImpl());
		account.queryAccount();
		account.updateAccount();
	}
}
