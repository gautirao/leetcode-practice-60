package com.goutham.examples;

import static org.junit.jupiter.api.Assertions.*;

import com.goutham.examples.linkedlist.LinkedList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListTests {
    private LinkedList list ;

    @BeforeEach
    public void doEach(){
        list = new LinkedList();
    }
    @Test
    public void testAppend() {
    list.append(1);
    list.append(2);
   List expected = Arrays.asList(1,2);
   List actual = list.getAllData();
   assertEquals(expected,actual);

    }
    @Test
    public void testPrepend() {
        list.prepend(1);
        list.prepend(2);
        List expected = Arrays.asList(2,1);
        List actual = list.getAllData();
        assertEquals(expected,actual);

    }
    @Test
    public void testPrependFail() {
        list.prepend(1);
        list.prepend(2);
        List expected = Arrays.asList(1,2);
        List actual = list.getAllData();
        assertNotEquals(expected,actual);

    }

    @Test
    public void testDeleteAtNthNodeFromEnd() {
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.deleteNodeAtFromEnd(2);
        List expected = Arrays.asList(1,2,3,5);
        List actual = list.getAllData();
        assertNotEquals(expected,actual);

    }
    @Test
    public void testReversal() {
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.reverse();
        List expected = Arrays.asList(5,4,3,2,1);
        List actual = list.getAllData();
        list.printAll();
        assertEquals(expected,actual);

    }


    @Test
    public void testCycle(){
        // 2 -> 4 -> 5 -> 6--I
        //       ^-----------I



        LinkedList list =new LinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);
        list.createCyleAtNthNode(2);

        assertTrue(list.hasCycle(list.head));


    }
    @Test
    public void testCycleNegativeScenario(){
        // 2 -> 4 -> 5 -> 6

        LinkedList list =new LinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);

        assertFalse(list.hasCycle(list.head));

    }

    @Test
    public void addTwoLists(){
        LinkedList list1 =new LinkedList();
        list1.append(1);
        list1.append(2);
        list1.append(3);

        LinkedList list2 =new LinkedList();
        list2.append(9);
        list2.append(2);
        list2.append(7);

        List expected = Arrays.asList(0,5,0,1);
        LinkedList result = list1.addTwoLists(list1, list2);
        result.getAllData().forEach(System.out::println);
        assertEquals(result.getAllData(),expected);
    }

}
