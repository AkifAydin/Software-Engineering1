package com.haw.se1lab.todolist.logic.impl.usecase;

import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.todolist.strategy.MergeSortStrategy;
import com.haw.se1lab.todolist.strategy.QuickSortStrategy;
import com.haw.se1lab.todolist.strategy.TodoListSortingStrategy;

import java.util.List;

public class TodoListSortingUtil {

    public static TodoListSortingStrategy getSortingStrategy(List<TodoList> list){

        if(list.size() <= 100){
            return new MergeSortStrategy();
        }else{
            return new QuickSortStrategy();
        }

    }
}
