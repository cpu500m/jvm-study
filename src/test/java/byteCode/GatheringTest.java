package byteCode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GatheringTest {
	@Test
	@DisplayName("상태 검사")
	void isFull(){
	    //given
		Gathering g = new Gathering();
	    //when
		g.maxNumberOfAttendees = 100;
		g.numberOfEnrollment = 10;
		//then
		Assertions.assertFalse(g.isEnrollmentFull());
	}

}