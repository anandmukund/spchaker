package com.airtel.spchacker;


public class SortingDemo {

	private SortingDemo() {
		
	}
	public static SortingDemo sortingDemo = null;
	
	
	public static SortingDemo getInstance() {
	
		
		if(sortingDemo == null) {
			sortingDemo = new SortingDemo();
		}
		
		return sortingDemo;
	}
}
