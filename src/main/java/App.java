public class App {
  public static void main(String[] args) throws Exception {
    TaintModify taintModify = new TaintModify();
    taintModify.insertTaint("Example.class");
  }
}
