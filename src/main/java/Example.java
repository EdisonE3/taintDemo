public class Example {
  static int val;
  static int val_tag;
  byte[] bArray;
  int[] bArray_tag;

  public static void main(String[] args) {

  }
  Example(int n) {
    this.bArray = new byte[n];
    this.bArray_tag = new int[n];
  }

  TaintContainer<Integer> doMath(int in) {
    int ret = in + val;
    return new TaintContainer(0, ret);
  }
}
