package socialnetwork.domain.datastructures;

public class Node<E> {

  private Node<E> next;
  private int key;
  private E item;

  public Node(E item, int key, Node<E> next) {
    this.item = item;
    this.key = key;
    this.next = next;
  }

  public Node<E> getNext() {
    return next;
  }

  public int getKey() {
    return key;
  }

  public E getItem() {
    return item;
  }

  public void setNext(Node<E> next) {
    this.next = next;
  }
}
