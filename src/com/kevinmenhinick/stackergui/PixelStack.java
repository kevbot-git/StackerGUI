package com.kevinmenhinick.stackergui;

import java.util.Objects;
import java.util.Random;
import java.util.Stack;

public class PixelStack {
    
    private int width;
    private int totalHeight = 0;
    private int maxHeight;
    private Shift currentDirection;
    private Stack<Boolean[]> stack;
    
    public PixelStack(int width, int maxHeight) {
	this.width = width;
	this.maxHeight = maxHeight;
	this.currentDirection = (new Random()).nextBoolean() ? Shift.LEFT : Shift.RIGHT;
	stack = new Stack<>();
	for(int i = 0; i < maxHeight - 1 ; i++) {
	    pushSilent(emptyRow());
	}
    }
    
    public PixelStack(PixelStack toCopy) {
	this(toCopy.getWidth(), toCopy.getMaxHeight());
    }
    
    public boolean hasRemainder() {
	for(int i = 0; i < width; i++) {
	    if((this.getCurrentLine()[i] == true) && this.getPreviousLine()[i]) {
		return true;
	    }
	}
	
	return false;
    }
    
    public Boolean[] remainder() {
	Boolean[] rem = new Boolean[width];
	for(int i = 0; i < width; i++) {
	    rem[i] = this.getCurrentLine()[i] && this.getPreviousLine()[i];
	}
	return rem;
    }
    
    public Boolean[] difference() {
	Boolean[] diff = new Boolean[width];
	for(int i = 0; i < width; i++) {
	    diff[i] = Objects.equals(this.getCurrentLine()[i] && true, this.getPreviousLine()[i]);
	}
	return diff;
    }
    
    public void moveRemainder(Boolean[] rem) {
	stack.pop();
	stack.push(rem);
	push(rem);
    }
    
    public Boolean[] getCurrentLine() {
	return stack.peek();
    }
    
    private Boolean[] getPreviousLine() {
	if(getTotalHeight() > 1) {
	    int height = getTotalHeight();
	    if(height > getMaxHeight()) height = getMaxHeight();
	    return stack.get(getMaxHeight() - height);
	}
	else return fullRow();
    }
    
    public void nextOscTick() {
	shiftCurrentLine(getCurrentDirection());
    }
    
    public Boolean[] shiftCurrentLine(Shift direction) {
	Boolean[] cur = getCurrentLine();
	
	if(cur[0] == true) {
	    direction = Shift.RIGHT;
	}
	else if(cur[getWidth() - 1] == true) {
	    direction = Shift.LEFT;
	}
	this.currentDirection = direction;
	
	switch(getCurrentDirection()) {
	    case LEFT:
		for(int i = 1; i < getWidth(); i++) {
		    cur[i - 1] = cur[i];
		}
		cur[getWidth() - 1] = false;
		break;
	    case RIGHT:
		for(int i = getWidth() - 1; i > 0; i--) {
		    cur[i] = cur[i - 1];
		}
		cur[0] = false;
		break;
	}
	stack.pop();
	return stack.push(cur);
    }
    
    public synchronized Boolean[] push(Boolean... line) {
	totalHeight++;
	return pushSilent(line);
    }
    
    public synchronized Boolean[] pushSilent(Boolean... line) {
	if(stack.size() >= this.maxHeight) {
	    stack.remove(stack.firstElement());
	}
	if(line.length == getWidth()) {
	    Boolean[] temp = new Boolean[getWidth()];
	    for(int i = 0; i < getWidth(); i++) {
		temp[i] = line[i];
	    }
	    stack.push(temp);
	    return line;
	}
	return null;
    }
    
    public int getTotalHeight() {
	return totalHeight;
    }

    public int getMaxHeight() {
	return maxHeight;
    }
    
    public int getWidth() {
	return width;
    }
    
    public Shift changeDirection() {
	return (currentDirection == Shift.RIGHT) ? Shift.LEFT : Shift.RIGHT;
    }
    
    public Shift getCurrentDirection() {
	return currentDirection;
    }
    
    public enum Shift {
	LEFT, RIGHT
    }
    
    protected Stack<Boolean[]> getStack() { // REMOVE
	return this.stack;
    }
    
    public Boolean[] emptyRow() {
	Boolean[] empty = new Boolean[width];
	for(int i = 0; i < width; i++) {
	    empty[i] = false;
	}
	return empty;
    }
    
    public Boolean[] fullRow() {
	Boolean[] empty = new Boolean[width];
	for(int i = 0; i < width; i++) {
	    empty[i] = true;
	}
	return empty;
    }
    
    public Boolean[] startRow(int blockWidth) {
	Boolean[] row = new Boolean[width];
	for(int i = 0; i < width; i++) {
	    row[i] = (i >= (width / 2) - (blockWidth / 2) && i <= (width / 2) + (blockWidth / 2));
	}
	return row;
    }

}