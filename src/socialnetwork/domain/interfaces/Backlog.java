package socialnetwork.domain.interfaces;

import java.util.Optional;

public interface Backlog<T> {

  boolean add(T task);

  Optional<T> getNextTaskToProcess();

  int numberOfTasksInTheBacklog();

  boolean contains(T task);
}
