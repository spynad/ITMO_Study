import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        B b = new B();
        Class testclass = b.getClass();
        Class c = testclass.getSuperclass();
        System.out.println();
    }

}

class A {
    private static final int a = 1;
}

class B extends A {
    private static final int b = 2;
}