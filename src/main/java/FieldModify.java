import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

public class FieldModify {
  public static FieldVisitor modifyField(ClassVisitor cv, int access, String name, String descriptor, String signature, Object value) {
    switch (descriptor.length()) {
      case 1:
        return cv.visitField(access, name + "_tag", "I", signature, null);
      case 2:
        return cv.visitField(access, name + "_tag", "[I", signature, null);
      default:
        break;
    }
    return null;
  }
}
