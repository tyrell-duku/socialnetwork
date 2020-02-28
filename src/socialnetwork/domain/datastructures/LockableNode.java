package socialnetwork.domain.datastructures;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockableNode<T> {

  private final T item;
  private final int key;
  private LockableNode<T> next;
  private final Lock l = new ReentrantLock();

  public LockableNode(T item, int key, LockableNode<T> next) {
    this.item = item;
    this.key = key;
    this.next = next;
  }

  public void lockNode() {
    l.lock();
  }

  public void unlockNode() {
    l.unlock();
  }

  public LockableNode<T> getNext() {
    return this.next;
  }

  public T getItem() {
    return item;
  }

  public int getKey() {
    return key;
  }

  public synchronized void setNext(LockableNode<T> next) {
    this.next = next;
  }
}
