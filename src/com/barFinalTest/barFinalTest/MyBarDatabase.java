package com.barFinalTest.barFinalTest;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//import android.util.Log;

public class MyBarDatabase extends SQLiteOpenHelper {
	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MyBarDB";
    
    public MyBarDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); 
        
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create food table
        String CREATE_MYBAR_TABLE = "CREATE TABLE MyBars ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "name TEXT, "+
                "website TEXT )";
 
        // create myBar table
        db.execSQL(CREATE_MYBAR_TABLE);
        
        //DEBUG
        //Log.e("FOODCYLCE", "onCreate for MyDatabase!");
    } 
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	//db = this.getWritableDatabase();
        // Drop older foods table if existed
        db.execSQL("DROP TABLE IF EXISTS MyBar");
 
        // create fresh foods table
        this.onCreate(db);
    }
    
 // Foods table name
    private static final String TABLE_MYBAR = "myBars";
 
    // Foods Table Columns names
    private static final String KEY_ID = "id"; //0
    private static final String KEY_NAME="name";//1
    private static final String KEY_WEBSITE = "website"; //2
  
    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_WEBSITE};
    
    public void addMyBar(Bar bar){
        //for logging
    	Log.d("addMyBar", bar.toString()); 

    	// 1. get reference to writable DB
    	SQLiteDatabase db = this.getWritableDatabase();
    	//db = this.getWritableDatabase();
    	
    	// 2. create ContentValues to add key "column"/value
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, bar.getName()); // get name 
    	values.put(KEY_WEBSITE, bar.getWebsite()); // get type 

    	// 3. insert
    	db.insert(TABLE_MYBAR, // table
        null, //nullColumnHack
        values); // key/value -> keys = column names/ values = column values

    	// 4. close
    	db.close(); 
    }
    
    public Bar getMyBar(String name) {
    	// 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
     
        // 2. build query
        Cursor cursor = 
                db.query(TABLE_MYBAR, // a. table
                COLUMNS, // b. column names
                KEY_NAME+"= ?", // c. selections 
                new String[] { String.valueOf(name) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
     
        // 4. build food object
        Bar bar = new Bar();
        bar.setId(Integer.parseInt(cursor.getString(0)));
        bar.setName(cursor.getString(1));
        bar.setWebsite(cursor.getString(2));
     
        db.close();
        //log 
        Log.d("getMyBar("+bar.getId()+")", bar.toString());
     
        // 5. return food
        return bar;
    }
    
    public List<String> getAllMyBarStrings() {
    	List<String> myBars = new LinkedList<String>();
    	 
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_MYBAR;
  
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build food and add it to list
        Bar bar = null;
        if (cursor.moveToFirst()) {
            do {
                bar = new Bar();
                bar.setId(Integer.parseInt(cursor.getString(0)));
                bar.setName(cursor.getString(1));
                bar.setWebsite(cursor.getString(2));
  
                // Add food to foods
                //hacking this together, as well!
                myBars.add(bar.getName());
                
            } while (cursor.moveToNext());
        }
  
        Log.d("getAllFoods", myBars.toString());
  
        db.close();
        
        // return foods
        return myBars;
    }
    
    public void deleteMyBar(Bar bar) {
    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
         
        // 2. delete
        db.delete(TABLE_MYBAR, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(bar.getId()) }); //selections args
        
       

        // 3. close
        db.close();
 
        //log
        //Log.d("deleteMyBar", bar.toString());
        Log.d("deletingMyBar", Integer.toString(bar.getId()));
    }
    
}
