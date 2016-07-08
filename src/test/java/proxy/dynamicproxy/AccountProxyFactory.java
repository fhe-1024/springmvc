package proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * 使用JDK动态代理使用到一个Proxy类和一个InvocationHandler接口。
Proxy已经设计得非常优美，但是还是有一点点小小的遗憾之处，那就是它仅支持interface代理（也就是代理类必须实现接口），因为它的设计注定了这个遗憾。
 */
public class AccountProxyFactory implements InvocationHandler {

	private Object targer;

	public Object bind(Object target) {
		this.targer = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		// 这里使用的是Jdk的动态代理，其必须要绑定接口，在我们的业务实现中有可能是没有基于接口是实现的。所以说这个缺陷cglib弥补了。
		boolean flag = method.getDeclaringClass().getName().equals("java.lang.Object");
		Object result = null;
		if (!flag) {
			System.out.println("代理before");
		}
		result = method.invoke(this.targer, args);
		if (!flag) {
			System.out.println("代理after");
		}
		return result;
	}

}
