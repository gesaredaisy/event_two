package com.onchiri.daisy.eventplanner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onchiri.daisy.eventplanner.Model.Todo;
import com.onchiri.daisy.eventplanner.Service.DatabaseHelper;

public class TodoListAdd extends Fragment {

    EditText addTodos,addNotes;
    DatabaseHelper myDb;
    Button button,todoListBack;
    TextView txt1,txt2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("To-Do List Add");
        View view = inflater.inflate(R.layout.todolist_add, container, false);
        addTodos = (EditText)view.findViewById(R.id.addtodos);
        addNotes = (EditText)view.findViewById(R.id.addnotes);
        button = (Button) view.findViewById(R.id.todolistbutton);
        txt1 = (TextView)view.findViewById(R.id.textView1);
        txt2 = (TextView)view.findViewById(R.id.textView2);
        myDb = new DatabaseHelper(getActivity());
        todoListBack = (Button) view.findViewById(R.id.todolistback);

        todoListBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new TodoListView());
                ft.commit();

            }});
        addTodoItems();
        return view;
    }
    public void addTodoItems(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Todo todos = new Todo();
                    todos.setTask(addTodos.getText().toString());
                    todos.setNotes(addNotes.getText().toString());

                    //Print data in the console
                    System.out.println("Insert Guest Data = " + addTodos.getText().toString() +","+ addNotes.getText().toString() );

                    boolean isInserted = myDb.insertTodoList(todos);
                    if (isInserted = true) {
                        Toast.makeText(getActivity(), "Data Inserted Successfully..", Toast.LENGTH_LONG).show();

                        openEventFragment();
                    }
                    else
                        Toast.makeText(getActivity(),"Data Inserted Error ..",Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }

            }
        });


    }

    public void openEventFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new TodoListView());
        ft.commit();
    }


}
