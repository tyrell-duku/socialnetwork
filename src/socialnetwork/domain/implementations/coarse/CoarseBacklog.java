package socialnetwork.domain.implementations.coarse;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import socialnetwork.domain.implementations.FirstBacklog;
import socialnetwork.domain.implementations.Task;

public class CoarseBacklog extends FirstBacklog {

  private final Lock l = new ReentrantLock();

  public CoarseBacklog() {
    super();
  }

  @Override
  public boolean add(Task task) {
    try {
      l.lock();
      return super.add(task);
    } finally {
      l.unlock();
    }
  }

  @Override
  public boolean contains(Task t) {
    try {
      return super.contains(t);
    } finally {
      l.unlock();
    }
  }

}
