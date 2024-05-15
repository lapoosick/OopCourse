package ru.academits.orlov.arraylist;

import java.util.*;

public class CustomArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;
    private int modCount;

    public CustomArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Вместимость не может быть меньше 0. Передано значение: " + initialCapacity);
        }

        //noinspection unchecked
        elements = (E[]) new Object[initialCapacity];
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            elements = Arrays.copyOf(elements, minCapacity);
        }
    }

    public void trimToSize() {
        if (size < elements.length) {
            //noinspection unchecked
            elements = (size == 0) ? (E[]) new Object[]{} : Arrays.copyOf(elements, size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Передана пустая ссылка на массив.");
        }

        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, size);

        return a;
    }

    @Override
    public boolean add(E element) {
        add(size, element);

        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index, size);

        if (size >= elements.length) {
            elements = Arrays.copyOf(elements, elements.length == 0 ? DEFAULT_CAPACITY : elements.length * 2);
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        ++size;
        ++modCount;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index, size);

        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка на коллекцию.");
        }

        if (c.isEmpty()) {
            return false;
        }

        int collectionSize = c.size();
        ensureCapacity(size + collectionSize);

        if (index == size) {
            for (E element : c) {
                add(element);
            }
        } else {
            System.arraycopy(elements, index, elements, index + collectionSize, size - index);
            size += collectionSize;

            for (E element : c) {
                set(index, element);
                ++index;
            }
        }

        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return removeElements(c, false);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return removeElements(c, true);
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(elements, null);

        size = 0;
        ++modCount;
    }

    @Override
    public E get(int index) {
        checkIndex(index, size - 1);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index, size - 1);

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, size - 1);

        E removedElement = elements[index];

        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }

        elements[size - 1] = null;
        --size;
        ++modCount;

        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomArrayList<?> list = (CustomArrayList<?>) o;

        return size == list.size && Arrays.equals(elements, list.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        stringBuilder.append(elements[0]);

        for (int i = 1; i < size; i++) {
            stringBuilder.append(", ").append(elements[i]);
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    private boolean removeElements(Collection<?> c, boolean isRetaining) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка на коллекцию.");
        }

        if (c.isEmpty()) {
            if (!isRetaining) {
                return false;
            }

            clear();

            return true;
        }

        int newSize = 0;

        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i]) != isRetaining) {
                continue;
            }

            elements[newSize] = elements[i];
            newSize++;
        }

        ++modCount;
        size = newSize;

        return true;
    }

    private static void checkIndex(int index, int maxIndex) {
        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы границ допустимых значений индекса [0, " + maxIndex + "]");
        }
    }

    private class CustomIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменён во время итерирования.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Список закончился.");
            }

            ++currentIndex;

            return elements[currentIndex];
        }
    }
}
