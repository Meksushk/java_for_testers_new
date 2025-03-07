import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

@Test
    void TestPerimeter () {
        var triangle = new Triangle(3.0, 3.0, 3.0);
        double result = triangle.Perimeter();
        Assertions.assertEquals(9.0, result);
    }
@Test
    void TestArea () {
        var triangle = new Triangle(3.0, 3.0, 3.0);
        double result = triangle.Area();
        Assertions.assertEquals(3.897114317029974, result);
    }
}