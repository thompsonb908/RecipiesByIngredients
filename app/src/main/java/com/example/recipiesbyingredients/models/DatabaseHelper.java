package com.example.recipiesbyingredients.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Ingredients.db";

    private static final String SQL_CREATE_INGREDIENTS = "CREATE TABLE INGREDIENTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, QUANTITY TEXT)";
    private static final String SQL_DELETE_INGREDIENTS = "DROP TABLE IF EXISTS INGREDIENTS";

    private static DatabaseHelper dbInstance;

    public static DatabaseHelper getInstance(Context context) {
        if(dbInstance == null) dbInstance = new DatabaseHelper(context);
        return dbInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_INGREDIENTS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_INGREDIENTS);
        onCreate(db);
    }

    private void createIngredient(Ingredient ing, SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("NAME", ing.getName());
        vals.put("QUANTITY", ing.getQuantity());
        db.insert("INGREDIENTS", null, vals);
    }


    public void createIngredient(Ingredient ing) {
        SQLiteDatabase db = this.getWritableDatabase();
        createIngredient(ing, db);
    }

    public List<Ingredient> searchIngredients(String search) {
        SQLiteDatabase db = this.getWritableDatabase();
        String filter = "NAME LIKE '" + search + "%'";
        Cursor cursor = db.rawQuery("SELECT * FROM INGREDIENTS WHERE " + filter, null);
        List<Ingredient> list = new ArrayList<Ingredient>();
        while(cursor.moveToNext()) {
            list.add(new Ingredient(cursor.getString(cursor.getColumnIndex("NAME")), cursor.getString(cursor.getColumnIndex("QUANTITY"))));
            list.get(list.size()-1).setId(cursor.getInt(cursor.getColumnIndex(("_id"))));
        }
        cursor.close();
        return list;
    }

    public void updateIngredient(Ingredient ing) {
        SQLiteDatabase db = this.getWritableDatabase();
        String strFilter;
        if(ing.getId() != -1) {
            strFilter = "_id='" + ing.getId() + "'";
        } else {
            strFilter = "NAME='" + ing.getName() + "'";
        }

        ContentValues args = new ContentValues();
        args.put("QUANTITY", ing.getQuantity());

        db.update("INGREDIENTS", args, strFilter, null);
    }

    public void deleteIngredient(Ingredient ing) {
        SQLiteDatabase db = this.getWritableDatabase();
        String strFilter;
        if(ing.getId() != -1) {
            strFilter = "_id=" + ing.getId();
        } else {
            strFilter = "NAME='" + ing.getName() + "'";
        }

        db.delete("INGREDIENTS", strFilter, null);
    }

    public List<Ingredient> getIngredients() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM INGREDIENTS", null);
        List<Ingredient> list = new ArrayList<Ingredient>();
        while(cursor.moveToNext()) {
            list.add(new Ingredient(cursor.getString(cursor.getColumnIndex("NAME")), cursor.getString(cursor.getColumnIndex("QUANTITY"))));
            list.get(list.size()-1).setId(cursor.getInt(cursor.getColumnIndex(("_id"))));
        }
        cursor.close();
        return list;
    }


}
