package socialnetwork.domain.implementations.fine;

import java.util.Optional;
import socialnetwork.domain.datastructures.FineLinkedList;
import socialnetwork.domain.datastructures.LockableNode;
import socialnetwork.domain.implementations.Task;
import socialnetwork.domain.interfaces.Backlog;

public class FineBacklog implements Backlog<Task> {

  private FineLinkedList<Task> backlog;

  public FineBacklog() {
    this.backlog = new FineLinkedList<>();
  }

  @Override
  public boolean add(Task task) {
    return backlog.stackAdd(task);
  }

  @Override
  public synchronized Optional<Task> getNextTaskToProcess() {
    if (backlog.size().get() == 0) {
      return Optional.empty();
    }
    LockableNode<Task> t = backlog.peek();
    t.lockNode();
    Task task = t.getItem();
    t.unlockNode();
    backlog.remove(t.getKey());
    return Optional.of(task);
  }

  @Override
  public int numberOfTasksInTheBacklog() {
    return backlog.size().get();
  }

  @Override
  public boolean contains(Task task) {
    return backlog.contains(new LockableNode<>(task, task.hashCode(), null));
  }
}
