package com.onchiri.daisy.eventplanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.onchiri.daisy.eventplanner.Service.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.onchiri.daisy.eventplanner.R.layout.activity_budget_list;


public class BudgetList extends Fragment {
    Spinner spinnerEvent;
    TextView budget_display,amount_display,paid_display,due_display;
    Button deleteBudget;
    EditText deletebudgetet;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(activity_budget_list, container, false);
        ImageButton add_budget_button = (ImageButton) view.findViewById(R.id.budgetadd);
        budget_display = view.findViewById(R.id.budget_display);
        spinnerEvent = view.findViewById(R.id.spinner_event_budget_list);
        due_display = view.findViewById(R.id.due_display);
        amount_display = view.findViewById(R.id.amount_display);
        paid_display = view.findViewById(R.id.paid_display);
        deleteBudget = view.findViewById(R.id.deleteBudgetButton);
        deletebudgetet = view.findViewById(R.id.editText_deletebudget);


        getActivity().setTitle("Budget");

        deleteBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBudget();

            }
        });

        //Background animation
//        AnimationDrawable animationDrawable = (AnimationDrawable) view.getBackground();
//        animationDrawable.setEnterFadeDuration(2000);
//        animationDrawable.setExitFadeDuration(4000);
//        animationDrawable.start();


        add_budget_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new AddBudget());
                ft.commit();

            }});



//SPINNER EVENTS
        DatabaseHelper mydb = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mydb.getReadableDatabase();

        Cursor cursor = mydb.getEvents(db);

        List<String> spinnerEventValues =new ArrayList<>();
        while(cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_NAME));
            spinnerEventValues.add(name);
        }
        ArrayAdapter<String> Eventadapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,spinnerEventValues);
        spinnerEvent.setAdapter(Eventadapter);
        spinnerEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       readBudgets();
        return view;
    }
//delete budget
    public void deleteBudget(){

        DatabaseHelper db = new DatabaseHelper(getActivity());
        SQLiteDatabase mydb = db.getWritableDatabase();

        int id = Integer.parseInt(deletebudgetet.getText().toString());

        if (deletebudgetet == null) {
            Toast.makeText(getActivity(), "Enter valid id", Toast.LENGTH_LONG).show();
        }


        db.deleteBudgets(id,mydb);

        db.close();
        deletebudgetet.setText("");
        openGuestFragment();
        Toast.makeText(getActivity(),"Budget removed successfully", Toast.LENGTH_SHORT).show();

    }
    public void openGuestFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new BudgetList());
        ft.commit();
    }


    private void readBudgets(){
        DatabaseHelper mydb = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mydb.getReadableDatabase();

        Cursor cursor = mydb.getBudgets(db);

        String info = " ";

        int paids =0;
        int amounts =0;
        int dues =0;

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_1));
            String event = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_BUDGET_EVENT));
            String budget = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_BUDGET_ITEM));
            int amount = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_BUDGET_AMOUNT));
            int paid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_BUDGET_PAID));
            String notes = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_BUDGET_NOTE));

            info = info + "\n\n"+"ID : "+id +"\nEvent : "+ event + "\nBudget : "+
                    budget + "\nAmount :" + amount+"\nPaid Amount : "+paid +"\nNotes : "+ notes;




           paids = paids + paid;
           amounts = amounts +amount;
           dues = amounts - paids;





    }

     budget_display.setText(info);
     amount_display.setText(Integer.toString(amounts));
     paid_display.setText(Integer.toString(paids));
     due_display.setText(Integer.toString(dues));

       mydb.close();
    }
}
