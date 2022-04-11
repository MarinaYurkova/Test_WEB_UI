package lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    void successCalculationTest() throws Exception {
        Assertions.assertEquals(TriangleSquare.calculateSquare(4,2,4), 3.872983346207417);
    }
}
