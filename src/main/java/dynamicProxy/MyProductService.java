package dynamicProxy;

public class MyProductService {
	public void rent(Product product) {
		System.out.println("rent: "+product.getTitle() );
	}

	public void returnProduct(Product product) {
		System.out.println("return: "+product.getTitle() );
	}
}
