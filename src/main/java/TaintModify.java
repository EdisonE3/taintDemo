import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.objectweb.asm.Opcodes.ASM9;

public class TaintModify {
  public void insertTaint(String className) throws Exception {
    InputStream is_1 = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(className);

    InputStream is_2 = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(className);

    if (is_1 == null || is_2 == null) {
      return;
    }

    // collect information of target classes
    ClassReader cr_1 = new ClassReader(is_1);
    ClassWriter cw_1 = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    CollectClassVisitor cv_1 = new CollectClassVisitor(ASM9, cw_1);
    cr_1.accept(cv_1, 0);

    //
    ClassReader cr_2 = new ClassReader(is_2);
    ClassWriter cw_2 = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    ClassVisitor cv_2 = new MyClassVisitor(ASM9, cw_2, cv_1.getIndexMap());
    cr_2.accept(cv_2, 0);

    byte[] code = cw_2.toByteArray();

    OutputStream f = new FileOutputStream("target/classes/" + className);
    f.write(code);
  }
}
