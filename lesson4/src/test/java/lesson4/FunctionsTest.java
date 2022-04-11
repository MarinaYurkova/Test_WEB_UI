package lesson4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static lesson4.Functions.isPalindrome;
import static lesson4.Functions.isPrime;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionsTest {
    private static Logger logger = LoggerFactory.getLogger(FunctionsTest.class);

    @BeforeAll
    static void beforeAll() {

        System.out.println("Метод выполнится 1 раз перед всеми тестами");
        logger.info("info log");
        logger.warn("warn log");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Метод выполнится перед каждыми тестами");
    }

    @Test
    @DisplayName("Проверка простого числа")
    void givenPrimeNumberWhenCallIsPrimeThenTrue () {
        boolean result = isPrime(7);
        assertTrue(result);
    }

    //@Test
    //@DisplayName("Подробное описание цели теста")
    //void isPalindromePositiveTest() {
    // assertTrue(isPalindrome("123321"));
   //}

    //@Test
    //@DisplayName("Подробное описание цели теста")
    //void isPalindromePositiveTest2() {
     //  assertTrue(isPalindrome("1235321"));
    //}

    @ParameterizedTest
    @ValueSource (strings = {"123321", "1235321"})
    void isPalindromePositiveTest(String word) {
        assertTrue(isPalindrome(word));
    }

    @Test
    void isNotPalindrome() {
        assertFalse(isPalindrome("12"));
    }

    @ParameterizedTest
    @CsvSource({"true, 123321", "false, 12"})
    void commonPalindromeTest(boolean expectedResult, String word) {
        assertEquals(expectedResult, isPalindrome(word));
    }

    @ParameterizedTest
    @MethodSource("catAndAgeDataProvider")
    void catTest(Cat cat, Integer age) {
        assertEquals(age, cat.getAge());
    }

    private static Stream<Arguments> catAndAgeDataProvider() {
        return Stream.of (
                Arguments.of(new Cat("Barsik", 10), 10),
                Arguments.of(new Cat("Murzik", 10), 11));
    }

    @AfterEach
    void afterEach() {
        System.out.println("Метод выполнится 1 раз после каждого теста");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Метод выполнится после всех тестов");
    }
}
