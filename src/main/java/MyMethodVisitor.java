import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.*;
import java.util.HashMap;

import static org.objectweb.asm.Opcodes.*;

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
  public void visitLocalVariable(String name, String descriptor,
                                 String signature, Label start, Label end, int index) {
    if (!name.equals("this")) {
      super.visitLocalVariable(name + "_tag", "I", signature, start, end, indexMap.get(index) - 1);
      super.visitLocalVariable(name, descriptor, signature, start, end, indexMap.get(index));
    } else {
      super.visitLocalVariable(name, descriptor, signature, start, end, index);
    }
  }

  @Override
  public void visitVarInsn(int opcode, int var) {
    super.visitVarInsn(opcode, indexMap.get(var));
  }

  @Override
  public void visitInsn(int opcode) {

    if (opcode == IRETURN){
      mv.visitTypeInsn(NEW, "TaintContainer");
      mv.visitInsn(SWAP);
      mv.visitInsn(DUP2);
      mv.visitInsn(POP);
      mv.visitInsn(SWAP);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(SWAP);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer",
              "valueOf", "(I)Ljava/lang/Integer;", false);
      mv.visitMethodInsn(INVOKESPECIAL, "TaintContainer",
              "<init>", "(ILjava/lang/Object;)V", false);
      mv.visitInsn(ARETURN);
      return;
    };

    super.visitInsn(opcode);
  }

  @Override
  public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
    if (descriptor.length() == 2 && descriptor.startsWith("[") && opcode == PUTFIELD){
      mv.visitVarInsn(ALOAD, 0);
      mv.visitInsn(SWAP);
      super.visitFieldInsn(opcode, owner, name, descriptor);
      mv.visitFieldInsn(PUTFIELD, owner, name+"_tag", "[I");
    }else {
      super.visitFieldInsn(opcode, owner, name, descriptor);
    }
  }
}
