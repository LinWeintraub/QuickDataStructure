package il.co.lird.FS133.Projects.QuickDataStructure;

public interface IQuickDataStructure<T> {
    void push(T element);
    T pop();
    T getMaxElement();
}
