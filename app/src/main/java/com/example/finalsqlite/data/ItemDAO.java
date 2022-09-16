package com.example.finalsqlite.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private static ItemDAO instance;

    private SQLiteDatabase db;

    private ItemDAO(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    //singleton
    public static ItemDAO getInstance(Context context) {
        if (instance == null) {
            instance = new ItemDAO(context.getApplicationContext());
        }
        return instance;
    }

    public List<Item> list() {

        String[] columns = {
                ItensContract.Columns._ID,
                ItensContract.Columns.TITULO,
                ItensContract.Columns.PRIORIDADE,
                ItensContract.Columns.DESCRICAO,
                ItensContract.Columns.COR
        };

        List<Item> itens = new ArrayList<>();

        try (Cursor c = db.query(ItensContract.TABLE_NAME, columns, null, null, null, null, ItensContract.Columns.TITULO)) {
            if (c.moveToFirst()) {
                do {
                    Item p = ItemDAO.fromCursor(c);
                    itens.add(p);
                } while (c.moveToNext());
            }
            return itens;
        }
    }

    private static Item fromCursor(Cursor c) {
        @SuppressLint("Range") int id = c.getInt(c.getColumnIndex(ItensContract.Columns._ID));
        @SuppressLint("Range") String titulo = c.getString(c.getColumnIndex(ItensContract.Columns.TITULO));
        @SuppressLint("Range") Integer prioridade = c.getInt(c.getColumnIndex(ItensContract.Columns.PRIORIDADE));
        @SuppressLint("Range") String descricao = c.getString(c.getColumnIndex(ItensContract.Columns.DESCRICAO));
        @SuppressLint("Range") String cor = c.getString(c.getColumnIndex(ItensContract.Columns.COR));

        return new Item(id, titulo, prioridade, descricao, cor);
    }

    public void save(Item item) {
        ContentValues values = new ContentValues();
        values.put(ItensContract.Columns.TITULO, item.getTitulo());
        values.put(ItensContract.Columns.PRIORIDADE, item.getPrioridade());
        values.put(ItensContract.Columns.DESCRICAO, item.getDescricao());
        values.put(ItensContract.Columns.COR, item.getCor());

        long id = db.insert(ItensContract.TABLE_NAME, null, values);
        item.setId((int) id);
    }

    public void update(Item item) {
        ContentValues values = new ContentValues();
        values.put(ItensContract.Columns.TITULO, item.getTitulo());
        values.put(ItensContract.Columns.PRIORIDADE, item.getPrioridade());
        values.put(ItensContract.Columns.DESCRICAO, item.getDescricao());
        values.put(ItensContract.Columns.COR, item.getCor());

        db.update(ItensContract.TABLE_NAME, values, ItensContract.Columns._ID + " = ?", new String[]{ String.valueOf(item.getId()) });
    }

    public void delete(Item item) {
        db.delete(ItensContract.TABLE_NAME, ItensContract.Columns._ID + " = ?", new String[]{ String.valueOf(item.getId()) });
    }
}
