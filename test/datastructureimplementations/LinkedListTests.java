package datastructureimplementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import socialnetwork.domain.datastructures.LinkedList;
import socialnetwork.domain.datastructures.Node;

public class LinkedListTests {

  // Checks to see if size() method works correctly.
  @Test
  public void sizeTest() {
    LinkedList<Integer> l = new LinkedList<>();
    l.add(203);
    l.add(78);
    l.add(203);
    l.add(100);
    System.out.println(l.toList());
    assertEquals(3, l.size());
  }

  // Checks to see if duplicate elements are placed inside the same list
  // (which should not).
  @Test
  public void duplicateTest() {
    LinkedList<Integer> l = new LinkedList<>();
    l.add(2);
    l.add(203);
    l.add(78);
    LinkedList<Integer> dupe = new LinkedList<>();
    dupe.add(2);
    dupe.add(2);
    dupe.add(203);
    dupe.add(203);
    dupe.add(78);
    dupe.add(78);
    assertEquals(l.size(), dupe.size());
  }

  @Test
  public void removeTest() {
    LinkedList<Integer> l = new LinkedList<>();
    l.add(2);
    l.add(203);
    l.add(78);
    l.remove(2);
    assertEquals(2, l.size());

    LinkedList<String> s = new LinkedList<>();
    String st = "hey";
    s.add("hey");
    s.add("hi");
    s.remove(st.hashCode());
    assertEquals(1, s.size());
  }

  @Test
  public void containsTest() {
    LinkedList<String> s = new LinkedList<>();
    String st = "Haskell";
    String ocaml = "OCaml";
    s.add("Haskell");
    s.add("Java");
    s.add("C++");
    assertTrue(s.contains(new Node<>(st, st.hashCode(), null)));
    assertFalse(s.contains(new Node<>(ocaml, ocaml.hashCode(), null)));
  }

  @Test
  public void toListTest() {
    LinkedList<String> s = new LinkedList<>();
    String st = "Haskell";
    String st1 = "Java";
    s.add("Haskell");
    s.add("Java");
    s.add("C++");
    String[] strings = new String[]{"Haskell", "Java", "C++"};
    assertTrue(s.toList().toString().contains("Haskell"));
    assertTrue(s.toList().toString().contains("Java"));
    assertTrue(s.toList().toString().contains("C++"));
  }

}
