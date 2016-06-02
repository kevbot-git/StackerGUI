package com.kevinmenhinick.stackergui;

public class PixelStackTest {
    
    public static PixelStack test;
    
    public static void main(String[] args) throws InterruptedException {
	
	test = new PixelStack(7, 10);
	
	test.push(test.startRow(3));
	test.push(false, false, true, true, false, false, false);

	printAll();
    }
    
    public static void printAll() {
	//System.out.println("Direction: " + test.getCurrentDirection());
	for(int i = 0; i < test.getStack().size(); i++) {
	    System.out.print((i) + "- ");
	    for(Boolean b : test.getStack().get(i)) {
		System.out.print(b ? "1" : "0");
	    }
	    System.out.println();
	}
	System.out.println(test.getMaxHeight() - test.getTotalHeight());
	for(Boolean b : test.getStack().get(test.getMaxHeight() - test.getTotalHeight())) {
	    System.out.print(b ? "1" : "0");
	}
    }
    
}