public class Example {
  static int val;
  byte[] bArray;

  Example(int n){
    bArray = new byte[n];
  }

  int doMath(int in){
    int ret = in + val;

    return ret;
  }
}
