package lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoxTest {
    Box box;

    @Nested
    class WhenEmpty {
        @BeforeEach
        void CreateEmptyBox() {
            box = new Box();
        }

        @Test
        void ecxeptionWhenTryToShuffleBoxTest() {
            Assertions.assertThrows(NullPointerException.class, () -> box.shuffleBalls());
        }

        @Test
        void addBallTest() {
            box.addBall();
            Assertions.assertEquals(box.getBallsCount(), 1);
        }

        @Nested
        class WhenOneBall {
            @BeforeEach
            void addBall() {
                box.addBall();
            }
        }

        @Test
        void deleteBallTest() throws BoxIsAlreadyEmptyException {
            box.deleteBalls();
            Assertions.assertEquals(box.getBallsCount(), 0);
        }
    }
}
