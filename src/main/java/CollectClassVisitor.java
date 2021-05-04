import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.HashMap;

public class CollectClassVisitor extends ClassVisitor {
  public CollectClassVisitor(int i, ClassVisitor classVisitor) {
    super(i, classVisitor);
  }
  private HashMap<Integer, Integer> indexMap;

  public HashMap<Integer, Integer> getIndexMap() {
    return indexMap;
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
    CollectMethodVisitor collectMethodVisitor = new CollectMethodVisitor(super.visitMethod(access, name, descriptor, signature, exceptions));
    this.indexMap = collectMethodVisitor.getIndexMap();
    return collectMethodVisitor;
  }


}
