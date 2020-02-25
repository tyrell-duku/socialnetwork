package socialnetwork.domain.interfaces;

import java.util.Optional;
import java.util.spi.LocaleNameProvider;
import socialnetwork.domain.Task;

public class FirstBacklog implements Backlog<Task> {

  private LinkedList<Task> backlog;

  public FirstBacklog() {
    backlog = new LinkedList<Task>();
  }

  @Override
  public boolean add(Task task) {
    return backlog.add(task);
  }

  @Override
  public Optional<Task> getNextTaskToProcess() {
    if (backlog.size() == 0) {
      return Optional.empty();
    }
    Task t = backlog.poll().getItem();
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
