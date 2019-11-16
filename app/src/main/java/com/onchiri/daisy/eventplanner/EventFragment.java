package com.onchiri.daisy.eventplanner;

//import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onchiri.daisy.eventplanner.Service.DatabaseHelper;

/**
 * Created by inushaV on 8/31/2019.
 */

public class EventFragment extends Fragment {

    View myView;
    Button creat_event_button;
    private TextView event_display;
    EditText deleteId;
    Button deleteEventButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Set title
        getActivity().setTitle("Events");

        myView = inflater.inflate(R.layout.fregment_event, container, false);
        creat_event_button = (Button) myView.findViewById(R.id.button_create_event);
        event_display = myView.findViewById(R.id.event_display);
        deleteId = myView.findViewById(R.id.editText_deleteEvent);
        deleteEventButton = myView.findViewById(R.id.deleteEventButton);

        deleteEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEvent();

            }
        });



        AnimationDrawable animationDrawable = (AnimationDrawable) myView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        creat_event_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, new CreateEvent());
                ft.commit();

            }});
        readEvents();
        return myView;

    }
    //delete events
    public void deleteEvent(){

        DatabaseHelper db = new DatabaseHelper(getActivity());
        SQLiteDatabase mydb = db.getWritableDatabase();

        int id = Integer.parseInt(deleteId.getText().toString());

        db.deleteEvents(id,mydb);

        db.close();
        deleteId.setText("");
        openGuestFragment();
        Toast.makeText(getActivity(),"Event deleted", Toast.LENGTH_SHORT).show();

    }

    public void openGuestFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, new EventFragment());
        ft.commit();
    }

    //retrieve guests from db
    private void readEvents(){
        DatabaseHelper mydb = new DatabaseHelper(getActivity());
        SQLiteDatabase db = mydb.getReadableDatabase();

        Cursor cursor = mydb.getEvents(db);

        String info = " ";

        while(cursor.moveToNext())
        {
            String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_1));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_NAME));
            String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_DATE));
            String location = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_LOCATION));
            String notes = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EVENT_NOTE));

            info =info + "\n\n\n"+"Event ID : " +id +"\nName : " + name +"\nDate : "+ date+"\nLocation : " + location + "\nNotes : " + notes;

            event_display.setText(info);
        }

        mydb.close();

    }
}
