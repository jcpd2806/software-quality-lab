package mx.edu.cetys.software_quality_lab.poc;


import org.junit.jupiter.api.*;

import java.util.logging.Logger;

public class LifeCycleTest {

    private static final Logger LOGGER = Logger.getLogger(LifeCycleTest.class.getName());

    @DisplayName("Pruebas POC")
    @BeforeAll
    static void beforeAllTest(){
        LOGGER.info("This is test: \tbeforeAllTest");
    }

    @BeforeEach
     void beforeEachTest(){
        LOGGER.info("\nThis is test: \tbeforeEachTest");
    }

    @DisplayName("Test 1")
    @Test
    void testLifeCycle_1() {
        LOGGER.info("\nThis is test: \ttestLifeCycle_1");

    }

    @DisplayName("Test 2")
    @Test
    void testLifeCycle_2() {
        LOGGER.info("\nThis is test: \ttestLifeCycle_2");
    }

    @AfterEach
    void afterEachTest(){
        LOGGER.info("\nThis is test: \tafterEachTest");
    }

    @AfterAll
    static void afterAllTest(){
        LOGGER.info("This is test: \tafterAllTest");
    }

}
