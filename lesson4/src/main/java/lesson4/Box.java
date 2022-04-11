package lesson4;

public class Box {
    public Integer ballsCount;

    public Box() {
        this.ballsCount = 0;
    }

    public Integer getBallsCount() {
        return ballsCount;
    }

    public void addBall() {
        ballsCount ++;
    }

    public void deleteBalls() throws BoxIsAlreadyEmptyException {
        if (ballsCount == 0) {
            throw new BoxIsAlreadyEmptyException();
        }
        ballsCount --;
    }

    public void shuffleBalls() {
        if(ballsCount == 0) {
            throw new NullPointerException();
        }
        System.out.println("Перемешали мячи");
    }
}
