import org.objectweb.asm.Type;

public class MethodModify {
  public static String modifyDesc(String desc){
    StringBuilder new_args = new StringBuilder();
    String new_desc;
    for (Type argumentType : Type.getArgumentTypes(desc)) {
      if (argumentType.toString().length() == 1){

      }
      new_args.append(argumentType.toString());
    }
    new_desc = "(" + new_args.toString() + ")" + Type.getReturnType(desc);

    return new_desc;
  }
}
