package com.onchiri.daisy.eventplanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.onchiri.daisy.eventplanner.Model.Budget;
import com.onchiri.daisy.eventplanner.Service.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AddBudget extends Fragment {

    DatabaseHelper myDb;
    Button budgetAddButton;
    EditText budgetItem,paidAmount,amount,budgetnotes;
    Spinner spinnerEvent;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myDb = new DatabaseHelper(getActivity());

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //Set title
        getActivity().setTitle("Budget");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_budget, container, false);
        spinnerEvent = view.findViewById(R.id.spinner_event_budget);
        budgetItem = view.findViewById(R.id.edit_budget_name);
        paidAmount = view.findViewById(R.id.editText_paid_amount);
        amount = view.findViewById(R.id.editText_budget_amount);
        budgetAddButton = view.findViewById(R.id.budgetAddButton);
        budgetnotes = view.findViewById(R.id.edit_budget_note);







        //Spinner Event values
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

        AddBudgetData();
        return view;
    }


    public void AddBudgetData(){
        budgetAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Budget budget = new Budget();
                    budget.setEvent(spinnerEvent.getSelectedItem().toString());
                    budget.setBudgetItem(budgetItem.getText().toString());
                    budget.setAmount((Integer.parseInt(amount.getText().toString())));
                   budget.setPaid(Integer.parseInt(paidAmount.getText().toString()));
                    budget.setNote(budgetnotes.getText().toString());


                    //Print data in the console
                    System.out.println("Insert Budget Data = " + budgetItem.getText().toString());

                    boolean isInserted = myDb.insertBudgetData(budget);
                    if (isInserted = true)
                        Toast.makeText(getActivity(),"Data Inserted Successfully..",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getActivity(),"Data Inserted Error ..",Toast.LENGTH_LONG).show();

                    openEventFragment();


                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }

            }
        });
    }

    public void openEventFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new BudgetList());
        ft.commit();
    }



}
