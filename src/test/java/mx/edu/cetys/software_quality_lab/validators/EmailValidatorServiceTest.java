package mx.edu.cetys.software_quality_lab.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class EmailValidatorServiceTest {

	@Test
	void shouldReturnFalse_WhenEmailIsNull() {

        // Arrange
        EmailValidatorService emailValidator = new EmailValidatorService();

        // Act
        var isValid = emailValidator.isValid(null);

        // Assert
        assertFalse(isValid);
	}

    @Test
    void shouldReturnFalse_WhenEmailIsEmpty() {

        // Arrange
        EmailValidatorService emailValidator = new EmailValidatorService();

        // Act is Empty
        var isValid = emailValidator.isValid("");

        // Assert
        assertFalse(isValid);
    }
}
