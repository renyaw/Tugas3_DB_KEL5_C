package com.example.fsm_classroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper{

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "classdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "fsmclass";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our class name column
    private static final String NAME_COL = "nomor";

    // below variable id for our building column.
    private static final String BUILDING_COL = "gedung";

    // below variable is for our capacity column.
    private static final String CAPACITY_COL = "kapasitas";

    // below variable for our class description column.
    private static final String DESCRIPTION_COL = "deskripsi";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + BUILDING_COL + " TEXT,"
                + CAPACITY_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new class to our sqlite database.
    public void addNewClass(String classNo, String building, String capacity, String classDescription) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, classNo);
        values.put(BUILDING_COL, building);
        values.put(CAPACITY_COL, capacity);
        values.put(DESCRIPTION_COL, classDescription);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the classs.
    public ArrayList<ClassModal> readClasses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorClasses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<ClassModal> classModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorClasses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                classModalArrayList.add(new ClassModal(cursorClasses.getString(1),
                        cursorClasses.getString(4),
                        cursorClasses.getString(2),
                        cursorClasses.getString(3)));
            } while (cursorClasses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor 
        // and returning our array list.
        cursorClasses.close();
        return classModalArrayList;
    }

    // below is the method for updating our classs
    public void updateClass(String originalClassName, String className,
                             String building, String capacity, String classDescription) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, className);
        values.put(CAPACITY_COL, capacity);
        values.put(DESCRIPTION_COL, classDescription);
        values.put(BUILDING_COL, building);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our class which is stored in original name variable.
        db.update(TABLE_NAME, values, "nomor=?", new String[]{originalClassName});
        db.close();
    }


    // below is the method for deleting our class.
    public void deleteClass(String className) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // class and we are comparing it with our class name.
        db.delete(TABLE_NAME, "nomor=?", new String[]{className});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
