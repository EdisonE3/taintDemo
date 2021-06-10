import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

import org.objectweb.asm.*;

public class ExampleDump implements Opcodes {
  public static void main(String[] args) throws Exception {
    byte[] code = dump();
    OutputStream f = new FileOutputStream("target/classes/" + "Example.class");
    f.write(code);
  }

  public static byte[] dump() throws Exception {

    ClassWriter cw = new ClassWriter(0);
    FieldVisitor fv;
    MethodVisitor mv;
    AnnotationVisitor av0;

    cw.visit(52, ACC_PUBLIC + ACC_SUPER, "Example", null, "java/lang/Object", null);

    cw.visitSource("Example.java", null);

    {
      fv = cw.visitField(ACC_STATIC, "val", "I", null, null);
      fv.visitEnd();
    }
    {
      fv = cw.visitField(0, "bArray", "[B", null, null);
      fv.visitEnd();
    }
    {
      mv = cw.visitMethod(0, "<init>", "(I)V", null, null);
      mv.visitCode();
      Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(6, l0);
      mv.visitVarInsn(ALOAD, 0);
      mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
      Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLineNumber(7, l1);
      mv.visitVarInsn(ALOAD, 0);
      mv.visitVarInsn(ILOAD, 1);
      mv.visitIntInsn(NEWARRAY, T_BYTE);
      mv.visitFieldInsn(PUTFIELD, "Example", "bArray", "[B");
      Label l2 = new Label();
      mv.visitLabel(l2);
      mv.visitLineNumber(8, l2);
      mv.visitInsn(RETURN);
      Label l3 = new Label();
      mv.visitLabel(l3);
      mv.visitLocalVariable("this", "LExample;", null, l0, l3, 0);
      mv.visitLocalVariable("n", "I", null, l0, l3, 1);
      mv.visitMaxs(2, 2);
      mv.visitEnd();
    }
    {
      mv = cw.visitMethod(0, "doMath", "(I)LTaintContainer;", "(I)LTaintContainer<Ljava/lang/Integer;>;", null);
      mv.visitCode();
      Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(11, l0);
      mv.visitVarInsn(ILOAD, 1);
      mv.visitFieldInsn(GETSTATIC, "Example", "val", "I");
      mv.visitInsn(IADD);
      mv.visitVarInsn(ISTORE, 2);
      mv.visitVarInsn(ILOAD, 2);
      Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLineNumber(12, l1);
      mv.visitTypeInsn(NEW, "TaintContainer");
      mv.visitInsn(SWAP);
      mv.visitInsn(DUP2);
      mv.visitInsn(POP);
      mv.visitInsn(SWAP);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(SWAP);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
      mv.visitMethodInsn(INVOKESPECIAL, "TaintContainer", "<init>", "(ILjava/lang/Object;)V", false);
      mv.visitInsn(ARETURN);
      Label l2 = new Label();
      mv.visitLabel(l2);
      mv.visitLocalVariable("this", "LExample;", null, l0, l2, 0);
      mv.visitLocalVariable("in", "I", null, l0, l2, 1);
      mv.visitLocalVariable("ret", "I", null, l1, l2, 2);
      mv.visitMaxs(4, 3);
      mv.visitEnd();
    }
    cw.visitEnd();

    return cw.toByteArray();
  }
}
