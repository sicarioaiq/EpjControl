package com.cah.control.epj.epjcontrol.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cah.control.epj.epjcontrol.SQL.MyDbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos.sarmiento on 23/06/2016.
 */
public class SQLController {
    private MyDbHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;
    ProgressDialog PD;

    public SQLController(Context c) {
        ourcontext = c;
    }

    public SQLController open() throws SQLException {

        dbhelper = new MyDbHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbhelper.close();
    }

    public Cursor readEntryPersona(String strNombre) {
        String orderBy =  " ORDER BY " + MyDbHelper.TMPersona_strNombrePersona + " ASC";
        Cursor c ;//database.query(MyDbHelper.TABLE_TMPersona, allColumns, null, null, null, null, orderBy);
        if (strNombre.trim().length()!=0)
        {
            c = database.rawQuery("SELECT * FROM " + MyDbHelper.TABLE_TMPersona + " WHERE " + MyDbHelper.TMPersona_strNombrePersona +
                    " LIKE ? " +orderBy, new String[]{"%" + strNombre + "%"});
            if (c != null) {
                c.moveToFirst();
            }
        }
        else
        {
            c = database.rawQuery("SELECT * FROM " + MyDbHelper.TABLE_TMPersona + orderBy,null);
        }
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public List<String> getAllPerona(boolean booTodo){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        if (booTodo)
        {
            labels.add("Todos");
        }
        String[] allColumns = new String[] { MyDbHelper.TMPersona_intIdPersona, MyDbHelper.TMPersona_strNombrePersona};
        String orderBy =  MyDbHelper.TMPersona_strNombrePersona + " ASC";
        Cursor cursor = database.query(MyDbHelper.TABLE_TMPersona, allColumns, null, null, null,null, orderBy);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        database.close();

        // returning lables
        return labels;
    }
}