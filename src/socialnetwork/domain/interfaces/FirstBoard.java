package socialnetwork.domain.interfaces;

import java.util.Collections;
import java.util.List;
import socialnetwork.domain.Message;

public class FirstBoard implements Board {

  private LinkedList<Message> messages;

  public FirstBoard() {
    messages = new LinkedList<Message>();
  }

  @Override
  public boolean addMessage(Message message) {
    return messages.add(message);
  }

  @Override
  public boolean deleteMessage(Message message) {
    return messages.remove(message.getMessageId());
  }

  @Override
  public int size() {
    return messages.size();
  }

  @Override
  public List<Message> getBoardSnapshot() {
    return messages.reverse();
  }
}
