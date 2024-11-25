package com.example.brickmart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Mydatabase extends SQLiteOpenHelper {
    public Mydatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {


        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("MainActivity.this","In oncreate\n\n\n\n\n\n\n");
        sqLiteDatabase.execSQL("CREATE TABLE DETAIL(USER TEXT,PASSWORD TEXT,NAME TEXT,PHONE TEXT,NUMBER TEXT,PRICE TEXT,COMPANY TEXT,DISTRICT TEXT,PINCODE TEXT,COLOR TEXT,ID INT)");
    }

    public void createNewTable() {
        SQLiteDatabase db=getWritableDatabase();
        String createTableQuery = "DELETE FROM DETAIL WHERE ID=1 OR ID=2 OR ID=3 OR ID IS NULL";
        db.execSQL(createTableQuery);
    }

    public Cursor getDetails(int id)
    {
            SQLiteDatabase db=getReadableDatabase();

            return db.rawQuery("SELECT * FROM DETAIL WHERE ID=?",new String[]{String.valueOf(id)});
    }
    public Cursor getDetails(String user)
    {
        SQLiteDatabase db=getReadableDatabase();
        return db.rawQuery("SELECT * FROM DETAIL WHERE USER=?",new String[]{user});
    }


    public void deleteItem(String user,String name,String number,String company,int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        String query="DELETE FROM DETAIL WHERE USER='"+user+"' AND NAME='"+name+"' AND NUMBER='"+number+"' AND ID="+id;
        if(company!=null)
        {
            query=query + " AND COMPANY='"+company+"'";
        }
        else
        {
            query=query+" AND COMPANY IS NULL";
        }
        db.execSQL(query);
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }







    public boolean insertdata(String user,String password)
    {

        SQLiteDatabase myDb=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("USER",user);
        cv.put("PASSWORD",password);
        long res=myDb.insert("DETAIL",null,cv);
        if(res!=-1)
            return true;
        else
            return false;
    }

    public boolean insertdetails(String user,String password,String name,String phone,String number,String price,String color ,String company,String district,String pin,int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("USER",user);
        cv.put("PASSWORD",password);
        cv.put("NAME",name);
        cv.put("PHONE",phone);
        cv.put("NUMBER",number);
        cv.put("PRICE",price);
        cv.put("COMPANY",company);
        cv.put("DISTRICT",district);
        cv.put("PINCODE",pin);
        cv.put("COLOR",color);
        cv.put("ID",id);

        long result=db.insert("DETAIL",null,cv);
        if(result!=-1)
            return true;
        else
            return false;
    }

    public int alreadyThere(String user,String password)
    {
        Cursor cursor=validate(user, password);
        if(cursor!=null)
        {
            if(cursor.moveToFirst())
                return -1;
            return 0;
        }
        cursor.close();
        return 0;
    }


    public Cursor validate(String user,String password)
    {

        SQLiteDatabase myDb = getReadableDatabase();
        String rawQuery = "SELECT * FROM DETAIL WHERE USER = ? AND PASSWORD = ?";
        Cursor cursor = myDb.rawQuery(rawQuery, new String[]{user, password});

        return cursor;



    }

}
