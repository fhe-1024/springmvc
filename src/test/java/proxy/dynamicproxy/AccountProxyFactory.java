package proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * ʹ��JDK��̬����ʹ�õ�һ��Proxy���һ��InvocationHandler�ӿڡ�
Proxy�Ѿ���Ƶ÷ǳ����������ǻ�����һ���СС���ź�֮�����Ǿ�������֧��interface����Ҳ���Ǵ��������ʵ�ֽӿڣ�����Ϊ�������ע��������ź���
 */
public class AccountProxyFactory implements InvocationHandler {

	private Object targer;

	public Object bind(Object target) {
		this.targer = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		// ����ʹ�õ���Jdk�Ķ�̬���������Ҫ�󶨽ӿڣ������ǵ�ҵ��ʵ�����п�����û�л��ڽӿ���ʵ�ֵġ�����˵���ȱ��cglib�ֲ��ˡ�
		boolean flag = method.getDeclaringClass().getName().equals("java.lang.Object");
		Object result = null;
		if (!flag) {
			System.out.println("����before");
		}
		result = method.invoke(this.targer, args);
		if (!flag) {
			System.out.println("����after");
		}
		return result;
	}

}
