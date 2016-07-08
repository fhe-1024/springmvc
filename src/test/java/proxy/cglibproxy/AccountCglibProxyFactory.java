package proxy.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//CGLIB�ڲ�ʹ�õ�ASM���������������������Ҫ����asm-3.3.jar��cglib-2.2.2.jar
public class AccountCglibProxyFactory implements MethodInterceptor {

	private Object target;

	public Object getInstance(Object target) {
		this.target = target;
		// Enhancer enhancer=new Enhancer();//�����������ɴ������
		// enhancer.setSuperclass(this.target.getClass());//���ø���
		// enhancer.setCallback(this);//���ûص��ö���Ϊ����
		return Enhancer.create(this.target.getClass(), this);
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		// �ų�Object���е�toString�ȷ���
		boolean objFlag = method.getDeclaringClass().getName().equals("java.lang.Object");
		if (!objFlag) {
			System.out.println("before");
		}
		Object result = null;
		// ����һ��ʹ��proxy.invokeSuper(obj,args)����������ܺ���⣬����ִ��ԭʼ��ķ���������һ������proxy.invoke(obj,args)������ִ����������ķ�����
		// ��������obj��������Ļ����ᷢ���ڴ��������Ϊ����ķ�����ͦ�ؽ���intercept�����������������ȥ��������ķ�������������ֱ��ѭ�������ˡ�
		result = proxy.invokeSuper(obj, args);
		// result = methodProxy.invoke(obj, args);
		if (!objFlag) {
			System.out.println("after");
		}
		return result;
	}

}
