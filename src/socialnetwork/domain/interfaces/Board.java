package socialnetwork.domain.interfaces;

import java.util.List;
import socialnetwork.domain.Message;

public interface Board {

  boolean addMessage(Message message);

  boolean deleteMessage(Message message);

  int size();

  List<Message> getBoardSnapshot();
}
