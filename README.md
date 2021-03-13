# Process-Creation-and-Destruction
This program simulates and compares the performance of
process creation and destruction when implemented with (version 1) and
without linked lists (version 2).

Description
Version 1 of the process creation hierarchy uses linked lists to keep track of
child processes.

For the purposes of performance evaluation, the PCBs are simplified as
follows:

• All PCBs are implemented as an array of size n.
• Each process is referred to by the PCB index, 0 through n-1.
• Each PCB is a structure consisting of only the two fields:
  o parent: a PCB index corresponding to the process's creator
  o children: a pointer to a linked list, where each list element
contains the PCB index of one child process
The necessary functions are simplified as follows:
• create(p) represents the create function executed by process PCB[p].
The function creates a new child process PCB[q] of process PCB[p] by
performing the following tasks:
  o allocate a free PCB[q]
  o record the parent's index, p, in PCB[q]
  o initialize the list of children of PCB[q] as empty
  o create a new link containing the child's index q and appends the
link to the linked list of PCB[p]
• destroy(p) represents the destroy function executed by process PCB[p].
The function recursively destroys all descendent processes (child,
grandchild, etc.) of process PCB[p] by performing the following tasks:
  o for each element q on the linked list of children of PCB[p]
    ▪ destroy(q) /* recursively destroy all progenies */
    ▪ free PCB[q]
    ▪ deallocate the element q from the linked list


Version 2 of the same process creation hierarchy uses no linked lists. Instead,
each PCB contains the 4 integer fields parent, first_child, younger_sibling, and
older_sibling.

Test Results:

TestCase(1)
Create 3 children to Process 0 and destroy Process 0.
create(processes.get(0));
create(processes.get(0));
create(processes.get(0));
destroy(processes.get(0));

Total Time LinkedList child: 2578600
Total Time Array: 999700
Reduced Time~= -100 %

TestCase(2)
Create 3 children to Process 0 and destroy Process 0.
create(processes.get(0));
create(processes.get(0));
create(processes.get(0));
destroy(processes.get(0));

Total Time LinkedList child: 5809800
Child limit Exceeded!

TestCase(3)
Create 3 children to Process 0 and destroy Process 0.
create(processes.get(0));
create(processes.get(0));
create(processes.get(2));
create(processes.get(0));

Total Time LinkedList child: 4929400
Total Time Array: 677100
Reduced Time~= -600 %
