package com.sargent.mark.todolist.data;



public class ToDoItem {
    private String description;
    private String dueDate;
    private String category;
    private Integer done;


    public ToDoItem(String description, String dueDate, String category, int done) {
        this.description = description;
        this.dueDate = dueDate;
        this.category = category;
        this.done = done;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
