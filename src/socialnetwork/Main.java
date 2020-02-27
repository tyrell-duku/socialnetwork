package socialnetwork;

import socialnetwork.domain.datastructures.LinkedList;
import socialnetwork.domain.implementations.FirstBacklog;
import socialnetwork.domain.implementations.SocialNetwork;

public class Main {

  static SocialNetwork s = new SocialNetwork(new FirstBacklog());

  public static void main(String[] args) {
    LinkedList<Integer> l = new LinkedList<>();
    l.add(3);
    l.add(7);
    l.add(56);
    l.remove(7);
    System.out.println(l.toList());
  }
}
