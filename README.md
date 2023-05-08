# QuickDataStructure
## Overview
This project consists of two classes: QuickPopDataStructure and QuickPushDataStructure. Both classes provide push and pop operations, but with different time complexities. The push operation of QuickPopDataStructure takes O(n) time, while the pop operation takes O(1) time. In contrast, the push operation of QuickPushDataStructure takes O(1) time, while the pop operation takes O(n) time.

Both classes have been designed to hold any type of objects, such as integers or complex objects like Person objects. However, an instance of each class can hold only one type of objects. For example, an instance of QuickPopDataStructure can hold either integers or Person objects but not both.

Both classes have also been designed to work in a multi-threaded environment where different threads may try to perform push and pop operations simultaneously.

## Algorithmic solution
### QuickPopDataStructure:
The algorithmic solution involves inserting new elements in the data structure in a way that keeps track of the maximum value. This is achieved by comparing the new element with the current maximum value and inserting it accordingly. The pop operation simply removes the head of the data structure, which should always be the maximum value.

The code implements this algorithm using a linked list with a head node, a comparator to compare elements, and a ReentrantLock to ensure thread safety.
It also provides an iterator to traverse the elements in the data structure.

### QuickPushDataStructure:
The algorithmic solution for QuickPushDataStructure involves inserting new elements at the head of the linked list. The push operation simply creates a new node and makes it the head of the list, pushing the existing nodes down. The pop operation removes the node with the maximum value, which is found by either using a comparable interface or a comparator function to compare the elements.

The code implements this algorithm using a linked list with a head node, a comparator to compare elements, and a ReentrantLock to ensure thread safety. It also provides an iterator to traverse the elements in the data structure.

## Usage
To use ```QuickPushDataStructure``` or ```QuickPopDataStructure```, you can create an instance of the class and then push elements onto the data structure using the push() method, for example:
```
QuickPushDataStructure<Integer> stack = new QuickPushDataStructure<>();
stack.push(1);
stack.push(2);
stack.push(3);
```
You can pop the elements from the data structure using the pop() method:
```
Integer poppedElement = stack.pop();
```
The pop() method returns the element that was popped from the data structure. If the data structure is empty, it returns null.

If you want to use a custom Comparator to compare the elements in the data structure, you can pass it to the constructor.

## Testing
A test application has been provided to test the requirements mentioned above. The test application tests the following:

* Push and pop operations for both classes.
* The ability to hold different types of objects.
* Pop operations returning the maximum value.
* Multithreaded environment with multiple threads performing push and pop operations simultaneously.
