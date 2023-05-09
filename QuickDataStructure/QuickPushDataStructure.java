package il.co.lird.FS133.Projects.QuickDataStructure;

import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

public class QuickPushDataStructure<T> implements Iterable<T>, IQuickDataStructure<T> {
    private Node<T> head;
    private Comparator<T> comparator;
    private ReentrantLock lock;

    public QuickPushDataStructure() {
        this.head = null;
        this.comparator = null;
        this.lock = new ReentrantLock();
    }

    public QuickPushDataStructure(Comparator<T> comparator) {
        this();
        this.comparator = comparator;
    }

    @Override
    public void push(T element) {
        lock.lock();
        try {
            Node<T> newNode = new Node<>(element);

            if (head == null) {
                head = newNode;
            } else {
                newNode.next = head;
                head = newNode;
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

            T maxElement = getMaxElement();

            Node<T> curr = head;
            Node<T> prev = null;

            while (curr != null && !curr.data.equals(maxElement)) {
                prev = curr;
                curr = curr.next;
            }

            if (prev == null) {
                head = head.next;
            } else {
                prev.next = curr.next;
            }

            return maxElement;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T getMaxElement() {
        if (comparator == null) {
            return findMaxComparable();
        } else {
            return findMaxComparator();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImp();
    }

    private T findMaxComparable() {
        T max = head.data;
        Node<T> curr = head.next;
        while (curr != null) {
            if (((Comparable<T>) curr.data).compareTo(max) > 0) {
                max = curr.data;
            }
            curr = curr.next;
        }
        return max;
    }

    private T findMaxComparator() {
        T max = head.data;
        Node<T> curr = head.next;
        while (curr != null) {
            if (comparator.compare(curr.data, max) > 0) {
                max = curr.data;
            }
            curr = curr.next;
        }
        return max;
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
            return (curr != null);
        }

        @Override
        public T next() {
            T curr_data = this.curr.data;
            this.curr = this.curr.next;

            return curr_data;
        }
    }
}