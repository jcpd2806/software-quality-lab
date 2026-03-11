package mx.edu.cetys.software_quality_lab.poc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParameterizedCalculatorTest {
    public static Stream<Object[]> provideNumbers() {
        return Stream.of(
                new Object[]{2, 4},
                new Object[]{5, 10},
                new Object[]{9, 18}
        );
    }

    public static Stream<Object> providePet(){
        return  Stream.of(
                new Object[] {new Pet("Perrito", "negro","chihuahua", 15), true},
                new Object[] {new Pet(null, null, null, 10),false},
                new Object[] {new Pet(null, null, null, 11),true},
                new Object[] {new Pet(null, null, null, -10),false}
                //new Object[] {"HOLA","HOLAHOLA" } //fail
        );
    }

//    Test

    @DisplayName("Test with CSV Source")
    @ParameterizedTest
    @CsvSource({
            "10, 10, 20",
            "0, 0, 0",
            "-5, 5, 0",
            "1, 2, 3"
    })
    void testSumWithCsvSource(int a, int b, int target){
        // Arrange
        // Act
        var sum = a + b;
        assertEquals(target, sum);
    }

    @DisplayName("Validate String not empty")
    @ParameterizedTest
    @ValueSource(strings = {
            "Hello",
            "Hello World",
            "aaaron",
    })
    void testValidateStringNotEmpty(String values){
        var isEmpty = values.isEmpty();
        assertFalse(isEmpty);
    }

    @DisplayName("Validate double of an integer with Method Source")
    @ParameterizedTest
    @MethodSource("provideNumbers")
    void testDouble(int a, int target) {
        var ansDouble = a * 2;
        assertEquals(target, ansDouble);
    }

    @ParameterizedTest
    @DisplayName("Validate Pets is older than 10 years old")
    @MethodSource("providePet")
    void testDouble(Pet pet, boolean expected){
        // act
        //var doub = a*2;
        var isOlderThanTen = pet.age() > 10 ? true : false;
        assertEquals(expected, isOlderThanTen);
    }
    // POJO Plain Old Java Object: CLase con getters y setters
    // Records - POJO inmutable sin BoilerPlate
    private record Pet(String name, String color, String race, int age){};
}
