package proxy.dynamicproxy;

public class AccountProxyTest {
	public static void main(String[] args) {
		// ����ʹ��JDK�Ĵ����࣬һ������Ϳ��Դ���ܶ�ӿ�
		Account account = (Account) new AccountProxyFactory().bind(new AccountImpl());
		account.queryAccount();
		account.updateAccount();
	}
}
