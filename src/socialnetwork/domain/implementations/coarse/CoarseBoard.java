package socialnetwork.domain.implementations.coarse;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import socialnetwork.domain.implementations.FirstBoard;
import socialnetwork.domain.implementations.Message;

public class CoarseBoard extends FirstBoard {

  private final Lock l = new ReentrantLock();

  public CoarseBoard() {
    super();
  }

  @Override
  public boolean addMessage(Message m) {
    try {
      l.lock();
      return super.addMessage(m);
    } finally {
      l.unlock();
    }
  }

  @Override
  public boolean deleteMessage(Message m) {
    try {
      l.lock();
      return super.deleteMessage(m);
    } finally {
      l.unlock();
    }
  }
}
