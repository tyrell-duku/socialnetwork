package socialnetwork.domain.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FineLinkedList<T> {

  private LockableNode<T> head;
  private AtomicInteger size;
  private final Lock l = new ReentrantLock();

  public FineLinkedList() {
    head = new LockableNode<>(null, Integer.MAX_VALUE, null);
    size = new AtomicInteger(0);
  }

  public boolean add(T t) {
    LockableNode<T> tAsNode = new LockableNode<>(t, t.hashCode(), null);
    LockableNode<T> pointer = head;
    pointer.lockNode();
    try {
      while (pointer.getNext() != null && tAsNode.getKey() <= pointer.getNext()
          .getKey()) {
        LockableNode<T> follow = pointer.getNext();
        follow.lockNode();
        pointer.unlockNode();
        pointer = follow;
      }
      if (pointer.getKey() == tAsNode.getKey()) {
        return false;
      }
      tAsNode.setNext(pointer.getNext());
      pointer.setNext(tAsNode);
      expand();
      return true;
    } finally {
      pointer.unlockNode();
    }
  }

  public boolean remove(int key) {
    LockableNode<T> pointer = head;
    try {
      while (pointer.getNext() != null && pointer.getNext().getKey() != key) {
        LockableNode<T> follow = pointer.getNext();
        follow.lockNode();
        pointer.unlockNode();
        pointer = follow;
      }
      if (pointer.getNext() == null) {
        return false;
      }
      pointer.lockNode();
      pointer.setNext(pointer.getNext().getNext());
      pointer.unlockNode();
      contract();
    } finally {
      // pointer.unlockNode();
    }
    return true;
  }


  public AtomicInteger size() {
    return size;
  }

  public LockableNode<T> peek() {
    return head.getNext();
  }

  private void expand() {
    size.getAndIncrement();
  }

  private void contract() {
    size.getAndDecrement();
  }

  public boolean contains(LockableNode<T> search) {
    if (head.getNext() == null) {
      return false;
    }
    LockableNode<T> pointer = head;
    boolean found = false;
    while (pointer.getNext() != null) {
      LockableNode<T> next = pointer.getNext();
      next.lockNode();
      pointer.unlockNode();
      pointer = next;
      next.unlockNode();
    }

    if (pointer.getKey() == search.getKey()) {
      found = true;
    }
    return found;
  }

  public List<T> toList() {
    List<T> list = new ArrayList<>();
    LockableNode<T> pointer = head.getNext();
    if (pointer == null || pointer.getNext() == null) {
      return list;
    }
    while (pointer.getNext() != null) {
      LockableNode<T> follow = pointer.getNext();
      pointer.lockNode();
      follow.lockNode();
      follow = follow.getNext();
      follow.unlockNode();
      list.add(pointer.getItem());
      pointer = pointer.getNext();
      pointer.unlockNode();
    }
    return list;
  }

  public boolean stackAdd(T value) {
    LockableNode<T> pointer = head;
    LockableNode<T> follow = pointer.getNext();
    LockableNode<T> valAsNode = new LockableNode<>(value, value.hashCode(),
        null);

    while (pointer.getNext() != null && pointer.getNext().getKey() <= value
        .hashCode()) {
      pointer.lockNode();
      pointer.unlockNode();
      pointer = pointer.getNext();
    }
    if (pointer.getKey() == value.hashCode()) {
      return false;
    }
    valAsNode.setNext(pointer.getNext());
    pointer.setNext(valAsNode);
    expand();
    return true;
  }
}
