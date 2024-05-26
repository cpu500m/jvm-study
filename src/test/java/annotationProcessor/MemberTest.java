package annotationProcessor;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

	@Test
	@DisplayName("getterSetter")
	void getterSetter(){
	    //given
		UseLomBok member = new UseLomBok();
		member.setName("paul");
		//when

	    //then
		Assertions.assertEquals(member.getName(), "paul");
	}

}