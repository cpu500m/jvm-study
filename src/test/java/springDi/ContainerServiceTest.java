package springDi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContainerServiceTest {
	@Test
	@DisplayName("객체 생성 테스트")
	void getObject_BookRepository(){
	    //given
		BookRepository repository = ContainerService.getObject(BookRepository.class);
		//when

	    //then
		assertNotNull(repository);
	}

	@Test
	@DisplayName("서비스 테스트")
	void getObject_BookService(){
	    //given
		BookService bookService = ContainerService.getObject(BookService.class);
	    //when
	bookService.bookRepository.printNotNull();
	    //then
		assertNotNull(bookService);
		assertNotNull(bookService.bookRepository);
	}

}