package ru.academits.orlov.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(E data) {
        this.head = new ListItem<>(data);
        count = 1;
    }

    // получение размера списка
    public int getCount() {
        return count;
    }

    // получение значения первого элемента
    public E getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Первый элемент отсутствует. Список пуст.");
        }

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

        getItem(index - 1).setNext(new ListItem<>(data, getItem(index)));
        count++;
    }

    // удаление узла по значению, пусть выдает true, если элемент был удален
    public boolean remove(E data) {
        if (data == null) {
            if (head.getData() == null) {
                unlinkHead();

                return true;
            } else {
                for (ListItem<E> previousItem = head, currentItem = head.getNext(); currentItem != null; previousItem = previousItem.getNext(), currentItem = currentItem.getNext()) {
                    if (currentItem.getData() == null) {
                        removeItem(previousItem);

                        return true;
                    }
                }
            }
        } else {
            if (head.getData().equals(data)) {
                unlinkHead();

                return true;
            } else {
                for (ListItem<E> previousItem = head, currentItem = head.getNext(); currentItem != null; previousItem = previousItem.getNext(), currentItem = currentItem.getNext()) {
                    if (data.equals(currentItem.getData())) {
                        removeItem(previousItem);

                        return true;
                    }
                }
            }
        }

        return false;
    }

    // удаление первого элемента, пусть выдает значение элемента
    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст.");
        }

        E removedData = head.getData();
        unlinkHead();

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

        for (ListItem<E> sourceItem = head.getNext(), copyItem = copy.head; sourceItem != null; sourceItem = sourceItem.getNext(), copyItem = copyItem.getNext()) {
            copyItem.setNext(new ListItem<>(sourceItem.getData()));
            copy.count++;
        }

        return copy;
    }

    private void checkElementIndex(int index, int upperBound) {
        if (index < 0 || index > upperBound) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы границ допустимых значений индекса (0, " + upperBound + ")");
        }
    }

    private void removeItem(ListItem<E> item) {
        item.setNext(item.getNext().getNext());
        count--;
    }

    private void unlinkHead() {
        head = head.getNext();
        count--;
    }

    private ListItem<E> getItem(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
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
