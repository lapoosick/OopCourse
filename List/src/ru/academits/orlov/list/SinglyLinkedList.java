package ru.academits.orlov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(E data) {
        head = new ListItem<>(data);
        count = 1;
    }

    // получение размера списка
    public int getCount() {
        return count;
    }

    // получение значения первого элемента
    public E getFirst() {
        throwIfEmptyList();

        return head.getData();
    }

    // получение значения по указанному индексу
    public E get(int index) {
        checkElementIndex(index, count - 1);

        return getItem(index).getData();
    }

    // изменение значения по указанному индексу, пусть выдает старое значение
    public E set(int index, E data) {
        checkElementIndex(index, count - 1);

        ListItem<E> item = getItem(index);
        E oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    // удаление элемента по индексу, пусть выдает значение элемента
    public E remove(int index) {
        checkElementIndex(index, count - 1);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<E> previousItem = getItem(index - 1);
        E removedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());
        count--;

        return removedData;
    }

    // вставка элемента в начало
    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        count++;
    }

    // вставка элемента по индексу
    public void add(int index, E data) {
        checkElementIndex(index, count);

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<E> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
        count++;
    }

    // удаление узла по значению, пусть выдает true, если элемент был удален
    public boolean remove(E data) {
        if (Objects.equals(data, head.getData())) {
            removeFirst();

            return true;
        }

        for (ListItem<E> currentItem = head.getNext(), previousItem = head; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(data, currentItem.getData())) {
                previousItem.setNext(currentItem.getNext());
                count--;

                return true;
            }
        }

        return false;
    }

    // удаление первого элемента, пусть выдает значение элемента
    public E removeFirst() {
        throwIfEmptyList();

        E removedData = head.getData();
        head = head.getNext();
        count--;

        return removedData;
    }

    // разворот списка за линейное время
    public void reverse() {
        ListItem<E> currentItem = head;
        ListItem<E> previousItem = null;

        while (currentItem != null) {
            ListItem<E> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    // копирование списка
    public SinglyLinkedList<E> copy() {
        if (head == null) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<E> copy = new SinglyLinkedList<>(head.getData());
        copy.count = count;

        for (ListItem<E> sourceItem = head.getNext(), copyItem = copy.head; sourceItem != null; sourceItem = sourceItem.getNext(), copyItem = copyItem.getNext()) {
            copyItem.setNext(new ListItem<>(sourceItem.getData()));
        }

        return copy;
    }

    private void throwIfEmptyList() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст.");
        }
    }

    private ListItem<E> getItem(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    private static void checkElementIndex(int index, int maxIndex) {
        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы границ допустимых значений индекса [0, " + maxIndex + "]");
        }
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        stringBuilder.append(head.getData());

        for (ListItem<E> item = head.getNext(); item != null; item = item.getNext()) {
            stringBuilder.append(", ").append(item.getData());
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
