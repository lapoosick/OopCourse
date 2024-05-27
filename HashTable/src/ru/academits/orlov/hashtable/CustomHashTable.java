package ru.academits.orlov.hashtable;

import java.util.*;

public class CustomHashTable<E> implements Collection<E> {
    private static final int DEFAULT_HASH_TABLE_LENGTH = 100;

    private final List<E>[] lists;
    private int size;
    private int modCount;

    public CustomHashTable(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Размер массива хэш-таблицы должен быть больше 0. Передано значение: " + length);
        }

        //noinspection unchecked
        lists = (List<E>[]) new List[length];
    }

    public CustomHashTable() {
        //noinspection unchecked
        lists = (List<E>[]) new List[DEFAULT_HASH_TABLE_LENGTH];
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
        int listIndex = getElementHash(o);

        return lists[listIndex] != null && lists[listIndex].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        convertToArray(array);

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Передана пустая ссылка на массив.");
        }

        if (a.length < size) {
            //noinspection unchecked
            return (T[]) toArray();
        }

        convertToArray(a);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void convertToArray(Object[] array) {
        int i = 0;

        for (List<E> list : lists) {
            if (list != null) {
                for (E element : list) {
                    array[i] = element;
                    ++i;
                }
            }
        }
    }

    @Override
    public boolean add(E e) {
        int listIndex = getElementHash(e);

        if (lists[listIndex] == null) {
            lists[listIndex] = new ArrayList<>();
        }

        lists[listIndex].add(e);
        ++modCount;
        ++size;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        List<E> list = lists[getElementHash(o)];

        if (list != null) {
            if (list.remove(o)) {
                ++modCount;
                --size;

                return true;
            }
        }

        return false;
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
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка на коллекцию.");
        }

        if (c.isEmpty()) {
            return false;
        }

        c.forEach(this::add);

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка на коллекцию.");
        }

        if (c.isEmpty()) {
            return false;
        }

        int initialSize = size;

        for (List<E> list : lists) {
            if (list != null) {
                int listSize = list.size();
                list.removeAll(c);
                size -= listSize - list.size();
            }
        }

        if (initialSize == size) {
            return false;
        }

        ++modCount;

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка на коллекцию.");
        }

        if (c.isEmpty()) {
            clear();

            return true;
        }

        int initialSize = size;

        for (List<E> list : lists) {
            if (list != null) {
                int listSize = list.size();
                list.retainAll(c);
                size -= listSize - list.size();
            }
        }

        if (initialSize == size) {
            return false;
        }

        ++modCount;

        return true;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (List<E> list : lists) {
            if (list != null) {
                list.clear();
            }
        }

        ++modCount;
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (List<E> list : lists) {
            if (list != null) {
                for (E element : list) {
                    stringBuilder.append(element).append(", ");
                }
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    private int getElementHash(Object object) {
        return object == null ? 0 : Math.abs(object.hashCode() % lists.length);
    }

    private class CustomIterator implements Iterator<E> {
        private final int currentModCount = modCount;

        private int currentHashTableIndex;
        private int currentElementIndex;
        private int nextElementIndex = 0;

        @Override
        public boolean hasNext() {
            return nextElementIndex < size;
        }

        @Override
        public E next() {
            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("Хэш-таблица была изменена во время итерирования.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("В хэш-таблице больше нет элементов.");
            }

            while (true) {
                if (lists[currentHashTableIndex] == null || lists[currentHashTableIndex].isEmpty()) {
                    currentHashTableIndex++;
                    continue;
                }

                int previousElementIndex = currentElementIndex;
                int previousHashTableIndex = currentHashTableIndex;

                if (currentElementIndex < lists[currentHashTableIndex].size() - 1) {
                    currentElementIndex++;
                } else {
                    currentElementIndex = 0;
                    currentHashTableIndex++;
                }

                nextElementIndex++;

                return lists[previousHashTableIndex].get(previousElementIndex);
            }
        }
    }
}
