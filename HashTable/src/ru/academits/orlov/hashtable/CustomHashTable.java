package ru.academits.orlov.hashtable;

import java.util.*;

public class CustomHashTable<E> implements Collection<E> {
    private static final int DEFAULT_TABLE_LENGTH = 100;

    private final List<E>[] lists;
    private int modCount;

    public CustomHashTable(int length) {
        //noinspection unchecked
        lists = (List<E>[]) new List[length];
    }

    public CustomHashTable() {
        //noinspection unchecked
        lists = (List<E>[]) new List[DEFAULT_TABLE_LENGTH];
    }

    @Override
    public int size() {
        int size = 0;

        for (List<E> list : lists) {
            if (list != null) {
                size += list.size();
            }
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return false;
        }

        //noinspection unchecked
        int listIndex = getElementHashcode((E) o);

        if (lists[listIndex] == null) {
            return false;
        }

        return lists[listIndex].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Iterator<E> iterator = iterator();

        for (int i = 0; iterator.hasNext(); i++) {
            array[i] = iterator.next();
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Передана пустая ссылка на массив.");
        }

        //noinspection unchecked
        a = (T[]) toArray();

        return a;
    }

    @Override
    public boolean add(E e) {
        int listIndex = getElementHashcode(e);

        if (lists[listIndex] == null) {
            lists[listIndex] = new ArrayList<>();
        }

        lists[listIndex].add(e);
        ++modCount;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        //noinspection unchecked
        int listIndex = getElementHashcode((E) o);
        List<E> list = lists[listIndex];

        if (list == null) {
            return false;
        }

        boolean isRemoved = list.remove(o);

        if (isRemoved) {
            ++modCount;
        }

        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            //noinspection unchecked
            int listIndex = getElementHashcode((E) element);

            //noinspection unchecked
            if (lists[listIndex] == null || !lists[listIndex].contains((E) element)) {
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

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        //noinspection unchecked
        return removeElements((Collection<E>) c, false);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        //noinspection unchecked
        return removeElements((Collection<E>) c, true);
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (List<E> list : lists) {
            if (list != null) {
                list.retainAll(new ArrayList<E>());
            }
        }

        ++modCount;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        Iterator<E> iterator = new CustomIterator();

        stringBuilder.append(iterator.next());

        while (iterator.hasNext()) {
            stringBuilder.append(", ").append(iterator.next());
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    private int getElementHashcode(E e) {
        return e == null ? 0 : Math.abs(e.hashCode() % lists.length);
    }

    private boolean removeElements(Collection<E> c, boolean isRetaining) {
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

        int initialSize = size();

        for (List<E> list : lists) {
            if (list != null) {
                if (isRetaining) {
                    list.retainAll(c);
                } else {
                    list.removeAll(c);
                }
            }
        }

        return initialSize != size();
    }

    private class CustomIterator implements Iterator<E> {
        private final int currentModCount = modCount;
        private final int elementsCount = size();

        private int currentTableIndex = 0;
        private int currentElementIndex = 0;
        private int nextElementNumber = 1;

        @Override
        public boolean hasNext() {
            return nextElementNumber <= elementsCount;
        }

        @Override
        public E next() {
            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("Таблица была изменена во время итерирования.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("В таблице больше нет элементов.");
            }

            while (true) {
                if (lists[currentTableIndex] == null || lists[currentTableIndex].isEmpty()) {
                    currentTableIndex++;
                    continue;
                }

                int lastElementIndex = lists[currentTableIndex].size() - 1;
                int previousElementIndex = currentElementIndex;
                int previousTableIndex = currentTableIndex;

                if (currentElementIndex < lastElementIndex) {
                    currentElementIndex++;
                } else {
                    currentElementIndex = 0;
                    currentTableIndex++;
                }

                nextElementNumber++;

                return lists[previousTableIndex].get(previousElementIndex);
            }
        }
    }
}
