package dynamicProxy;

import static net.bytebuddy.matcher.ElementMatchers.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

class ProductServiceTest {
	// newProxyInstance(어떤 클래스 로더써서 프록시 만들거냐, 프록시 인스턴스가 어떤 인터페이스의 구현체냐, 프록시의
	// 어떤 메서드 호출 됐을 때 어떻게 처리할거냐)
	ProductService productService = (ProductService)Proxy.newProxyInstance(ProductService.class.getClassLoader(),
		new Class[] {ProductService.class}, new InvocationHandler() {
			final ProductService productService = new DefaultProductService();

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if(method.getName().equals("rent")) {
				System.out.println("before delegation");
				Object invoke = method.invoke(productService, args);
				System.out.println("after delegation");
				}
				return method.invoke(productService, args);
			}
		});

	@Test
	@DisplayName("다이나믹 프록시동작")
	void useDynamicProxy() {
		//given
		Product product = new Product();
		product.setTitle("spring");
		//when
		productService.rent(product);
		//then
	}

	@Test
	@DisplayName("클래스 프록시 만들기")
	void useClassDynamicProxy() throws Exception {
		//given
		// MyProductService의 subClass를 만들어서 MyProductService의 클래스로더를 사용하여 로딩한거임.
		// 그리고 heap영역에 생성된 class 객체를 가져온거지
/*
		Class<? extends MyProductService> proxyClass = new ByteBuddy().subclass(MyProductService.class)
			.make().load(MyProductService.class.getClassLoader())
			.getLoaded();
		*/

		// 메서드 이름이 rent인 애는 InvocationHandler에게 위임한다. 라는 뜻
		Class<? extends MyProductService> proxyClass = new ByteBuddy().subclass(MyProductService.class)
			.method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
				MyProductService myProductService = new MyProductService();

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

					System.out.println("before");
					Object invoke = method.invoke(myProductService, args);
					System.out.println("after");
					return invoke;
				}
			}))
			.make().load(MyProductService.class.getClassLoader())
			.getLoaded();


		MyProductService myProductServiceProxy = proxyClass.getConstructor(null).newInstance();

		//when
		Product product = new Product();
		product.setTitle("product!!");
		//then
		myProductServiceProxy.rent(product);
		myProductServiceProxy.returnProduct(product);
	}

}