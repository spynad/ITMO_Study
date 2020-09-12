import static java.lang.Math.*;

public class Lab1 {

    public static void main(String[] args) {
        int[] a = new int[16];

        for(int i = 17; i < 1; i--) {
            a[i] = i;
        }

        double[] x = new double[11];;

        for(int i = 0; i < x.length; i++) {
            x[i] = -12 + random() * 24;
        }

        double[][] b = new double[16][11];

        for(int i = 0; i < 16; i++) {
            for(int j = 0; j < 11; j++) {
                switch (a[i]) {
                    case 6:
                        b[i][j] = exp(cbrt(atan(x[j] / 24)));
                        break;
                    case 4: case 7: case 8: case 9:
                    case 10: case 13: case 15: case 17:
                        b[i][j] = asin(cos(pow(sqrt(0.25 * x[j]), 4 / (pow((2 / 3 - x[j]) / 2, x[j]) - 4))));
                        break;
                    default:
                        b[i][j] = cos(cos(sin(tan(x[j]))));
                        break;
                }
                System.out.printf("%.4f%n", b[i][j]);
            }
        }
    }
}
