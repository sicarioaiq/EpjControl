package com.cah.control.epj.epjcontrol.SQL;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by carlos.sarmiento on 13/06/2016.
 */
public class MyDbHelper extends SQLiteOpenHelper{
    // TABLE INFORMATTION
    public static final String TABLE_TMPersona = "TMPersona";
    public static final String TMPersona_intIdPersona = "intIdPersona";
    public static final String TMPersona_strNombrePersona = "strNombrePersona";
    public static final String TMPersona__intActivo = "intActivo";

    public static final String TABLE_TMUsuario = "TMUsuario";
    public static final String TMUsuario_intId = "intId";
    public static final String TMUsuario_strUsuario = "strUsuario";
    public static final String TMUsuario_strPassword = "strPassword";
    public static final String TMUsuario_intIdPerfil = "intIdPerfil";
    public static final String TMUsuario_intActivo = "intActivo";
    public static final String TMUsuario_strRuta = "strRuta";
    public static final String TMUsuario_UltimaActualizacion = "UltimaActualizacion";


//    public int intIdPerfil { get; set; }
//    public int intActivo { get; set; }
//    public String strRuta { get; set; }
//    public String UltimaActualizacion { get; set; }


//    public static final String MONTO = "monto";
//
//    public static final String TABLE_PAGO_POR = "opcion";
//    public static final String OPCION_ID = "_id";
//    public static final String OPCION_DESCRIPTION = "descripcion";
//
//    public static final String TABLE_PERSONA = "persona";
//    public static final String PERSONA_ID = "_id";
//    public static final String PERSONA_NOMBRE = "nombre";
//
//    public static final String TABLE_TIKET = "tiket";
//    public static final String TIKET_ID = "_id";
//    public static final String TIKET_PERSONA = "nombre";
//    public static final String TIKET_ACTIVIDAD = "actividad";
//    public static final String TIKET_NRO = "nro";
//
//    public static final String TABLE_PERSON_TIKET = "persontiket";
//    public static final String PERSON_TIKET_ID = "_id";
//    public static final String PERSON_TIKET_PERSONA = "nombre";
//    public static final String PERSON_TIKET_ACTIVID = "actividad";
//    public static final String PERSON_TIKET_NRO ="nro";


    // DATABASE INFORMATION
    static final String DB_NAME = "Escuela.sqlite";//"MEMBER.DB";
    static final int DB_VERSION = 1;

    // TABLE CREATION STATEMENT

//    private static final String CREATE_TABLE =
//            "create table " + TABLE_MEMBER
//                    + "(" + MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                    + MEMBER_FIRSTNAME + " TEXT NOT NULL ," + MEMBER_LASTNAME + " TEXT NOT NULL ,"
//                    + MONTO  + " TEXT NOT     private static final String CREATE_TABLE =
//            "create table " + TABLE_MEMBER
//                    + "(" + MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                    + MEMBER_FIRSTNAME + " TEXT NOT NULL ," + MEMBER_LASTNAME + " TEXT NOT NULL ,"
//                    + MONTO  + " TEXT NOT NULL);";

//    private static final String CREATE_TABLEOPCION =
//            "create table " + TABLE_PAGO_POR
//                    + "(" + OPCION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                    + OPCION_DESCRIPTION + " TEXT NOT NULL );";
//
//    private static  final  String CREATE_TABLEPERSON =
//            "create table " + TABLE_PERSONA
//                    + "(" + PERSONA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                    + PERSONA_NOMBRE + " TEXT NOT NULL );";
//
//    private static final String CREATE_TABLETIKET_PERSON =
//            "create table " + TABLE_TIKET
//                    + "(" + TIKET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                    + TIKET_PERSONA + " TEXT NOT NULL ," + TIKET_ACTIVIDAD + " TEXT NOT NULL , " + TIKET_NRO+ " TEXT NOT NULL );";
//
//    private static final String CREATE_TABLEPER_TIKET =
//            "create table " + TABLE_PERSON_TIKET
//                    + "(" + PERSON_TIKET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                    + PERSON_TIKET_PERSONA + " TEXT NOT NULL ," + PERSON_TIKET_ACTIVID + " TEXT NOT NULL , " + PERSON_TIKET_NRO+ " TEXT NOT NULL );";

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE);
//        db.execSQL(CREATE_TABLEOPCION);
//        db.execSQL(CREATE_TABLEPERSON);
//        db.execSQL(CREATE_TABLETIKET_PERSON);
//        db.execSQL(CREATE_TABLEPER_TIKET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAGO_POR);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONA);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIKET);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON_TIKET);
        onCreate(db);
    }
}
