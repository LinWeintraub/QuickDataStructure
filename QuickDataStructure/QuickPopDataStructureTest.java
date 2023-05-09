package il.co.lird.FS133.Projects.QuickDataStructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;
import java.util.Iterator;

public class QuickPopDataStructureTest {
    @Test
    public void testPushPop() {
        QuickPopDataStructure<Integer> quickPopDataStructure = new QuickPopDataStructure<>();

        quickPopDataStructure.push(3);
        quickPopDataStructure.push(1);
        quickPopDataStructure.push(4);
        quickPopDataStructure.push(1);
        quickPopDataStructure.push(5);

        assertEquals(Integer.valueOf(5), quickPopDataStructure.pop());
        assertEquals(Integer.valueOf(4), quickPopDataStructure.pop());
        assertEquals(Integer.valueOf(3), quickPopDataStructure.pop());
        assertEquals(Integer.valueOf(1), quickPopDataStructure.pop());
        assertEquals(Integer.valueOf(1), quickPopDataStructure.pop());
        assertNull(quickPopDataStructure.pop());
    }

    @Test
    public void testGetMaxElement() {
        QuickPopDataStructure<Integer> quickPopDataStructure = new QuickPopDataStructure<>();

        quickPopDataStructure.push(3);
        quickPopDataStructure.push(1);
        quickPopDataStructure.push(4);
        quickPopDataStructure.push(1);
        quickPopDataStructure.push(5);

        assertEquals(Integer.valueOf(5), quickPopDataStructure.getMaxElement());
        quickPopDataStructure.pop();

        assertEquals(Integer.valueOf(4), quickPopDataStructure.getMaxElement());
        quickPopDataStructure.pop(); //4

        assertEquals(Integer.valueOf(3), quickPopDataStructure.getMaxElement());
        quickPopDataStructure.pop(); //3

        assertEquals(Integer.valueOf(1), quickPopDataStructure.getMaxElement());
        quickPopDataStructure.pop(); //1

        assertEquals(Integer.valueOf(1), quickPopDataStructure.getMaxElement());
        quickPopDataStructure.pop(); //1
    }

    @Test
    public void testComparator() {
        Comparator<Person> comparator = Comparator.comparing(Person::getAge);
        QuickPopDataStructure<Person> quickPopDataStructure = new QuickPopDataStructure<>(comparator);

        Person p1 = new Person("Lin", 29);
        Person p2 = new Person("Racheli", 25);
        Person p3 = new Person("Shani", 32);
        Person p4 = new Person("Moshe", 27);

        quickPopDataStructure.push(p1);
        quickPopDataStructure.push(p2);
        quickPopDataStructure.push(p3);
        quickPopDataStructure.push(p4);

        assertEquals("Shani", quickPopDataStructure.pop().getName());
        assertEquals("Lin", quickPopDataStructure.pop().getName());
        assertEquals("Moshe", quickPopDataStructure.pop().getName());
        assertEquals("Racheli", quickPopDataStructure.pop().getName());
        assertNull(quickPopDataStructure.pop());
    }

    @Test
    public void testIterator() {
        QuickPopDataStructure<Integer> quickPopDataStructure = new QuickPopDataStructure<>();
        quickPopDataStructure.push(3);
        quickPopDataStructure.push(1);
        quickPopDataStructure.push(4);
        quickPopDataStructure.push(2);
        quickPopDataStructure.push(5);

        Iterator<Integer> it = quickPopDataStructure.iterator();

        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(5), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(4), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(3), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(2), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());
        assertFalse(it.hasNext());
    }

    public class Person implements Comparable<Person>, Comparator<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(Person other) {
            return this.name.compareTo(other.getName());
        }

        @Override
        public int compare(Person p1, Person p2) {
            return Integer.compare(p1.getAge(), p2.getAge());
        }
    }
}

