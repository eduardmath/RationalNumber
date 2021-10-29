public class Main {
    public static void main(String[] args) throws Exception {
        Rational a = new Rational(1 ,6);
        Rational b = new Rational(1 ,2);
        Rational c = new Rational(1 ,3);
        
        System.out.print(a.toDouble());
        System.out.println();

        a.plusTo(b);
        System.out.print(a.toString());
        a.plusTo(c);

        System.out.println();
        System.out.print(a.toDouble());
    }
}
