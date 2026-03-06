package mx.edu.cetys.software_quality_lab.validators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorServiceTest {

    private EmailValidatorService emailValidatorService;

    @BeforeAll
    static void beforeAllTest(){
        // Log Start Time

    }

    @BeforeEach
    void beforeEachTest(){
        // Restart Class
        emailValidatorService = new EmailValidatorService();
    }

    @Test
    void rule0_Empty(){

        var isValid = emailValidatorService.isValid("");

        assertFalse(isValid);
    }

    @Test
    void rule0_Null(){

        var isValid = emailValidatorService.isValid(null);

        assertFalse(isValid);
    }

    @Test
    void rule1_shouldReturnFalse_WhenContainsInvalidCharacters() {
        //Arrange

        // Act: contains '!' which is not in the allowed list
        var isValid = emailValidatorService.isValid("aron!4#gm4l.com");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule2_shouldReturnTrue_WhenUserHasSpecialChars() {

        // Act
        var isValid = emailValidatorService.isValid("aron+coppel4#gm4l.com");
        // Assert
        assertTrue(isValid);
    }

    @Test
    void rule3_shouldReturnFalse_WhenSeparatorIsNotHashtag() {

        // Act: Using standard '@' instead of '#'
        var isValid = emailValidatorService.isValid("aron4@gm4l.com");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule3_shouldReturnFalse_WhenThereIsNoSeparator() {

        var isValid = emailValidatorService.isValid("aron4gm4l.cm");

        assertFalse(isValid);
    }

    @Test
    void rule3_EmptyFirstPart(){

        var isValid = emailValidatorService.isValid("#gm4l.cm");

        assertFalse(isValid);
    }

    @Test
    void rule3_EmptySecondPart(){

        var isValid = emailValidatorService.isValid("hola#");

        assertFalse(isValid);

    }

    @Test
    void rule3_EmptyThirdPart(){

        var isValid = emailValidatorService.isValid("hola#g4m#");

        assertFalse(isValid);
    }


    @Test
    void rule4_shouldReturnFalse_WhenDiphthongIsPresent() {

        // Act: 'io' is a diphthong
        var isValid = emailValidatorService.isValid("stud4nt#studio.com");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule5_shouldReturnFalse_WhenDomainSuffixIsTooLong() {

        // Act: '.online' is 6 characters
        var isValid = emailValidatorService.isValid("user4#gm4l.online");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule5_shouldReturnFalse_WhenNoDotIsPresent() {

        // Act: No hay punto '.'
        var isValid = emailValidatorService.isValid("user4#gmailcom");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule6_shouldReturnFalse_WhenDomainSuffixIsNull() {

        var isValid = emailValidatorService.isValid("user4#gml.");

        assertFalse(isValid);
    }

    @Test
    void rule6_shouldReturnFalse_WhenEmailExceedsMaxLength() {

        // Act
        var isValid = emailValidatorService.isValid("qwertyupilokjhgfdsazxmcnvbhfrujgtqmfertgfdvbhy#4gml.cm");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule7_shouldReturnFalse_WhenNoFourIsPresent() {

        // Act
        var isValid = emailValidatorService.isValid("aron#gm.com");
        // Assert
        assertFalse(isValid);
    }


    @Test
    void validEmail_shouldReturnTrue_WhenAllRulesMet() {

        // Act: Meets all rules: # separator, has a 4, no diphthongs, length fine
        var isValid = emailValidatorService.isValid("aron#gm4l.com");
        // Assert
        assertTrue(isValid);
    }
}