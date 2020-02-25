package socialnetwork;

import socialnetwork.domain.interfaces.LinkedList;
import socialnetwork.domain.interfaces.Node;

public class Main {

  public static void main(String[] args) {
    LinkedList<Integer> l = new LinkedList<>();
    l.add(2);
    l.add(78);
    l.add(99);
    l.add(100);
    l.toList();
    System.out.println(l.toList());
    System.out.println(l.size());
    System.out.println(l.contains(new Node(2, 2, null)));
    System.out.println(l.reverse());
  }
}
