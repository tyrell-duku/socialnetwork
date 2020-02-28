package socialnetwork.domain.datastructures;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<T> {

  private Node<T> headNode;
  private int size;

  public LinkedList() {
    headNode = new Node<T>(null, Integer.MAX_VALUE, null);
    size = 0;
  }

  public synchronized boolean add(T t) {
    Node<T> tAsNode = new Node<>(t, t.hashCode(), null);
    if (contains(tAsNode)) {
      return false;
    }
    Node<T> pointer = headNode;
    while (pointer.getNext() != null && tAsNode.getKey() <= pointer.getNext()
        .getKey()) {
      pointer = pointer.getNext();
    }
    tAsNode.setNext(pointer.getNext());
    pointer.setNext(tAsNode);
    size++;
    return true;
  }

  public synchronized boolean remove(int key) {
    Node<T> pointer = headNode;
    while (pointer.getNext() != null && pointer.getNext().getKey() != key) {
      pointer = pointer.getNext();
    }
    if (pointer.getNext() == null) {
      return false;
    }
    pointer.setNext(pointer.getNext().getNext());
    size--;
    return true;
  }


  public int size() {
    return size;
  }

  public Node<T> peek() {
    return headNode.getNext();
  }


  public synchronized boolean contains(Node<T> search) {
    Node<T> pointer = headNode;
    boolean found = false;
    while (pointer.getNext() != null) {
      pointer = pointer.getNext();
      if (pointer.getKey() == search.getKey()) {
        found = true;
        break;
      }

    }
    return found;
  }

  public synchronized List<T> toList() {
    List<T> list = new ArrayList<>();
    Node<T> pointer = headNode.getNext();
    while (pointer != null) {
      list.add(pointer.getItem());
      pointer = pointer.getNext();
    }
    return list;
  }

  public synchronized boolean stackAdd(T value) {
    Node<T> pointer = headNode;
    Node<T> valAsNode = new Node<>(value, value.hashCode(), null);
    while (pointer.getNext() != null && pointer.getNext().getKey() <= value
        .hashCode()) {
      pointer = pointer.getNext();
    }

    if (pointer.getKey() == value.hashCode()) {
      return false;
    }
    valAsNode.setNext(pointer.getNext());
    pointer.setNext(valAsNode);
    size++;
    return true;
  }
}
