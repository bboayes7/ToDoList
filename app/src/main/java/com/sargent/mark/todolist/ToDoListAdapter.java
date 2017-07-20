package com.sargent.mark.todolist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sargent.mark.todolist.data.Contract;
import com.sargent.mark.todolist.data.ToDoItem;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ItemHolder> {

    private Cursor cursor;
    private ItemClickListener listener;
    private String TAG = "todolistadapter";

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item, parent, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public interface ItemClickListener {
        void onItemClick(int pos, String description, String duedate, long id, String category, Integer done);
        void checkBoxClicked(long id, Integer done);
    }

    public ToDoListAdapter(Cursor cursor, ItemClickListener listener) {
        this.cursor = cursor;
        this.listener = listener;
    }

    public void swapCursor(Cursor newCursor){
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView descr;
        TextView due;
        TextView cat;
        String duedate;
        String description;
        //adding the extra columns
        String category;
        Integer done = 0;
        //need a way to update the database with the checkbox
        CheckBox checkBox;


        long id;


        ItemHolder(View view) {
            super(view);
            descr = (TextView) view.findViewById(R.id.description);
            due = (TextView) view.findViewById(R.id.dueDate);
            cat = (TextView) view.findViewById(R.id.type);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            view.setOnClickListener(this);
        }

        public void bind(ItemHolder holder, int pos) {
            cursor.moveToPosition(pos);
            id = cursor.getLong(cursor.getColumnIndex(Contract.TABLE_TODO._ID));
            Log.d(TAG, "deleting id: " + id);

            duedate = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_DUE_DATE));
            description = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_DESCRIPTION));
            category = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_CATEGORY));

            //create a listener for the checkbox
            checkBox.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //set done to 1 or 0 if its checked or not and pass into the item listener
                    if(checkBox.isChecked()){
                        done = 1 ;
                    } else {
                        done = 0;
                    }
                    listener.checkBoxClicked(id, done);
                }
            });


            descr.setText(description);
            due.setText("Due: " + duedate);
            cat.setText("Type: " + category);
            holder.itemView.setTag(id);
        }

        @Override
        public void onClick(View v) {
            //need to find who the culprit is for this nullpointer exception
            Log.d(TAG, "CLICKCLICKCLICK");
            int pos = getAdapterPosition();
            listener.onItemClick(pos, description, duedate, id, category, done);
        }


    }

}
