package com.haw.se1lab.common.api.datatype;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haw.se1lab.Application;

/**
 * Test class for {@link PhoneNumber}.
 * 
 * @author Arne Busch
 */
@ActiveProfiles("test") // causes exclusive creation of general and test-specific beans (marked by @Profile("test"))
@ExtendWith(SpringExtension.class) // required to use Spring TestContext Framework in JUnit 5
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE) // test environment
public class PhoneNumberTest {

	@ParameterizedTest // executes this test method for each value defined in @ValueSource
	@ValueSource( // test values for this method
			strings = { "+49-40-58967572", "+49-040-58967572", "+49-040-5896" })
	public void constructPhoneNumber_Success(String phoneNumber) {
		new PhoneNumber(phoneNumber);
	}

	@ParameterizedTest // executes this test method for each value defined in @ValueSource
	@ValueSource( // test values for this method
			strings = { "+4-040-5896", "49-040-5896", "+49-0-5896", "+49-040-896" })
	public void constructPhoneNumber_Fail_PhoneNumberInvalid(String phoneNumber) {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new PhoneNumber(phoneNumber))
				.withMessageContaining("Invalid phone number");
	}

}
