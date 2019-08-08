package com.airtel.spchacker.dto;

public class Node {

	public String word;
    public Node next;

    public Node(String key, Node next) {
        this.word = key;
        this.next = next;
    }

}

