package com.example.intents2.classes;
import androidx.annotation.NonNull;

import com.example.intents2.interfaces.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E>,Iterable<E> {
    private E[] data;
    private int i =-1;
    public ArrayStack(int capacity){
        data = (E[]) new Object[capacity];
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return new StackIterator<>();
    }

    private class StackIterator<E> implements Iterator<E>{
        private int j =size()-1;
        @Override
        public boolean hasNext() {
            return j>=0;
        }

        @Override
        public E next() throws IllegalStateException {
            if(j==-1) throw new IllegalStateException("No next element");
            return (E) data[j--];
        }
    }

    @Override
    public E pop() {
        if(isEmpty())return null;
        E popped = data[i];
        data[i] = null;
        i--;
        return popped;
    }

    @Override
    public void push(E e) throws IllegalStateException {
        if(size()== data.length) throw new IllegalStateException("Stack is full");
        data[++i] = e;
    }

    @Override
    public E top() {
        if(isEmpty())return null;
        return data[i];
    }

    @Override
    public boolean isEmpty() {
        return i==-1;
    }

    @Override
    public int size() {
        return i+1;
    }
}
