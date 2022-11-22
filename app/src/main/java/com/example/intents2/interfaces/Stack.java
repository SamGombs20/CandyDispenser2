package com.example.intents2.interfaces;

public interface Stack<E> {
    public E pop();
    public void push(E e);
    public E top();
    public boolean isEmpty();
    public int size();
}
