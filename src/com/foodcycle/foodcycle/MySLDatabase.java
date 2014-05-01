package com.foodcycle.foodcycle;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//import android.util.Log;

public class MySLDatabase extends SQLiteOpenHelper {
	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "FoodDB";
    
    public MySLDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); 
        
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create food table
        String CREATE_FOOD_TABLE = "CREATE TABLE food ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "type TEXT, "+
                "count TEXT )";
 
        // create foods table
        db.execSQL(CREATE_FOOD_TABLE);
        
        //DEBUG
        //Log.e("FOODCYLCE", "onCreate for MyDatabase!");
    } 
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	//db = this.getWritableDatabase();
        // Drop older foods table if existed
        db.execSQL("DROP TABLE IF EXISTS food");
 
        // create fresh foods table
        this.onCreate(db);
    }
    
 // Foods table name
    private static final String TABLE_FOODS = "food";
 
    // Foods Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_COUNT = "count";
 
    private static final String[] COLUMNS = {KEY_ID,KEY_TYPE,KEY_COUNT};
    
    public void addFoodSL(FoodSL food){
        //for logging
    	Log.d("addFood", food.toString()); 

    	// 1. get reference to writable DB
    	SQLiteDatabase db = this.getWritableDatabase();
    	//db = this.getWritableDatabase();
    	
    	// 2. create ContentValues to add key "column"/value
    	ContentValues values = new ContentValues();
    	values.put(KEY_TYPE, food.getType()); // get type 
    	values.put(KEY_COUNT, food.getCount()); // get count

    	// 3. insert
    	db.insert(TABLE_FOODS, // table
        null, //nullColumnHack
        values); // key/value -> keys = column names/ values = column values

    	// 4. close
    	db.close(); 
    }
    
    public FoodSL getFoodSL(String type) {
    	// 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
     
        // 2. build query
        Cursor cursor = 
                db.query(TABLE_FOODS, // a. table
                COLUMNS, // b. column names
                KEY_TYPE+"= ?", // c. selections 
                new String[] { String.valueOf(type) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
     
        // 4. build food object
        FoodSL food = new FoodSL();
        food.setId(Integer.parseInt(cursor.getString(0)));
        food.setType(cursor.getString(1));
        food.setCount(Integer.parseInt(cursor.getString(2)));
     
        db.close();
        //log 
        Log.d("getFoodSL("+food.getId()+")", food.toString());
     
        // 5. return food
        return food;
    }
    
    public List<String> getAllFoodsSLStrings() {
    	List<String> foods = new LinkedList<String>();
    	 
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_FOODS;
  
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build food and add it to list
        FoodSL food = null;
        if (cursor.moveToFirst()) {
            do {
                food = new FoodSL();
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setType(cursor.getString(1));
                food.setCount(Integer.parseInt(cursor.getString(2)));
  
                // Add food to foods
                //hacking this together, as well!
                foods.add(food.getType() + food.getId() + "\t\tQt: " + food.getCount());
                
            } while (cursor.moveToNext());
        }
  
        Log.d("getAllFoods", foods.toString());
  
        db.close();
        
        // return foods
        return foods;
    }
    
    public void deleteFoodSL(FoodSL food) {
    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_FOODS, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(food.getId()) }); //selections args

        // 3. close
        db.close();
 
        //log
        Log.d("deleteFood", food.toString());
    }
    
}
