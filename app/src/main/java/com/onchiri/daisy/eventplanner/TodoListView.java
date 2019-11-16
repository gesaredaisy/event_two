package com.onchiri.daisy.eventplanner;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.onchiri.daisy.eventplanner.Service.DatabaseHelper;


public class TodoListView extends Fragment {

    Button btn_viewTodos;
    DatabaseHelper mydb;

    ImageButton btn_update_todolist,btn_delete_todolist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("To-Do List");
        final View view = inflater.inflate(R.layout.todolist_view, container, false);
        btn_viewTodos = (Button)view.findViewById(R.id.addTodoListbutton);
        mydb = new DatabaseHelper(getActivity());

        btn_update_todolist = (ImageButton)view.findViewById(R.id.ibUpdateCustomer);

        btn_delete_todolist = (ImageButton)view.findViewById(R.id.ibDeleteitem);

        btn_update_todolist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view1){
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mview = getLayoutInflater().inflate(R.layout.todolist_update_dialog,null);
                EditText notes =(EditText)mview.findViewById(R.id.editnotes);
                EditText tasks = (EditText)mview.findViewById(R.id.edittasks);
                Button mEdit = (Button)mview.findViewById(R.id.editbutton);

                mBuilder.setView(mview);
                AlertDialog dialog = mBuilder.create();
                dialog.show();



            }

        });

        btn_delete_todolist.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View view1){
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mview = getLayoutInflater().inflate(R.layout.todolist_delete_dialog,null);

                Button mCancel = (Button)mview.findViewById(R.id.cancel_action);
                Button mOk = (Button)mview.findViewById(R.id.okbutton);

                mBuilder.setView(mview);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }



        });

        btn_viewTodos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new TodoListAdd());
                ft.commit();
            }});
        return view;
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}