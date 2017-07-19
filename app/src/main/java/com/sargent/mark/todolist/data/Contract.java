package com.sargent.mark.todolist.data;

import android.provider.BaseColumns;



public class Contract {

    public static class TABLE_TODO implements BaseColumns{
        public static final String TABLE_NAME = "todoitems";

        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DUE_DATE = "duedate";
        //I created 2 more columns, one to store the type of to do(category) , one to see if the task is done
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_DONE = "done";

    }
}
