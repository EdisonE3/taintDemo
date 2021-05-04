import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.HashMap;

public class CollectMethodVisitor extends MethodVisitor {
  private final HashMap<Integer, Integer> indexMap;
  private int old_index;
  private int cur_index;

  public CollectMethodVisitor(MethodVisitor mv) {
    super(Opcodes.ASM9, mv);
    this.indexMap = new HashMap<>();
  }

  @Override
  public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
    super.visitLocalVariable(name, descriptor, signature, start, end, index);

    if (!name.equals("this")){
      cur_index++;
    }

    indexMap.put(old_index, cur_index);

    old_index++;
    cur_index++;
  }

  public HashMap<Integer, Integer> getIndexMap() {
    return indexMap;
  }
}
