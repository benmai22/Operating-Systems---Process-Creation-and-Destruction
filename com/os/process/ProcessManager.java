package com.os.process;
import java.util.ArrayList;
import java.util.List;
public class ProcessManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10; // Only one PCB
		List<PCB> processes = new ArrayList<PCB>();  // PCB's are stored as a array of n size
	    for (int i = 0; i < n; i++) {	    	
	    	processes.add(new PCB());
	    	}
	    long startTime = System.nanoTime();
	    // Child Creation
	    create(processes.get(0));
	    create(processes.get(0));
	    create(processes.get(2));
	    create(processes.get(0));

	    for (int i = 0; i < n; i++) {
		    PCB pcb  = processes.get(i);
	    	System.out.println("Process: "+i+" "+pcb);
		    while(pcb.getChild()!=null) {
		    	System.out.println(" Child: "+pcb.getChild());
		    	pcb = pcb.getChild();		    		
		    }
		    
    	}	
	    System.out.println("-------------Destroying------------");
	    destroy(processes.get(0));   
	    for (int i = 0; i < n; i++) {
		    PCB pcb  = processes.get(i);
	    	System.out.println("Process: "+i+" "+pcb);
		    while(pcb.getChild()!=null) {
		    	System.out.println(" Child: "+pcb.getChild());
		    	pcb = pcb.getChild();		    		
		    }		    
    	}	
	    
	    long endTime   = System.nanoTime();
	    long totalTimelinkedlist = endTime - startTime;
	    System.out.println("Total Time LinkedList child: "+totalTimelinkedlist);
	    
	    
	    n = 5;
		List<PCB2> processes2 = new ArrayList<PCB2>();  // PCB's are stored as a array of n size
	    for (int i = 0; i < n; i++) {
	    	if(i==0){
	    		processes2.add(new PCB2(i,"Active"));
	    	}	    		
	    	else {
	    		processes2.add(new PCB2(i,"Dead"));	
	    	}
	    	}	    
	    // 0 index reserved for single parent 
	    long startTime2 = System.nanoTime();
	    
	    // Child Creation
	    create2(processes2,0,n);
	    create2(processes2,0,n);
	    create2(processes2,2,n);
	    create2(processes2,0,n);

	    for (int i = 0; i < n; i++) {
    	System.out.println(processes2.get(i));
    	}	
	    // Destroying process 0
	    processes2 = destroy2(processes2,0,n);	  
	    for (int i = 0; i < n; i++) {
    	System.out.println(processes2.get(i));
    	}	
	    
	    long endTime2   = System.nanoTime();
	    long totalTimearray = endTime2 - startTime2;
	    System.out.println("Total Time Array: "+totalTimearray);
	    System.out.println("Reduced Time~= "+((totalTimearray-totalTimelinkedlist)/totalTimearray)*100+" %");
	    
	    	    
	}	
	
	
	static void create(PCB node) {	
		//System.out.println(node);
		PCB p  = node;
		while(p!=null && p.getChild()!=null) {
			p = p.getChild();
		}		
		PCB child = new PCB();		
		child.setParent(p);
		child.setState("Active");
		child.setChild(null);
		p.setChild(child);
	  }
	
	static List<PCB> destroy(PCB p) {		
		if(p==null) {
			return null;	
		}
		if(p!=null && p.getChild()!=null) {
			destroy(p.getChild());	
		}
		
		p.setChild(null);
		p.setParent(null);
		p.setState("Dead");
		return null;
	  }	
	
	static int getFreePCB(List<PCB2> processes,int n) {
		int i;
	    for (i = 0; i < n; i++) {
	    	if(processes.get(i).getState()=="Dead") {
	    		processes.get(i).setState("Active");
	    		return i;	
	    	}	    	
	    }	 
	    return -1;
	  }
	
	static void create2(List<PCB2> processes,int p,int n) {		
		PCB2 parent = processes.get(p);
		int freeindex = getFreePCB(processes,n);	
		PCB2 child = processes.get(freeindex);						
		
		if(parent.getFirst_child()==-1) {
			parent.setFirst_child(freeindex);				
		}else if(parent.getYoung_sibling()==-1) {
			parent.setYoung_sibling(freeindex);	
		}else if(parent.getOlder_sibling()==-1) {
			parent.setOlder_sibling(freeindex);	
		}
		else {
			System.out.println("Child limit Exceeded!");
			return;
		}
		child.setParent(p);

	  }
	
	static List<PCB2> destroy2(List<PCB2> processes,int p,int n) {	
		PCB2 pcb = processes.get(p);
		if(pcb.getFirst_child()>=0) {
			destroy2(processes,pcb.getFirst_child(),n);	
		}
		if(pcb.getYoung_sibling()>=0) {
			destroy2(processes,pcb.getYoung_sibling(),n);	
		}
		if(pcb.getOlder_sibling()>=0) {
			destroy2(processes,pcb.getOlder_sibling(),n);	
		}	
		pcb.setState("Dead");				
		pcb.setFirst_child(-1);
		pcb.setParent(-1);
		pcb.setYoung_sibling(-1);
		pcb.setOlder_sibling(-1);
		return processes;
	  }	
}
