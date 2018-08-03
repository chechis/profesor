package com.example.chechis.profesor;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
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
