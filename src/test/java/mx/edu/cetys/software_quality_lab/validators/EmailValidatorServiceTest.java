package mx.edu.cetys.software_quality_lab.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorServiceTest {

    @Test
    void rule0_Empty(){
        EmailValidatorService emailValidatorService = new EmailValidatorService();

        var isValid = emailValidatorService.isValid("");

        assertFalse(isValid);
    }

    @Test
    void rule0_Null(){
        EmailValidatorService emailValidatorService = new EmailValidatorService();

        var isValid = emailValidatorService.isValid(null);

        assertFalse(isValid);
    }

    @Test
    void rule1_shouldReturnFalse_WhenContainsInvalidCharacters() {
        // Arrange: Rule #1 (Valid: 1-0, a-z)
        EmailValidatorService emailValidator = new EmailValidatorService();
        // Act: contains '!' which is not in the allowed list
        var isValid = emailValidator.isValid("aron!4#gm4l.com");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule2_shouldReturnTrue_WhenUserHasSpecialChars() {
        // Arrange: Rule #2 (Valid user special chars: . - _ +)
        EmailValidatorService emailValidator = new EmailValidatorService();
        // Act
        var isValid = emailValidator.isValid("aron+coppel4#gm4l.com");
        // Assert
        assertTrue(isValid);
    }

    @Test
    void rule3_shouldReturnFalse_WhenSeparatorIsNotHashtag() {
        // Arrange: Rule #3 (Must use # to separate user and provider)
        EmailValidatorService emailValidator = new EmailValidatorService();
        // Act: Using standard '@' instead of '#'
        var isValid = emailValidator.isValid("aron4@gm4l.com");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule3_shouldReturnFalse_WhenThereIsNoSeparator() {
        EmailValidatorService emailValidator = new EmailValidatorService();

        var isValid = emailValidator.isValid("aron4gm4l.cm");

        assertFalse(isValid);
    }

    @Test
    void rule3_EmptyFirstPart(){
        EmailValidatorService emailValidator = new EmailValidatorService();

        var isValid = emailValidator.isValid("#gm4l.cm");

        assertFalse(isValid);
    }

    @Test
    void rule3_EmptySecondPart(){
        EmailValidatorService emailValidator = new EmailValidatorService();

        var isValid = emailValidator.isValid("hola#");

        assertFalse(isValid);

    }

    @Test
    void rule3_EmptyThirdPart(){
        EmailValidatorService emailValidator = new EmailValidatorService();

        var isValid = emailValidator.isValid("hola#g4m#");

        assertFalse(isValid);
    }


    @Test
    void rule4_shouldReturnFalse_WhenDiphthongIsPresent() {
        // Arrange: Rule #4 (No 2 vowels together)
        EmailValidatorService emailValidator = new EmailValidatorService();
        // Act: 'io' is a diphthong
        var isValid = emailValidator.isValid("stud4nt#studio.com");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule5_shouldReturnFalse_WhenDomainSuffixIsTooLong() {
        // Arrange: Rule #5 (Domain max: 5)
        EmailValidatorService emailValidator = new EmailValidatorService();
        // Act: '.online' is 6 characters
        var isValid = emailValidator.isValid("user4#gm4l.online");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule5_shouldReturnFalse_WhenNoDotIsPresent() {
        // Arrange
        EmailValidatorService emailValidator = new EmailValidatorService();
        // Act: No hay punto '.'
        var isValid = emailValidator.isValid("user4#gmailcom");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule6_shouldReturnFalse_WhenDomainSuffixIsNull() {
        EmailValidatorService emailValidator = new EmailValidatorService();

        var isValid = emailValidator.isValid("user4#gml.");

        assertFalse(isValid);
    }

    @Test
    void rule6_shouldReturnFalse_WhenEmailExceedsMaxLength() {
        // Arrange: Rule #6 (Max Length: 47)
        EmailValidatorService emailValidator = new EmailValidatorService();
        // Act
        var isValid = emailValidator.isValid("qwertyupilokjhgfdsazxmcnvbhfrujgtqmfertgfdvbhy#4gml.cm");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule7_shouldReturnFalse_WhenNoFourIsPresent() {
        // Arrange: Rule #7 (Mandatory 4 in the whole email)
        EmailValidatorService emailValidator = new EmailValidatorService();
        // Act
        var isValid = emailValidator.isValid("aron#gm.com");
        // Assert
        assertFalse(isValid);
    }


    @Test
    void validEmail_shouldReturnTrue_WhenAllRulesMet() {
        // Arrange: A "Happy Path" test
        EmailValidatorService emailValidator = new EmailValidatorService();
        // Act: Meets all rules: # separator, has a 4, no diphthongs, length fine
        var isValid = emailValidator.isValid("aron#gm4l.com");
        // Assert
        assertTrue(isValid);
    }
}