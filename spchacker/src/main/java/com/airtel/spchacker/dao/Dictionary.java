package com.airtel.spchacker.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.airtel.spchacker.dto.Bucket;
import com.airtel.spchacker.dto.Node;

@Service
public class Dictionary {
	 
     public boolean get(String in) { 
         Node next = DataBase.first;
         while (next != null) {
             if (next.word.equals(in)) {
                 return true;
             }
             next = next.next;
         }
         return false;
     }
     public boolean contains(String input) {
	        input = input.toLowerCase();
	        return DataBase.array[hash(input)].get(input);
	    }
     public void put(String key) {
         for (Node curr = DataBase.first; curr != null; curr = curr.next) {
             if (key.equals(curr.word)) {
                 return;              
             }
         }
         DataBase.first = new Node(key, DataBase.first);
     }
     public void build(String filePath) {
	        try {
	           // BufferedReader reader = new BufferedReader(new FileReader(filePath));
	            String line = "I AM THE BEST TEST GOOD";
	            String[] ip = line.split(" ");
	            for(String st : ip) {
	                add(st);
	            }
	        } catch (Exception ioe) {
	            ioe.printStackTrace();
	        }

	    }
     
     public void add(String key) {
    	 key = key.toLowerCase();
    	 DataBase.array[hash(key)].put(key);
	    }
     private int hash(String key) {
	        return (key.hashCode() & 0x7fffffff) % DataBase.M;
	    }
     
}
