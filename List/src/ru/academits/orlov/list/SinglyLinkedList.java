package ru.academits.orlov.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private static final String EMPTY_LIST_MESSAGE = "Список пуст.";

    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(ListItem<T> head) {
        this.head = head;
        count = 1;
    }

    public ListItem<T> getHead() {
        return head;
    }

    // получение размера списка
    public int getCount() {
        return count;
    }

    // получение значения первого элемента
    public T getFirst() {
        return head.getData();
    }

    // получение значения по указанному индексу
    public T get(int index) {
        checkItemIndex(index);

        return getItem(index).getData();
    }

    // изменение значения по указанному индексу
    public T set(int index, T data) {
        checkItemIndex(index);
        ListItem<T> currentItem = getItem(index);
        T oldData = currentItem.getData();
        currentItem.setData(data);

        return oldData;
    }

    // удаление элемента по индексу, пусть выдает значение элемента
    public T remove(int index) {
        checkItemIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        T currentItemData = getItem(index).getData();
        ListItem<T> previousItem = getItem(index - 1);
        previousItem.setNext(previousItem.getNext().getNext());
        count--;

        return currentItemData;
    }

    // вставка элемента в начало
    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    // вставка элемента по индексу
    public void add(int index, T data) {
        checkPositionIndex(index);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> newItem = new ListItem<>(data);

        if (index < count) {
            newItem.setNext(getItem(index));
        }

        getItem(index - 1).setNext(newItem);
        count++;
    }

    // удаление узла по значению, пусть выдает true, если элемент был удален
    public boolean remove(T data) {
        int index = 0;

        for (ListItem<T> item = head; item != null; item = item.getNext(), index++) {
            if (item.getData().equals(data)) {
                remove(index);

                return true;
            }
        }

        return false;
    }

    // удаление первого элемента, пусть выдает значение элемента
    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException(EMPTY_LIST_MESSAGE);
        }

        ListItem<T> deletedItem = head;
        head = head.getNext();
        count--;

        return deletedItem.getData();
    }

    // разворот списка за линейное время
    public void reverse() {
        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;
        ListItem<T> nextItem;

        while (currentItem != null) {
            nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    // копирование списка
    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();
        ListItem<T> item = head;
        int i = 0;

        while (item != null) {
            copy.add(i, item.getData());
            item = item.getNext();
            i++;
        }

        return copy;
    }


    private void checkItemIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(outOfBoundsMessage(index));
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > count)
            throw new IndexOutOfBoundsException(outOfBoundsMessage(index));
    }

    private String outOfBoundsMessage(int index) {
        return "Переданное значение индекса: " + index + ". Размер списка: " + count;
    }

    private ListItem<T> getItem(int index) {
        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    @Override
    public String toString() {
        if (head != null) {
            StringBuilder stringBuilder = new StringBuilder(head.toString());

            for (ListItem<T> item = head.getNext(); item != null; item = item.getNext()) {
                stringBuilder.append(", ").append(item);
            }

            return stringBuilder.toString();
        }

        return EMPTY_LIST_MESSAGE;
    }
}
