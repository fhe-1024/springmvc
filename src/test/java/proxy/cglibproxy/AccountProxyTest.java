package proxy.cglibproxy;

public class AccountProxyTest {
	public static void main(String[] args) {
		// ��������cglib�Ĵ���
        // 1.֧��ʵ�ֽӿڵ���
		Account account = (Account) new AccountCglibProxyFactory().getInstance(new AccountImpl());
		account.queryAccount();
		account.updateAccount();
		// 2.֧��δʵ�ֽӿڵ���
		Person person = (Person) new AccountCglibProxyFactory().getInstance(new Person());
		person.show();
	}
}
