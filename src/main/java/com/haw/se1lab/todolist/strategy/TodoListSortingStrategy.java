package com.haw.se1lab.todolist.strategy;

import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;

import java.util.List;

/**
 * Interface for abstract sorting strategy
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */


public interface TodoListSortingStrategy {

    /**
     * sorts todolists by their id in ascending order
     * returns a copy of the list
     * to a list size of 100 mergesort is used
     * for a list containing more than 100 elements quicksort is used
     *
     * @param list the unsorted list
     * @return returns sorted list
     */
    List<TodoList> sort(List<TodoList> list);

}
