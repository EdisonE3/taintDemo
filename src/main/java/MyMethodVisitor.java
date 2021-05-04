import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.HashMap;

public class MyMethodVisitor extends MethodVisitor {
  private final String methodName;
  private final HashMap<Integer, Integer> indexMap;

  public MyMethodVisitor(MethodVisitor mv, String methodName, HashMap<Integer, Integer> indexMap) {
    super(Opcodes.ASM9, mv);
    this.methodName = methodName;
    this.indexMap = indexMap;
  }

  @Override
  public void visitCode() {
    System.out.println("at Method ‘" + methodName + "’ Begin...");
    super.visitCode();
  }

  @Override
  public void visitEnd() {
    System.out.println("at Method ‘" + methodName + "’End.");
    super.visitEnd();
  }

  @Override
  public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
    if (!name.equals("this")) {
      super.visitLocalVariable(name + "_tag", descriptor, signature, start, end, index);
      super.visitLocalVariable(name, descriptor, signature, start, end, index + 1);
    } else {
      super.visitLocalVariable(name, descriptor, signature, start, end, index);
    }
  }

  @Override
  public void visitVarInsn(int opcode, int var) {
    super.visitVarInsn(opcode, indexMap.get(var));
  }
}
