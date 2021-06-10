public class TaintContainer<T> {
  T value;
  int value_tag;

  public TaintContainer(int value_tag, T value){
    this.value_tag = value_tag;
    this.value = value;
  }
}
