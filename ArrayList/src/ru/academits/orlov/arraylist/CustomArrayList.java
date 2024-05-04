package ru.academits.orlov.arraylist;

import java.util.*;

public class CustomArrayList<E> implements List<E> {
    private E[] elements;
    private int size;
    private int modCount;

    public CustomArrayList() {
        //noinspection unchecked
        elements = (E[]) new Object[10];
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

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }

        Object[] objectsArray = toArray();
        System.arraycopy(elements, 0, objectsArray, 0, size);

        return (T[]) objectsArray;
    }

    @Override
    public boolean add(E e) {
        if (size >= elements.length) {
            increaseCapacity();
        }

        elements[size] = e;
        ++size;
        ++modCount;

        return true;
    }

    @Override
    public void add(int index, E element) {
        checkElementIndex(index, size);

        if (size >= elements.length) {
            increaseCapacity();
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
        return addCollection(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return addCollection(index, c);
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
        size = 0;
        ++modCount;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index, size - 1);

        return getElement(index);
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index, size);

        E oldValue = getElement(index);
        elements[index] = element;

        return oldValue;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index, size);

        E oldValue = getElement(index);

        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }

        elements[size - 1] = null;
        --size;
        ++modCount;

        return oldValue;
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
        return List.of();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomArrayList<?> another = (CustomArrayList<?>) o;

        return size == another.size && modCount == another.modCount && Objects.deepEquals(elements, another.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(elements), size, modCount);
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

    private E getElement(int index) {
        return elements[index];
    }

    private void increaseCapacity() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    private boolean addCollection(int index, Collection<? extends E> collection) {
        if (index != size) {
            checkElementIndex(index, size);
        }

        if (collection == null || collection.isEmpty()) {
            return false;
        }

        int collectionSize = collection.size();
        ensureCapacity(size + collectionSize);

        //noinspection unchecked
        E[] esArray = (E[]) new Object[collectionSize];

        if (index == size) {
            System.arraycopy(collection.toArray(esArray), 0, elements, size, collectionSize);
        } else {
            System.arraycopy(elements, index, elements, index + collectionSize, size - index);
            System.arraycopy(collection.toArray(esArray), 0, elements, index, collectionSize);
        }

        size += collectionSize;
        ++modCount;

        return true;
    }

    private boolean removeElements(Collection<?> c, boolean isRetaining) {
        if (c == null || c.isEmpty()) {
            return false;
        }

        int currentIndex = 0;

        while (true) {
            if (currentIndex == size) {
                return false;
            }

            if (c.contains(elements[currentIndex]) != isRetaining) {
                break;
            }

            ++currentIndex;
        }

        int newSize = currentIndex++;

        while (currentIndex < size) {
            if (c.contains(elements[currentIndex]) == isRetaining) {
                elements[newSize++] = elements[currentIndex];
            }

            ++currentIndex;
        }

        ++modCount;
        size = newSize;

        return true;
    }

    private static void checkElementIndex(int index, int maxIndex) {
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

            if (currentIndex + 1 >= size) {
                throw new NoSuchElementException("Список закончился.");
            }

            ++currentIndex;

            return elements[currentIndex];
        }
    }
}
