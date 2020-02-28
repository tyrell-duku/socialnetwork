package socialnetwork.domain.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class User extends Thread {

  private static final AtomicInteger nextId = new AtomicInteger(0);
  private Random r = new Random();

  protected final SocialNetwork socialNetwork;
  private final int id;
  private final String name;

  public User(String username, SocialNetwork socialNetwork) {
    this.name = username;
    this.id = User.nextId.getAndIncrement();
    this.socialNetwork = socialNetwork;
  }

  public int getUserId() {
    return id;
  }

  @Override
  public void run() {
    decideAction();
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
  }

  @Override
  public int hashCode() {
    return id;
  }

  public void decideAction() {
    Random r = new Random();
    int choice = r.nextInt(3);
    switch (choice) {
      case 0:
        socialNetwork.getAllUsers();
        break;
      case 1:
        int numberOfUsers = socialNetwork.getAllUsers().size();
        List<User> recipients = new ArrayList<>();
        for (int i = 0; i < numberOfUsers; i++) {
          recipients
              .add(SocialNetwork.getRandomUser(socialNetwork.getAllUsers()));
        }
        socialNetwork.postMessage(this, recipients,
            SocialNetwork.generateRandomMessage(10));
        break;
      default:
        socialNetwork.userBoard(this);
        List<Message> userBoard =
            socialNetwork.userBoard(this).getBoardSnapshot();
        if (!userBoard.isEmpty()) {
          for (int i = 0; i < 5; i++) { // User
            int messageIndex = r.nextInt(userBoard.size());
            socialNetwork.deleteMessage(userBoard.get(messageIndex));
          }
        }
    }
  }
}