package socialnetwork;

import socialnetwork.domain.datastructures.LinkedList;

public class Main {

  public static void main(String[] args) {
    LinkedList<Integer> l = new LinkedList<>();
    l.add(56);
    l.add(2);
    l.add(5);
    System.out.println(l.peek());
    System.out.println(l.toList());
    System.out.println(l.size());
    System.out.println(l.peek());
  }
}
