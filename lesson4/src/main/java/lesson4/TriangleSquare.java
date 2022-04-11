package lesson4;

public class TriangleSquare {
    public static double calculateSquare (double a, double b, double c) throws Exception {
        if ( a <= 0 || b <= 0 || c <=0) {
            throw new Exception("Это не похоже на треугольник");
        }

        //S=sqrt{p*(p-a)*(p-b)*(p-c)}
        //p - полупериметр = (a+b+c)/2

        double p = (a + b + c)/2;
        double s = (p * (p - a) * (p - b) * (p - c));
        return Math.sqrt(s);
    }
}
