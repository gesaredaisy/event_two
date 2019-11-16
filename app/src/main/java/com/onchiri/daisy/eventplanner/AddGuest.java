package com.onchiri.daisy.eventplanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
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

import com.onchiri.daisy.eventplanner.Model.Guest;
import com.onchiri.daisy.eventplanner.Service.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AddGuest extends Fragment{

    Button guest_add_button;
    EditText editTextGuestName,editTextNote,editTextEmail;
    Spinner spinnerGender, spinnerEvent,spinnerAge,spinnerStatus;


    DatabaseHelper mydb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_guest, container, false);

        mydb = new DatabaseHelper(getActivity());
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        editTextGuestName= (EditText) view.findViewById(R.id.et_guestName);
        guest_add_button = (Button) view.findViewById(R.id.add_guest_button);
        spinnerAge= (Spinner) view.findViewById(R.id.spinner_age);
        editTextNote= (EditText) view.findViewById(R.id.et_notes);
        editTextEmail= (EditText) view.findViewById(R.id.et_email);
        spinnerGender =(Spinner) view.findViewById(R.id.spinner_gender);
        spinnerEvent =(Spinner) view.findViewById(R.id.spinner_event);
        spinnerStatus =view.findViewById(R.id.spinner_status);




        //Spinner Gender Values
        String[] spinnerGenderValues ={"Female", "Male"};
        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,spinnerGenderValues);
        spinnerGender.setAdapter(adapterGender);
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Spinner Age values
        String[] spinnerValueHoldValue = {"Child", "Teenager", "Adult", "Senior"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,spinnerValueHoldValue);
        spinnerAge.setAdapter(adapter);
        spinnerAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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


        //Spinner status values
        String[] spinnerStatusValues = {"Invited", "RSVP", "Not Invited", "Attending","Waiting RSVP","Not Invited"};
        ArrayAdapter<String> statusadapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,spinnerStatusValues);
        spinnerStatus.setAdapter(statusadapter);
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AddGuestData();
        return view;
    }

    public void AddGuestData(){

        guest_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Guest guest = new Guest();
                    guest.setEventName(spinnerEvent.getSelectedItem().toString());
                    guest.setGuestName(editTextGuestName.getText().toString());
                    guest.setGuestEmail(editTextEmail.getText().toString());
                    String emailRegistration = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
                    guest.setGuestGender(spinnerGender.getSelectedItem().toString());
                    guest.setAge(spinnerAge.getSelectedItem().toString());
                    guest.setNotesGuest(editTextNote.getText().toString());
                    guest.setStatus(spinnerStatus.getSelectedItem().toString());

                    //Print data in the console
                    System.out.println("Insert Guest Data = " + editTextGuestName.getText().toString() +","+ editTextEmail.getText().toString() +","+  spinnerAge.toString() +","+ editTextNote.getText().toString());
                    if (emailRegistration.matches("[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+"))
                    {
                        editTextEmail.setError("Invalid Email Address");

                    }else if (editTextGuestName.getText().toString().length() <= 0 || editTextEmail.getText().toString().length() <= 0 || editTextNote.getText().toString().length() <= 0)
                    {
                        Toast.makeText(getActivity(),"Kindly insert data...",Toast.LENGTH_LONG).show();
                    } else
                    {
                        boolean isInserted = mydb.insertGuestData(guest);
                        if (isInserted = true)
                        {
                            Toast.makeText(getActivity(),"Data Inserted Successfully..",Toast.LENGTH_LONG).show();
                            editTextNote.setText("");
                            editTextGuestName.setText("");
                            editTextEmail.setText("");
                        }

                        else{
                            Toast.makeText(getActivity(),"Data Inserted Error ..",Toast.LENGTH_LONG).show();
                            openEventFragment();
                        }

                    }


                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }

            }
        });
    }

    public void openEventFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new GuestsView());
        ft.commit();
    }


}