package com.os.process;

public class PCB{	
	private PCB parent;
	private String state;
	private PCB child;
	
	public PCB() {
		super();
		this.parent = null;
		this.child = null;
		this.state = "Dead";
	}
	public PCB getParent() {
		return parent;
	}
	public void setParent(PCB parent) {
		this.parent = parent;
	}
	public PCB getChild() {
		return child;
	}
	public void setChild(PCB child) {
		this.child = child;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}