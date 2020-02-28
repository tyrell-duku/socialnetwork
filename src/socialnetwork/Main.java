package socialnetwork;

import socialnetwork.domain.implementations.FirstBacklog;
import socialnetwork.domain.implementations.SocialNetwork;
import socialnetwork.domain.implementations.User;
import socialnetwork.domain.implementations.Worker;
import socialnetwork.domain.implementations.fine.FineBacklog;
import socialnetwork.domain.implementations.fine.FineBoard;
import socialnetwork.domain.interfaces.Backlog;
import socialnetwork.domain.interfaces.Board;

public class Main {

  static SocialNetwork s = new SocialNetwork(new FirstBacklog());

  public static void main(String[] args) {
    Backlog b = new FineBacklog();
    SocialNetwork s = new SocialNetwork(b);
    Worker a = new Worker(b);
    Worker c = new Worker(b);
    Board tBoard = new FineBoard();
    Board aBoard = new FineBoard();
    Board jBoard = new FineBoard();
    User tyrell = new User("tduku", s);
    User ayoob = new User("aahmed", s);
    User jaimi = new User("jpatel", s);


  }
}
