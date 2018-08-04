package com.example.chechis.profesor;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.chechis.profesor.almacenamiento.BaseDatos;
import com.example.chechis.profesor.almacenamiento.Estructura;

import java.util.HashMap;

public class ProveedorContenidos extends ContentProvider {

    private SQLiteDatabase sq;
    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    static final String NOMBREPROVIDER = "com.example.chechis.profesor.ProveedorContenidos";
    static final String URL = "content://" + NOMBREPROVIDER + "/cte";
    static final Uri CONTENEDORURI = Uri.parse(URL);
    private static HashMap<String, String> values;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(NOMBREPROVIDER, "cte", uriCode);
        uriMatcher.addURI(NOMBREPROVIDER, "cte/*", uriCode);
    }


    @Override
    public boolean onCreate() {

        Context context = getContext();
        BaseDatos baseDatos = new BaseDatos(context);
        sq = baseDatos.getWritableDatabase();
        if (sq != null){
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(Estructura.EstructuraBase.TABLE_NAME);

        switch (uriMatcher.match(uri)){
            case uriCode:
                qb.setProjectionMap(values);
                break;
                default:
                    throw new IllegalArgumentException("Uri no soportada " + uri);
        }
        if (sortOrder == null || sortOrder == ""){
            sortOrder = Estructura.EstructuraBase.COLUMN_NAME_TAREA;
        }
        Cursor c = qb.query(sq, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case uriCode:
                return "vnd.android.cursor.dir/cte";
            default:
                throw new IllegalArgumentException("Uri no valida: "+ uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long rowID = sq.insert(Estructura.EstructuraBase.TABLE_NAME, "", contentValues);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENEDORURI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Error a agregar el registro " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
