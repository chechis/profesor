package com.example.chechis.profesor;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.HashMap;

public class ProviderContent extends ContentProvider {

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "db";
    static final String TABLE_NAME = "Textos";
    static final int DATABASE_VERSION = 2;
    static final String Sentencia = " CREATE TABLE " + TABLE_NAME
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " Texto TEXT NOT NULL);";
    static final String id = "id";
    static final String Texto = "Texto";
    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    static final String NOMBREPROVIDER = "com.example.chechis.profesor.ProviderContent";
    static final String URL = "content://" + NOMBREPROVIDER + "/cte";
    static final Uri CONTENEDORURI = Uri.parse(URL);
    private static HashMap<String, String> values;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(NOMBREPROVIDER, "cte", uriCode);
        uriMatcher.addURI(NOMBREPROVIDER, "cte/*", uriCode);
    }

    private static class BaseDatos extends SQLiteOpenHelper{

        BaseDatos(Context context){

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Sentencia);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);

        }
    }

    @Override
    public boolean onCreate() {

        Context context = getContext();
        BaseDatos dbHelper = new BaseDatos(context);
        db = dbHelper.getWritableDatabase();
        if (db != null) {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case uriCode:
                qb.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("Uri No soportada " + uri);
        }
        if (sortOrder == null || sortOrder == "") {
            sortOrder = Texto;
        }
        Cursor c = qb.query(db, projection, selection, selectionArgs, null,
                null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "vnd.android.cursor.dir/cte";
            default:
                throw new IllegalArgumentException("Uri no valida: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long rowID = db.insert(TABLE_NAME, "", contentValues);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENEDORURI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Error a agregar el registro " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int entero= 0;
        switch (uriMatcher.match(uri)){
            case uriCode:
                entero= db.delete(TABLE_NAME,s,strings);
                break;
            default:
                throw  new IllegalArgumentException("Uri desconocida ");
        }

        return entero;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, String selection,String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = db.update(TABLE_NAME, values, selection, selectionArgs);
                Toast.makeText(getContext(), "epa", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
