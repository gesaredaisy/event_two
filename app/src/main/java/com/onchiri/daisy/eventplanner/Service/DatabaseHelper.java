package com.onchiri.daisy.eventplanner.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.onchiri.daisy.eventplanner.Model.Budget;
import com.onchiri.daisy.eventplanner.Model.Event;
import com.onchiri.daisy.eventplanner.Model.Guest;
import com.onchiri.daisy.eventplanner.Model.Todo;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="eventplanner.db";
    public static final String TABLE_TODO = "todotable";
    public static final String TABLE_EVENT = "events";
    public static final String TABLE_GUEST = "guests";
    public static final String TABLE_BUDGET ="budget";
    public static final String TABLE_SHOPPING ="shopping";

    //common column names
    public static final String COL_1 ="ID";


    //todolist columns
    public static final String COL_TASKS ="TASKS";
    public static final String COL_NOTES ="NOTES";

    //Event table column names
    public static final String COL_EVENT_NAME = "EVENTNAME";
    public static final String COL_EVENT_LOCATION = "EVENTLOCATION";
    public static final String COL_EVENT_DATE = "EVENTDATE";
    public static final String COL_EVENT_NOTE = "EVENTNOTE";

    //Guest table column names
    public static final String COL_GUEST_NAME = "GUESTNAME";
    public static final String COL_GUEST_AGE= "GUESTAGE";
    public static final String COL_GUEST_EMAIL = "GUESTEMAIL";
    public static final String COL_GUEST_GENDER= "GUESTGENDER";
    public static final String COL_GUEST_NOTE= "GUESTNOTE";
    public static final String COL_GUEST_EVENT= "GUESTEVENT";
    public static final String COL_GUEST_STATUS= "GUESTSTATUS";

    //Budget Table column names
    public static final String COL_BUDGET_ITEM = "BUDGETNAME";
    public static final String COL_BUDGET_EVENT = "BUDGETEVENT";
    public static final String COL_BUDGET_AMOUNT = "AMOUNT";
    public static final String COL_BUDGET_PAID = "PAID";
    public static final String COL_BUDGET_NOTE = "NOTES";

    //SHOPPING Table column names
    public static final String COL_SHOPPING_ITEM = "ITEMNAME";
    public static final String COL_SHOPPING_EVENT = "SHOPPINGEVENT";
    public static final String COL_SHOPPING_PURCHASED = "PURCHASED";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_UNITS = "UNITS";
    public static final String COL_QUANTITYMODE = "QUANTITYMODE";




    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    //creating tables in here
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ TABLE_TODO +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TASKS TEXT, NOTES TEXT)");

        db.execSQL("create table "+ TABLE_BUDGET +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,BUDGETNAME TEXT,BUDGETEVENT TEXT, AMOUNT INTEGER,PAID INTEGER, NOTES TEXT)");

        db.execSQL("create table " + TABLE_EVENT +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EVENTNAME TEXT,EVENTLOCATION TEXT,EVENTDATE TEXT,EVENTNOTE TEXT)");
        db.execSQL("create table " + TABLE_GUEST +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,GUESTNAME TEXT,GUESTAGE TEXT,GUESTEMAIL TEXT,GUESTGENDER TEXT, GUESTNOTE TEXT, GUESTEVENT TEXT, GUESTSTATUS TEXT)");
        db.execSQL("create table " + TABLE_SHOPPING +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ITEMNAME TEXT,SHOPPINGEVENT TEXT,PURCHASED TEXT,PRICE TEXT, UNITS TEXT, QUANTITYMODE TEXT, NOTES TEXT)");
    }

    //if the table exits delete them
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_GUEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING);
        onCreate(db);
    }


    //insert data to the Todolist
    public boolean insertTodoList(Todo t){
        System.out.println("Error InsertingData to todolist " + t.getTask());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TASKS,t.getTask());
        contentValues.put(COL_NOTES,t.getNotes());
        long result = db.insert(TABLE_TODO,null,contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    //view todolist data
    public Cursor getTodoListData(SQLiteDatabase db){

        String[] projections = {COL_1,COL_TASKS,COL_NOTES};
        Cursor cursor = db.query(TABLE_TODO, projections,null,null,null,null,null);
        return cursor;

    }








    //insert Event details
    public boolean insertEventData(Event event){
        System.out.println("Error insertEventData " + event.getEventName());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EVENT_NAME,event.getEventName());
        contentValues.put(COL_EVENT_LOCATION,event.getLocation());
        contentValues.put(COL_EVENT_DATE,event.getDate());
        contentValues.put(COL_EVENT_NOTE,event.getNote());

        long result = db.insert(TABLE_EVENT,null ,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }


    //insert Guest details
    public boolean insertGuestData(Guest guest){
        System.out.println("Error insert GuestData " + guest.getGuestName());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GUEST_NAME,guest.getGuestName());
        contentValues.put(COL_GUEST_AGE,guest.getAge());
        contentValues.put(COL_GUEST_EMAIL,guest.getGuestEmail());
        contentValues.put(COL_GUEST_GENDER,guest.getGuestGender());
        contentValues.put(COL_GUEST_NOTE,guest.getNotesGuest());
        contentValues.put(COL_GUEST_EVENT,guest.getEventName());
        contentValues.put(COL_GUEST_STATUS,guest.getStatus());

        long result = db.insert(TABLE_GUEST,null ,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    //delete guests
    public void deleteGuests(int id, SQLiteDatabase db){


        String selection = COL_1+"="+id;
        db.delete(TABLE_GUEST,selection,null);
    }

    //view guests data
    public Cursor getGuests(SQLiteDatabase db){
        String[] projections = {COL_1,COL_GUEST_NAME,COL_GUEST_AGE,COL_GUEST_EMAIL,COL_GUEST_GENDER,COL_GUEST_NOTE,COL_GUEST_EVENT,COL_GUEST_STATUS};
        Cursor cursor = db.query(TABLE_GUEST,projections,null,null,null,null,null);
        return cursor;

    }
    //search guests
    public Cursor searchGuests(String name, SQLiteDatabase db){

        String sql = "SELECT * FROM " + TABLE_GUEST + " WHERE " + COL_GUEST_NAME + " LIKE '%" + name + "%'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }


    //insert budget details
    public boolean insertBudgetData(Budget budget){
        System.out.println("Error insert Budget Data " + budget.getBudgetItem());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BUDGET_ITEM,budget.getBudgetItem());
        contentValues.put(COL_BUDGET_EVENT,budget.getEvent());
        contentValues.put(COL_BUDGET_AMOUNT,budget.getAmount());
        contentValues.put(COL_BUDGET_PAID,budget.getPaid());
        contentValues.put(COL_BUDGET_NOTE,budget.getNote());


        long result = db.insert(TABLE_BUDGET,null ,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //view budget data
    public Cursor getBudgets(SQLiteDatabase db){

        String[] projections = {COL_1,COL_BUDGET_ITEM,COL_BUDGET_EVENT,COL_BUDGET_AMOUNT,COL_BUDGET_PAID,COL_BUDGET_NOTE};
        Cursor cursor = db.query(TABLE_BUDGET,projections,null,null,null,null,null);
        return cursor;

    }
    //delete guests
    public void deleteBudgets(int id, SQLiteDatabase db){


        String selection = COL_1+"="+id;
        db.delete(TABLE_BUDGET,selection,null);
    }


    //view events data
    public Cursor getEvents(SQLiteDatabase db){


        String[] projections = {COL_1,COL_EVENT_NAME,COL_EVENT_DATE,COL_EVENT_LOCATION,COL_EVENT_NOTE};
        Cursor cursor = db.query(TABLE_EVENT,projections,null,null,null,null,null);
        return cursor;

    }


//Delete Events by Inusha
public void deleteEvents(int id, SQLiteDatabase db){


    String selection = COL_1+"="+id;
    db.delete(TABLE_EVENT,selection,null);

}


    //insert SHOPPING details
   /* public boolean insertShoppingData(ShoppingListModel list){
        System.out.println("Error insertEventData " + list.getItem());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SHOPPING_ITEM,list.getItem());
        contentValues.put(COL_SHOPPING_EVENT,list.getEvent());
        contentValues.put(COL_SHOPPING_PURCHASED,list.getPurchased());
        contentValues.put(COL_PRICE,list.getPrice());
        contentValues.put(COL_UNITS,list.getUnits());
        contentValues.put(COL_QUANTITYMODE,list.getQuantityMode());
        contentValues.put(COL_NOTES,list.getNotes());




        long result = db.insert(TABLE_SHOPPING,null ,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    //view shopping items data
    public Cursor getShoppingItems(SQLiteDatabase db){


        String[] projections = {COL_1,COL_SHOPPING_ITEM,COL_SHOPPING_EVENT,COL_SHOPPING_PURCHASED,COL_PRICE,COL_UNITS,COL_QUANTITYMODE,COL_NOTES};
        Cursor cursor = db.query(TABLE_SHOPPING,projections,null,null,null,null,null);
        return cursor;

    }

    //DELETE SHOPPING ITEM
    public void deleteShoppingItem(int id, SQLiteDatabase db){


        String selection = COL_1+"="+id;
        db.delete(TABLE_SHOPPING,selection,null);

    }*/

}
