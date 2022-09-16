package com.example.finalsqlite.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalsqlite.R;
import com.example.finalsqlite.data.Item;
import com.example.finalsqlite.data.ItemDAO;

import java.util.Arrays;
import java.util.List;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private EditText edtTitulo;
    private EditText edtPrioridade;
    private EditText edtDescricao;
    private Spinner spnCor;
    private Button btnProcessar;
    private Button btnCancelar;
    private Item item;
    private ItemDAO itemDAO;                    //red   laranja     amarelo       verde       azul    roxo
    private List<String> cores = Arrays.asList("#f33634", "#fe9134", "#f0e801", "#3a9140", "#0048ff", "#7c109a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_item);

        edtTitulo = findViewById(R.id.edt_titulo);
        edtPrioridade = findViewById(R.id.edt_prioridade);
        edtDescricao = findViewById(R.id.edt_descricao);
        spnCor = (Spinner) findViewById(R.id.spn_cor);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cores, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCor.setAdapter(adapter);
        btnProcessar = findViewById(R.id.btnProcessar);
        btnCancelar = findViewById(R.id.btnCancelar);
        spnCor.setOnItemSelectedListener(this);
        btnProcessar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        itemDAO = ItemDAO.getInstance(this);

        item = (Item) getIntent().getSerializableExtra("item");

        if (item != null) {
            edtTitulo.setText(item.getTitulo());
            edtPrioridade.setText(String.valueOf(item.getPrioridade()));
            edtDescricao.setText(item.getDescricao());
//            spnCor.setText(item.getCor());
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnProcessar) {
            String titulo = edtTitulo.getText().toString();
            Integer prioridade = Integer.parseInt(edtPrioridade.getText().toString());
            String descricao = edtDescricao.getText().toString();
            String cor = spnCor.getSelectedItem().toString();
            String msg;

            if (item == null) {
                Item item = new Item(titulo, prioridade, descricao, cor);
                itemDAO.save(item);
                msg = "Item gravado com ID = " + item.getId();

            } else {
                item.setTitulo(titulo);
                item.setPrioridade(prioridade);
                item.setDescricao(descricao);
                item.setCor(cor);

                itemDAO.update(item);
                msg = "Item atualizado com ID = " + item.getId();
            }

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else if (view.getId() == R.id.btnCancelar) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}