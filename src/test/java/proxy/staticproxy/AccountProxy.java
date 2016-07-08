package proxy.staticproxy;

public class AccountProxy implements Account {

	private Account account;

	public AccountProxy(Account account) {
		super();
		this.account = account;
	}

	public void queryAccount() {
		// TODO Auto-generated method stub
		System.out.println("代理before");
		account.queryAccount();
		System.out.println("代理after");
	}

	public void updateAccount() {
		// TODO Auto-generated method stub
		System.out.println("代理before");
		account.queryAccount();
		System.out.println("代理after");
	}

}
