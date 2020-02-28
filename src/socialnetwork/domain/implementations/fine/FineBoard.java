package socialnetwork.domain.implementations.fine;

import java.util.List;
import socialnetwork.domain.datastructures.FineLinkedList;
import socialnetwork.domain.implementations.Message;
import socialnetwork.domain.interfaces.Board;

public class FineBoard implements Board {

  private FineLinkedList<Message> messages;

  public FineBoard() {
    messages = new FineLinkedList<>();
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
    return messages.size().get();
  }

  @Override
  public List<Message> getBoardSnapshot() {
    return messages.toList();
  }
}
