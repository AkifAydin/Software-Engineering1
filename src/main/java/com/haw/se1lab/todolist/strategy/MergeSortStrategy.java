package com.haw.se1lab.todolist.strategy;

import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeSortStrategy implements TodoListSortingStrategy {

    @Override
    public List<TodoList> sort(List<TodoList> list) {

        List<TodoList> outputList = new ArrayList<>(list);
        Collections.sort(outputList, Comparator.comparing(TodoList::getId));

        return outputList;
    }

}
