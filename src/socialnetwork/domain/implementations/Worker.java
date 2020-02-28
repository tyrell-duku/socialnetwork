package socialnetwork.domain.implementations;

import java.util.Optional;
import socialnetwork.domain.implementations.Task.Command;
import socialnetwork.domain.interfaces.Backlog;
import socialnetwork.exceptions.UnknownCommandException;

public class Worker extends Thread {

  private final Backlog<Task> backlog;
  private boolean interrupted = false;

  public Worker(Backlog<Task> backlog) {
    this.backlog = backlog;
  }

  @Override
  public void run() {
    while (!interrupted) {
      Optional<Task> taskAttempt = backlog.getNextTaskToProcess();
      if (taskAttempt.isEmpty()) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          System.out.println("Could not retrieve task from backlog. Trying "
              + "again...");
        } finally {
          run();
        }
      } else {
        process(taskAttempt.get());
      }
    }
  }

  public void interrupt() {
    interrupted = true;
  }

  public void process(Task nextTask) {
    Message m = nextTask.getMessage();
    switch (nextTask.getCommand()) {
      case POST:
        nextTask.getBoard().addMessage(m);
        break;
      case DELETE:
        if (!nextTask.getBoard().deleteMessage(m)) {
          backlog.add(new Task(Command.DELETE, m, nextTask.board));
        }
        break;
      default:
        throw new UnknownCommandException("Command " + nextTask.command + " "
            + "is not supported.");
    }
  }
}
