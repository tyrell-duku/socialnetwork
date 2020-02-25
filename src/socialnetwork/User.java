package socialnetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class User extends Thread {

  private static final AtomicInteger nextId = new AtomicInteger(0);

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
    int choice = r.nextInt(0);
    switch (choice) {
      case 0:
        socialNetwork.getAllUsers();
        break;
      case 1:
        int numberOfUsers = socialNetwork.getAllUsers().size();
        List<User> recipients = new ArrayList<>();
        for (int i = 0; i < numberOfUsers; i++) {

        }
    }
  }
}
