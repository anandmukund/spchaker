package com.airtel.spchacker.dto;

public class Bucket {

	 private static  Node first;

     public boolean get(String in) {   
         Node next = first;
         while (next != null) {
             if (next.word.equals(in)) {
                 return true;
             }
             next = next.next;
         }
         return false;
     }

     public void put(String key) {
         for (Node curr = first; curr != null; curr = curr.next) {
             if (key.equals(curr.word)) {
                 return;                   
             }
         }
         first = new Node(key, first);
     }
}
