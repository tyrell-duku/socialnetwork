package socialnetwork.domain.implementations;

import java.util.List;
import socialnetwork.domain.datastructures.LinkedList;
import socialnetwork.domain.interfaces.Board;

public class FirstBoard implements Board {

  private LinkedList<Message> messages;

  public FirstBoard() {
    messages = new LinkedList<>();
  }

  @Override
  public synchronized boolean addMessage(Message message) {
    return messages.add(message);
  }

  @Override
  public synchronized boolean deleteMessage(Message message) {
    return messages.remove(message.getMessageId());
  }

  @Override
  public int size() {
    return messages.size();
  }

  @Override
  public List<Message> getBoardSnapshot() {
    return messages.toList();
  }
}
