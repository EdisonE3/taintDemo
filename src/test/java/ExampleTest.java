import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ExampleTest {
  @Test
  void testAdd() throws Exception {
    Class<?> example_class = Class.forName("Example");
    Example example_ins = (Example) example_class.getDeclaredConstructor(int.class, int.class).newInstance(1, 1);
    int result = (int) example_class.getDeclaredMethod("doMath", int.class, int.class).invoke(example_ins, 2, 2);
    System.out.println(result);
  }
}
