package com.os.process;

public class PCB2 {
private int pcbid;
private int parent;
private int first_child;
private int young_sibling;
private int older_sibling;
private String state;



public PCB2(int pcbid,String state) {
	super();
	this.pcbid = pcbid;
	this.state = state;
	this.parent = -1;
	this.first_child = -1;
	this.young_sibling = -1;
	this.older_sibling = -1;
}
public PCB2(int pcbid, int parent, int first_child, int young_sibling, int older_sibling, String state) {
	super();
	this.pcbid = pcbid;
	this.parent = parent;
	this.first_child = first_child;
	this.young_sibling = young_sibling;
	this.older_sibling = older_sibling;
	this.state = state;
}
public int getPcbid() {
	return pcbid;
}
public void setPcbid(int pcbid) {
	this.pcbid = pcbid;
}
public int getParent() {
	return parent;
}
public void setParent(int parent) {
	this.parent = parent;
}
public int getFirst_child() {
	return first_child;
}
public void setFirst_child(int first_child) {
	this.first_child = first_child;
}
public int getYoung_sibling() {
	return young_sibling;
}
public void setYoung_sibling(int young_sibling) {
	this.young_sibling = young_sibling;
}
public int getOlder_sibling() {
	return older_sibling;
}
public void setOlder_sibling(int older_sibling) {
	this.older_sibling = older_sibling;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
@Override
public String toString() {
	return "PCB2 [pcbid=" + pcbid + ", parent=" + parent + ", first_child=" + first_child + ", young_sibling="
			+ young_sibling + ", older_sibling=" + older_sibling + ", state=" + state + "]";
}






}
