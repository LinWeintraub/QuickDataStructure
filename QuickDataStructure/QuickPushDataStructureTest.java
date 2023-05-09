package il.co.lird.FS133.Projects.QuickDataStructure;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.Test;

public class QuickPushDataStructureTest {
    @Test
    public void testPushPop() {
        QuickPushDataStructure<Integer> quickPushDataStructure = new QuickPushDataStructure<>();

        quickPushDataStructure.push(3);
        quickPushDataStructure.push(1);
        quickPushDataStructure.push(4);
        quickPushDataStructure.push(1);
        quickPushDataStructure.push(5);

        assertEquals(Integer.valueOf(5), quickPushDataStructure.pop());
        assertEquals(Integer.valueOf(4), quickPushDataStructure.pop());
        assertEquals(Integer.valueOf(3), quickPushDataStructure.pop());
        assertEquals(Integer.valueOf(1), quickPushDataStructure.pop());
        assertEquals(Integer.valueOf(1), quickPushDataStructure.pop());
        assertNull(quickPushDataStructure.pop());
    }

    @Test
    public void testGetMaxElement() {
        QuickPushDataStructure<Integer> quickPushDataStructure = new QuickPushDataStructure<>();

        quickPushDataStructure.push(3);
        quickPushDataStructure.push(1);
        quickPushDataStructure.push(4);
        quickPushDataStructure.push(1);
        quickPushDataStructure.push(5);

        assertEquals(Integer.valueOf(5), quickPushDataStructure.getMaxElement());
        quickPushDataStructure.pop(); //5

        assertEquals(Integer.valueOf(4), quickPushDataStructure.getMaxElement());
        quickPushDataStructure.pop(); //4

        assertEquals(Integer.valueOf(3), quickPushDataStructure.getMaxElement());
        quickPushDataStructure.pop(); //3

        assertEquals(Integer.valueOf(1), quickPushDataStructure.getMaxElement());
        quickPushDataStructure.pop(); //1

        assertEquals(Integer.valueOf(1), quickPushDataStructure.getMaxElement());
        quickPushDataStructure.pop(); //1
    }

    @Test
    public void testComparator() {
        Comparator<Person> comparator = Comparator.comparing(Person::getAge);
        QuickPushDataStructure<Person> quickPushDataStructure = new QuickPushDataStructure<>(comparator);

        Person p1 = new Person("Lin", 29);
        Person p2 = new Person("Racheli", 25);
        Person p3 = new Person("Shani", 32);
        Person p4 = new Person("Moshe", 27);

        quickPushDataStructure.push(p1);
        quickPushDataStructure.push(p2);
        quickPushDataStructure.push(p3);
        quickPushDataStructure.push(p4);

        assertEquals("Shani", quickPushDataStructure.pop().getName());
        assertEquals("Lin", quickPushDataStructure.pop().getName());
        assertEquals("Moshe", quickPushDataStructure.pop().getName());
        assertEquals("Racheli", quickPushDataStructure.pop().getName());
        assertNull(quickPushDataStructure.pop());
    }

    @Test
    public void testIterator() {
        QuickPushDataStructure<Integer> quickPushDataStructure = new QuickPushDataStructure<>();
        quickPushDataStructure.push(3);
        quickPushDataStructure.push(1);
        quickPushDataStructure.push(4);
        quickPushDataStructure.push(1);
        quickPushDataStructure.push(5);

        Iterator<Integer> it = quickPushDataStructure.iterator();

        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(5), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(4), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(3), it.next());
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

