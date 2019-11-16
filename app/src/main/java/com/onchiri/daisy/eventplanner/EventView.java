package com.onchiri.daisy.eventplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class EventView extends Fragment {

    CardView mycard ;
    CardView guest;

    Intent i ;
    LinearLayout ll;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.eventview_activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Home");


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.eventview_activity, container, false);
        ll = view.findViewById(R.id.ll);
        mycard = view. findViewById(R.id.bankcardId);
        //i = new Intent(this,BudgetList.class);
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = new EventFragment();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.content_frame, new EventFragment());
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();



            }});
        mycard = view. findViewById(R.id.guest);
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = new GuestsView();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.content_frame, new GuestsView());
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

//                return view;
//      ;



            }});
        mycard = view. findViewById(R.id.budget);
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = new  BudgetList();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.content_frame, new   BudgetList());
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

//                return view;
//      ;



            }});
        mycard = view. findViewById(R.id.schedule);
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = new  TodoListView();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.content_frame, new   TodoListView());
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

//                return view;
//      ;



            }});
        mycard = view. findViewById(R.id.providers);
        mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = new  TodoListView();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.content_frame, new   TodoListView());
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

//                return view;
//      ;



            }});
        return view;


    }


}
