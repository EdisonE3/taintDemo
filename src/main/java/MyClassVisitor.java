import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.HashMap;

public class MyClassVisitor extends ClassVisitor {
  private final HashMap<Integer, Integer> indexMap;

  public MyClassVisitor(int i, ClassVisitor classVisitor, HashMap<Integer, Integer> indexMap) {
    super(i, classVisitor);
    this.indexMap = indexMap;
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
    return new MyMethodVisitor(super.visitMethod(access, name, MethodModify.modifyDesc(descriptor), signature, exceptions), name, indexMap);
  }

  @Override
  public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
    super.visitField(access, name, descriptor, signature, value);
    return FieldModify.modifyField(cv, access, name, descriptor, signature, value);
  }
}
