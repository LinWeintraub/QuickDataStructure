package il.co.lird.FS133.Projects.QuickDataStructure;

import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

public class QuickPopDataStructure<T> implements Iterable<T>, IQuickDataStructure<T> {
    private Node<T> head;
    private T maxElement;
    private Comparator<T> comparator;
    private ReentrantLock lock;

    public QuickPopDataStructure() {
        this.head = null;
        this.maxElement = null;
        this.comparator = null;
        this.lock = new ReentrantLock();
    }

    public QuickPopDataStructure(Comparator<T> comparator) {
        this();
        this.comparator = comparator;
    }

    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);

        lock.lock();
        try {
            if (head == null) {
                insertAtHead(newNode, element);
            } else {
                insertElement(newNode, element);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T pop() {
        lock.lock();
        try {
            if (head == null) {
                return null;
            }

            T result = head.data;
            head = head.next;

            return result;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T getMaxElement() {
        if (head == null) {
            return null;
        }

        return maxElement;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImp();
    }

    private void insertAtHead(Node<T> newNode, T element) {
        newNode.next = head;
        head = newNode;
        maxElement = element;
    }

    private void insertAfterMax(Node<T> newNode, T element) {
        Node<T> current = head;

        while (current.next != null) {
            if (comparator != null) {
                if (comparator.compare(element, current.next.data) > 0) {
                    break;
                }
            } else {
                if (((Comparable<T>) element).compareTo(current.next.data) > 0) {
                    break;
                }
            }

            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    private void insertComparable(Node<T> newNode, T element) {
        if (((Comparable<T>) element).compareTo(maxElement) > 0) {
            insertAtHead(newNode, element);
        } else {
            insertAfterMax(newNode, element);
        }
    }

    private void insertComparator(Node<T> newNode, T element) {
        if (comparator.compare(element, maxElement) > 0) {
            insertAtHead(newNode, element);
        } else {
            insertAfterMax(newNode, element);
        }
    }

    private void insertElement(Node<T> newNode, T element) {
        if (comparator != null) {
            insertComparator(newNode, element);
        } else {
            insertComparable(newNode, element);
        }
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T value) {
            this.data = value;
            this.next = null;
        }
    }

    private class IteratorImp implements Iterator<T> {
        private Node<T> curr;

        public IteratorImp() {
            this.curr = head;
        }

        @Override
        public boolean hasNext() {
            return (curr.next != null);
        }

        @Override
        public T next() {
            T curr_data = this.curr.data;
            this.curr = this.curr.next;

            return curr_data;
        }
    }
}
