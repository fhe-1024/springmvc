package proxy.staticproxy;

public class AccountProxyTest {
	public static void main(String[] args) {
		// AccountProxyΪ�Լ�ʵ�ֵĴ����࣬���Է��֣�һ��������ֻ��Ϊһ���ӿڷ���
		Account account = new AccountImpl();
		AccountProxy proxy = new AccountProxy(account);
		proxy.queryAccount();
		proxy.updateAccount();
	}
}
