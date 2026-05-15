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
    void rule0Empty(){

        var isValid = emailValidatorService.isValid("");

        assertFalse(isValid);
    }

    @Test
    void rule0Null(){

        var isValid = emailValidatorService.isValid(null);

        assertFalse(isValid);
    }

    @Test
    void rule1ShouldReturnFalseWhenContainsInvalidCharacters() {
        //Arrange

        // Act: contains '!' which is not in the allowed list
        var isValid = emailValidatorService.isValid("aron!4#gm4l.com");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule2ShouldReturnTrueWhenUserHasSpecialChars() {

        // Act
        var isValid = emailValidatorService.isValid("aron+coppel4#gm4l.com");
        // Assert
        assertTrue(isValid);
    }

    @Test
    void rule3ShouldReturnFalseWhenSeparatorIsNotHashtag() {

        // Act: Using standard '@' instead of '#'
        var isValid = emailValidatorService.isValid("aron4@gm4l.com");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule3ShouldReturnFalseWhenThereIsNoSeparator() {

        var isValid = emailValidatorService.isValid("aron4gm4l.cm");

        assertFalse(isValid);
    }

    @Test
    void rule3EmptyFirstPart(){

        var isValid = emailValidatorService.isValid("#gm4l.cm");

        assertFalse(isValid);
    }

    @Test
    void rule3EmptySecondPart(){

        var isValid = emailValidatorService.isValid("hola#");

        assertFalse(isValid);

    }

    @Test
    void rule3EmptyThirdPart(){

        var isValid = emailValidatorService.isValid("hola#g4m#");

        assertFalse(isValid);
    }


    @Test
    void rule4ShouldReturnFalseWhenDiphthongIsPresent() {

        // Act: 'io' is a diphthong
        var isValid = emailValidatorService.isValid("stud4nt#studio.com");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule5ShouldReturnFalseWhenDomainSuffixIsTooLong() {

        // Act: '.online' is 6 characters
        var isValid = emailValidatorService.isValid("user4#gm4l.online");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule5ShouldReturnFalseWhenNoDotIsPresent() {

        // Act: No hay punto '.'
        var isValid = emailValidatorService.isValid("user4#gmailcom");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule6ShouldReturnFalseWhenDomainSuffixIsNull() {

        var isValid = emailValidatorService.isValid("user4#gml.");

        assertFalse(isValid);
    }

    @Test
    void rule6ShouldReturnFalseWhenEmailExceedsMaxLength() {

        // Act
        var isValid = emailValidatorService.isValid("qwertyupilokjhgfdsazxmcnvbhfrujgtqmfertgfdvbhy#4gml.cm");
        // Assert
        assertFalse(isValid);
    }

    @Test
    void rule7ShouldReturnFalseWhenNoFourIsPresent() {

        // Act
        var isValid = emailValidatorService.isValid("aron#gm.com");
        // Assert
        assertFalse(isValid);
    }


    @Test
    void validEmailShouldReturnTrueWhenAllRulesMet() {

        // Act: Meets all rules: # separator, has a 4, no diphthongs, length fine
        var isValid = emailValidatorService.isValid("aron#gm4l.com");
        // Assert
        assertTrue(isValid);
    }
}