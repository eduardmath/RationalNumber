import java.io.IOException;

public class Rational implements Cloneable, Comparable<Rational> {
    int Gcd(int a, int b) {
        while (a != 0) {
            b %= a;
            int tmp = b;
            b = a;
            a = tmp;
        }
        return b;
    }

    public Rational() {
    }

    public Rational(int a) {
        Numerator = a;
    }

    public Rational(int num, int denom) throws IOException {
        if (denom == 0) {
            throw new IOException("Denominator can't be equal to 0");
        }
        this.Numerator = num / Gcd(num, denom);
        this.Denominator = denom / Gcd(num, denom);
        if (Denominator < 0) {
            Numerator = -Numerator;
            Denominator = -Denominator;
        }
    }

    public Rational(Rational r) {
        Numerator = r.Numerator;
        Denominator = r.Denominator;
    }

    public void setNum(int a) {
        Numerator = a / Gcd(a, Denominator);
        Denominator /= Gcd(a, Denominator);
    }

    public void setDenom(int b) throws IOException {
        if (b == 0) {
            throw new IOException("Denominator can't be equal to 0");
        }
        Denominator = b / Gcd(b, Numerator);
        Numerator /= Gcd(b, Numerator);
        if (Denominator < 0) {
            Numerator = -Numerator;
            Denominator = -Denominator;
        }
    }

    public int GetNum() {
        return Numerator;
    }

    public int GetDenom() {
        return Denominator;
    }

    public Rational plus(Rational rhs) throws IOException {
        return new Rational(Numerator * rhs.Denominator + rhs.Numerator * Denominator,
                Denominator * rhs.Denominator);
    }
    public Rational plus(int rhs) throws IOException {
        return new Rational(Numerator + rhs * Denominator, Denominator);
    }

    public Rational minus(Rational rhs) throws IOException {
        return new Rational(Numerator * rhs.Denominator - rhs.Numerator * Denominator,
                Denominator * rhs.Denominator);
    }
    public Rational minus(int rhs) throws IOException {
        return new Rational(Numerator - rhs * Denominator, Denominator);
    }

    public Rational multiplication(Rational rhs) throws IOException {
        return new Rational(Numerator * rhs.Numerator, Denominator * rhs.Denominator);
    }
    public Rational multiplication(int rhs) throws IOException {
        return new Rational(Numerator * rhs, Denominator);
    }

    public Rational division(Rational rhs) throws IOException {
        if (rhs.Numerator == 0) {
            throw new IOException("Division by zero");
        }
        return new Rational(Numerator * rhs.Denominator, Denominator * rhs.Numerator);
    }
    public Rational division(int rhs) throws IOException {
        if (rhs == 0) {
            throw new IOException("Division by zero");
        }
        return new Rational(Numerator, Denominator * rhs);
    }

    public Rational plusTo(Rational rhs) {
        Numerator = Numerator * rhs.Denominator + rhs.Numerator * Denominator;
        Denominator *= rhs.Denominator;
        int a = Gcd(Numerator, Denominator);
        Denominator /= a;
        Numerator /= a;
        if (Denominator < 0) {
            Numerator = -Numerator;
            Denominator = -Denominator;
        }
        return new Rational(this);
    }
    public Rational plusTo(int rhs) {
        Numerator += rhs * Denominator;
        int a = Gcd(Numerator, Denominator);
        Denominator /= a;
        Numerator /= a;
        if (Denominator < 0) {
            Numerator = -Numerator;
            Denominator = -Denominator;
        }
        return new Rational(this);
    }

    public Rational minusTo(Rational rhs) {
        Numerator = Numerator * rhs.Denominator - rhs.Numerator * Denominator;
        Denominator *= rhs.Denominator;
        int a = Gcd(Numerator, Denominator);
        Denominator /= a;
        Numerator /= a;
        return new Rational(this);
    }
    public Rational minusTo(int rhs) {
        Numerator -= rhs * Denominator;
        int a = Gcd(Numerator, Denominator);
        Denominator /= a;
        Numerator /= a;
        return new Rational(this);
    }

    public Rational multiplicationTo(Rational rhs) {
        Denominator *= rhs.Denominator;
        Numerator *= rhs.Numerator;
        int a = Gcd(Numerator, Denominator);
        Denominator /= a;
        Numerator /= a;
        return new Rational(this);
    }
    public Rational multiplicationTo(int rhs) {
        Numerator *= rhs;
        int a = Gcd(Numerator, Denominator);
        Denominator /= a;
        Numerator /= a;
        return new Rational(this);
    }

    public Rational divisionTo(Rational rhs) {
        Denominator *= rhs.Numerator;
        Numerator *= rhs.Denominator;
        int a = Gcd(Numerator, Denominator);
        Denominator /= a;
        Numerator /= a;
        return new Rational(this);
    }
    public Rational divisionTo(int rhs) {
        Denominator *= rhs;
        int a = Gcd(Numerator, Denominator);
        Denominator /= a;
        Numerator /= a;
        return new Rational(this);
    }

    @Override
    public Rational clone() throws CloneNotSupportedException {
        return (Rational) super.clone();
    }

    @Override
    public int compareTo(Rational rhs) {
        return Numerator * rhs.Denominator - Denominator * rhs.Numerator;
    }

    public boolean isLess(Rational rhs) {
        if (Denominator == rhs.Denominator) {
            return Numerator < rhs.Numerator;
        } else {
            return (Numerator * rhs.Denominator - rhs.Numerator * Denominator) / (rhs.Denominator * Denominator) < 0;
        }
    }

    public boolean isGreater(Rational rhs) {
        return !isLess(rhs);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;

        if (otherObject == null) return false;

        if (getClass() != otherObject.getClass())
            return false;

        Rational r = (Rational) otherObject;
        return Numerator == r.Numerator && Denominator == r.Denominator;
    }

    public String toString() {
        if (Numerator == 0) {
            return "0";
        }
        if (Denominator == 1) {
            return Integer.toString(Numerator);
        }
        String a = Integer.toString(Numerator) + '/';
        a += Integer.toString(Denominator);
        return a;
    }

    private int Numerator = 0;
    private int Denominator = 1;
}
