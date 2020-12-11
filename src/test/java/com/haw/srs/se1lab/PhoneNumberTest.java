package com.haw.srs.se1lab;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PhoneNumberTest {

	@ParameterizedTest
	@ValueSource(strings = { "+49-40-58967572", "+49-040-58967572", "+49-040-5896" })
	public void createPhoneNumberSuccess(String phoneNumber) {
		new PhoneNumber(phoneNumber);
	}

	@ParameterizedTest
	@ValueSource(strings = { "+4-040-5896", "49-040-5896", "+49-0-5896", "+49-040-896" })
	public void createPhoneNumberFail(String phoneNumber) {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new PhoneNumber(phoneNumber))
				.withMessageContaining("Invalid phone number");
	}

}
