package socialnetwork.domain.implementations;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import socialnetwork.domain.datastructures.LinkedList;
import socialnetwork.domain.datastructures.Node;
import socialnetwork.domain.interfaces.Backlog;

public class FirstBacklog implements Backlog<Task> {

  private LinkedList<Task> backlog;
  private final Lock l = new ReentrantLock();

  public FirstBacklog() {
    backlog = new LinkedList<>();
  }

  @Override
  public boolean add(Task task) {
    return backlog.stackAdd(task);
  }

  @Override
  public synchronized Optional<Task> getNextTaskToProcess() {
    if (backlog.size() == 0) {
      return Optional.empty();
    }
    Task t = backlog.peek().getItem();
    backlog.remove(t.getId());
    return Optional.of(t);
  }

  @Override
  public int numberOfTasksInTheBacklog() {
    return backlog.size();
  }

  @Override
  public boolean contains(Task task) {
    return backlog.contains(new Node<>(task, task.hashCode(), null));
  }
}
