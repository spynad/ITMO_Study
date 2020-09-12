public class Lab1 {

    public static void main(String[] args) {
        int[] a = new int[16];;

        for(int i = 17; i < 1; i--) {
            a[i] = i;
        }

        double[] x = new double[11];;

        for(int i = 0; i < x.length; i++) {
            x[i] = -12 + Math.random() * 24;
        }

        double[][] b = new double[16][11];

        for(int i = 0; i < 16; i++) {
            for(int j = 0; j < 11; j++) {
                switch (b[i]) {
                    case 6:
                        b[i][j] = Math.exp(Math.cbrt(Math.atan(x[j] / 24)));
                        break;
                    case 4: case 7: case 8: case 9:
                    case 10: case 13: case 15: case 17:
                        b[i][j] = Math.asin(Math.cos(Math.pow(Math.sqrt(0.25 * x[j]), 4 / (Math.pow((2 / 3 - x[j]) / 2, x[j]) - 4))));
                        break;
                    default:
                        b[i][j] = Math.cos(Math.cos(Math.sin(Math.tan(x[j]))));
                        break;
                }
                System.out.println("%.4f", b[i][j]);
            }
        }
    }
}
