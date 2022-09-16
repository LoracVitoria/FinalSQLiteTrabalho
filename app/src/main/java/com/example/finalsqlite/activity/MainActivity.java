package com.example.finalsqlite.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.finalsqlite.R;
import com.example.finalsqlite.adapter.ItemAdapter;
import com.example.finalsqlite.data.Item;
import com.example.finalsqlite.data.ItemDAO;
import com.example.finalsqlite.dialog.DeleteDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.GenericArrayType;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, DeleteDialog.OnDeleteListener, View.OnClickListener {

    private ListView lista;
    private ItemAdapter adapter;
    private ItemDAO itemDAO;
    private FloatingActionButton fabAdd;
    private static final int REQ_EDIT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.lista);

        adapter = new ItemAdapter(this);
        fabAdd = findViewById(R.id.action_add);
        fabAdd.setOnClickListener(this);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(this);
        lista.setOnItemLongClickListener(this);

        itemDAO = ItemDAO.getInstance(this);

        updateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_EDIT && resultCode == RESULT_OK) {
            updateList();
        }
    }

    private void updateList() {
        List<Item> items = itemDAO.list();
        adapter.setItens(items);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
        intent.putExtra("item", adapter.getItem(i));
        startActivityForResult(intent, REQ_EDIT);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Item item = adapter.getItem(i);

        DeleteDialog dialog = new DeleteDialog();
        dialog.setItem(item);
        dialog.show(getSupportFragmentManager(), "deleteDialog");
        return true;
    }

    @Override
    public void onDelete(Item item) {
        itemDAO.delete(item);
        updateList();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
        startActivityForResult(intent, REQ_EDIT);
    }
}