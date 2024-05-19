package byteCode;

public class Gathering {
	int maxNumberOfAttendees;
	int numberOfEnrollment;

	public boolean isEnrollmentFull() {
		if (maxNumberOfAttendees == 0) {
			return false;
		}
		return numberOfEnrollment >= maxNumberOfAttendees;
	}
}
