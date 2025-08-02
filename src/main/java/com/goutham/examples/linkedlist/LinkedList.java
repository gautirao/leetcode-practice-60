package com.goutham.examples.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class LinkedList {
  public Node head;

  public LinkedList() {}

  public LinkedList(Node head) {
    this.head = head;
  }

  public void append(int data) {
    // if list is empty start with the head node
    if (head == null) {
      head = new Node(data);
      return;
    }
    // traverse till end
    Node current = head;
    while (current.next != null) {
      current = current.next;
    }
    // add a node to the end of the list
    current.next = new Node(data);
  }

  //      a ->  b ->  c-> null
  //  newhead   head
  public void prepend(int data) {
    Node newHead = new Node(data);
    newHead.next = head;
    head = newHead;
  }

  public void deleteWithValue(int data) {
    // if empty list return
    if (head == null) {
      return;
    }
    // start traversing by pointing to head node
    Node current = head;
    while (current.next != null) {
      if (current.next.data == data) {
        //      a    ->    b   --> c   --> d
        //    point b to d if c is to be deleted
        current.next = current.next.next;
        return;
      }
      // if not move to the next node
      current = current.next;
    }
  }

  public void printAll() {
    if (head == null) {
      System.out.println("List is empty");
    }
    Node current = head;
    System.out.println(current.data);
    while (current.next != null) {
      current = current.next;
      System.out.println(current.data);
    }
  }

  // works only if 1 <= position <= sizeOfList
  // i.e the position has to be in the list
  public void deleteNodeAtFromEnd(int position) {
    // fast method . Use two pointers , one fast and another slow
    // move fast pointer by n nodes ( position ) and move both incrementally until fast pointer is
    // at the end .. then break the chain at the position at slow pointer

    // eg n=2
    //  a  ->  b  ->  c  ->  d  ->  e  ->  f  -> null
    // init   slp            fp
    //  a  ->  b  ->  c  ->  d  ->  e  ->  f  -> null
    // join d with f removing e      slp            fp

    Node slowPointer = head;
    Node fastPointer = head;
    int count = 0;
    while (count < position) {
      fastPointer = fastPointer.next;
      count++;
    }

    while (fastPointer != null) {
      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next;
    }
    slowPointer.next = slowPointer.next.next;
  }

  public void reverse() {
    Node previous = null;
    Node current = head;
    Node next = head;
    //  a  ->  b  ->  c  ->  d  ->  e  ->  f  -> null
    // init   cur
    //   prev next
    while (current != null) {
      next = next.next;
      current.next = previous;
      previous = current;
      current = next;
    }
    // return the head now
    head = previous;
  }

  public List<Integer> getAllData() {
    List data = new ArrayList();
    if (head == null) {
      return data;
    }
    Node current = head;
    data.add(current.data);
    while (current.next != null) {
      current = current.next;
      data.add(current.data);
    }
    return data;
  }

  public void createCyleAtNthNode(int position) {
    // 2 -> 4 -> 5 -> 6--I
    //       ^-----------I
    // given position = 2

    Node current = head;
    Node cycleNode = null;
    int count = 1;
    while (count < position) {
      current = current.next;
      count++;
    }
    cycleNode = current;

    while (current != null && current.next != null) {
      current = current.next;
    }

    current.next = cycleNode;
  }

  // logic is evetuallly fast pointer catches up with the slow pointer
  public boolean hasCycle(Node head) {
    Node fastPointer = head, slowPointer = head;

    while ((fastPointer != null) && (fastPointer.next != null)) {
      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next.next;

      if (slowPointer == fastPointer) {
        return true;
      }
    }

    return false;
  }

  public LinkedList addTwoLists(LinkedList l1, LinkedList l2) {
    Node head1 = l1.head;
    Node head2 = l2.head;

    //  [ 1 ,5 ,7]
    //  [ 9, 1, 4]
    // op [ 0, 7, 1, 1]
    Node dummy = new Node(0);
    Node curr = dummy; // point the current to dummy
    int carry = 0; // variable used to carry over

    // traverse the nodes until l1 or l2 != null and carry forward has some value in it
    while (head1 != null || head2 != null || carry > 0) {
      int sum = 0;
      if (head1 != null) {
        sum += head1.data;
        head1 = head1.next;
      }
      if (head2 != null) {
        sum += head2.data;
        head2 = head2.next;
      }
      // add the previous carry if there is to the total
      sum += carry;

      carry = sum / 10; // this should be the value that needs to be carry forwarded

      Node newNode = new Node(sum % 10);
      curr.next = newNode;
      curr = curr.next;
    }

    return new LinkedList(dummy.next);
  }
}
