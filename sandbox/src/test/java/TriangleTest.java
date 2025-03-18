import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

@Test
    void testPerimeter () {
        var triangle = new Triangle(3.0, 3.0, 3.0);
        double result = triangle.perimeter();
        Assertions.assertEquals(9.0, result);
    }

@Test
    void testArea () {
        var triangle = new Triangle(3.0, 3.0, 3.0);
        double result = triangle.area();
        Assertions.assertEquals(3.897114317029974, result);
    }

@Test
    void negativeSideA(){
    try {
       new Triangle(-3.0, 3.0, 3.0);
       Assertions.fail();
    } catch (IllegalArgumentException exception) {
    }
    }

@Test
    void triangleInequality(){
        try {
            new Triangle(1.0, 5.0, 10.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }
}