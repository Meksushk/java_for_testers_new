public class Triangle{
    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;

        if(a < 0 || b < 0 || c < 0){
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if(a + b < c || a + c < b || b + c < a){
            throw new IllegalArgumentException("The triangle inequality is violated");
        }
    }

    public double perimeter(){
        return this.a + this.b + this.c;
    }

    public double area(){
        return((Math.sqrt((perimeter() / 2.0) * ((perimeter() / 2.0 ) - this.a) * ((perimeter() / 2.0 ) - this.b) * ((perimeter() / 2.0 ) - this.c))));
    }
}
