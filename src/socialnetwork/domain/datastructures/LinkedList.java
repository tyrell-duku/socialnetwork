package socialnetwork.domain.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedList<T> {

  private Node<T> headNode;
  private int size;

  public LinkedList() {
    headNode = new Node<T>(null, Integer.MIN_VALUE, null);
    size = 0;
  }

  public boolean add(T t) {
    Node<T> tAsNode = new Node<>(t, t.hashCode(), null);
    if (contains(tAsNode)) {
      return false;
    }
    Node<T> pointer = headNode;
    while (tAsNode.getKey() > pointer.getKey()
        && pointer.getNext() != null) {
      pointer = pointer.getNext();
    }
    tAsNode.setNext(pointer.getNext());
    pointer.setNext(tAsNode);

    size++;
    return true;
  }

  public boolean remove(int key) {
    if (headNode.getNext() == null && key != headNode.getKey()) {
      throw new NoSuchElementException("No elements inside an empty list.");
    }
    Node<T> followNode = headNode;
    Node<T> cycleThroughNode = followNode.getNext();
    while (key != cycleThroughNode.getKey()) {
      cycleThroughNode = cycleThroughNode.getNext();
      followNode = followNode.getNext();
    }
    followNode.setNext(cycleThroughNode.getNext());
    size--;
    return true;
  }

  public int size() {
    return size;
  }

  public Node<T> peek() {
    return headNode.getNext();
  }

  public Node<T> poll() {
    remove(headNode.getNext().getKey());
    return headNode.getNext();
  }

  public T item(Node<T> node) {
    return node.getItem();
  }

  public boolean contains(Node<T> search) {
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

  public List<T> toList() {
    List<T> list = new ArrayList<>();
    Node<T> pointer = headNode.getNext();
    while (pointer != null) {
      list.add(pointer.getItem());
      pointer = pointer.getNext();
    }
    return list;
  }
}
