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

public class BarDatabase extends SQLiteOpenHelper {
	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BarDB";
    
    public BarDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); 
        
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create food table
        String CREATE_BAR_TABLE = "CREATE TABLE Bars ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "name TEXT, "+
                "type TEXT, "+
                "address TEXT, " +
                "website TEXT)";
 
        // create myBar table
        db.execSQL(CREATE_BAR_TABLE);
        
        //DEBUG
        //Log.e("FOODCYLCE", "onCreate for MyDatabase!");
    } 
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	//db = this.getWritableDatabase();
        // Drop older foods table if existed
        db.execSQL("DROP TABLE IF EXISTS Bar");
 
        // create fresh foods table
        this.onCreate(db);
    }
    
 // Foods table name
    private static final String TABLE_BAR = "Bars";
 
    // Foods Table Columns names
    private static final String KEY_ID = "id"; //0
    private static final String KEY_NAME="name";//1
    private static final String KEY_TYPE = "type"; //2
    private static final String KEY_ADDRESS = "address"; //3
    private static final String KEY_WEBSITE = "website";
  
    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_TYPE,KEY_ADDRESS,KEY_WEBSITE};
    
    public void addBar(Bar bar){
        //for logging
    	Log.d("addBar", bar.toString()); 

    	// 1. get reference to writable DB
    	SQLiteDatabase db = this.getWritableDatabase();
    	//db = this.getWritableDatabase();
    	
    	// 2. create ContentValues to add key "column"/value
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, bar.getName()); // get name 
    	values.put(KEY_TYPE, bar.getType()); // get type 
    	values.put(KEY_ADDRESS, bar.getAddress()); // get address
    	values.put(KEY_WEBSITE, bar.getWebsite());

    	// 3. insert
    	db.insert(TABLE_BAR, // table
        null, //nullColumnHack
        values); // key/value -> keys = column names/ values = column values

    	// 4. close
    	db.close(); 
    }
    
    public Bar getBar(String name) {
    	// 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
     
        // 2. build query
        Cursor cursor = 
                db.query(TABLE_BAR, // a. table
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
        bar.setType(cursor.getString(2));
        bar.setAddress(cursor.getString(3));
        bar.setWebsite(cursor.getString(4));
     
        db.close();
        //log 
        Log.d("getBar("+bar.getId()+")", bar.toString());
     
        // 5. return food
        return bar;
    }
    
    public List<String> getAllBarStrings() {
    	List<String> myBars = new LinkedList<String>();
    	 
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_BAR;
  
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
                bar.setType(cursor.getString(2));
                bar.setAddress(cursor.getString(3));
  
                // Add food to foods
                //hacking this together, as well!
                myBars.add(bar.getName() + " " + bar.getType() + " " + bar.getId() + "\t\tQt: " + bar.getAddress());
                
            } while (cursor.moveToNext());
        }
  
        Log.d("getAllFoods", myBars.toString());
  
        db.close();
        
        // return foods
        return myBars;
    }
    
    public void deleteBar(Bar bar) {
    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
         
        // 2. delete
        db.delete(TABLE_BAR, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(bar.getId()) }); //selections args
        
       

        // 3. close
        db.close();
 
        //log
        //Log.d("deleteMyBar", bar.toString());
        Log.d("deletingMyBar", Integer.toString(bar.getId()));
    }
    
}
