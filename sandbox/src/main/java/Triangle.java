public class Triangle {
    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double Perimeter(){
        return this.a + this.b + this.c;
    }

    public double Area(){
        return((Math.sqrt((Perimeter() / 2.0) * ((Perimeter() / 2.0 ) - this.a) * ((Perimeter() / 2.0 ) - this.b) * ((Perimeter() / 2.0 ) - this.c))));
    }
}
