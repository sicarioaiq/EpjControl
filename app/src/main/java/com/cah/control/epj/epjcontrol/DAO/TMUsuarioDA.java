package com.cah.control.epj.epjcontrol.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cah.control.epj.epjcontrol.SQL.MyDbHelper;

/**
 * Created by carlos.sarmiento on 23/06/2016.
 */
public class TMUsuarioDA {
    private MyDbHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;
    ProgressDialog PD;

    public TMUsuarioDA(Context c) {
        ourcontext = c;
    }

    public TMUsuarioDA open() throws SQLException {

        dbhelper = new MyDbHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbhelper.close();
    }

    public Cursor readEntryUsuario(String strUsuario, String strPassword) {
        String orderBy =  " ORDER BY " + MyDbHelper.TMUsuario_strUsuario + " ASC";
        String [] allFilters = new String[] { "%" + strUsuario + "%" , "%" + strPassword + "%" };
        Cursor c = null;//database.query(MyDbHelper.TABLE_TMPersona, allColumns, null, null, null, null, orderBy);
        if(strUsuario.trim().length()!=0 && strPassword.trim().length()!=0)
        {
            c = database.rawQuery("SELECT * FROM " + MyDbHelper.TABLE_TMUsuario + " WHERE " + MyDbHelper.TMUsuario_strUsuario +
                    " LIKE ? AND " + MyDbHelper.TMUsuario_strPassword + " LIKE ? " +orderBy, allFilters);
        }
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
}
