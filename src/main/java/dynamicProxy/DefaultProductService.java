package dynamicProxy;

public class DefaultProductService implements ProductService{
	@Override
	public void rent(Product product) {
		System.out.println("rent: "+product.getTitle() );
	}

	@Override
	public void returnProduct(Product product) {
		System.out.println("return: "+product.getTitle() );
	}
}
