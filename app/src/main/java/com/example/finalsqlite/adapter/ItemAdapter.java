package com.example.finalsqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.finalsqlite.R;
import com.example.finalsqlite.data.Item;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("PT","BR"));
    private List<Item> itens = new ArrayList<>();

    public ItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Item getItem(int i) {
        return itens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return itens.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_listitens, viewGroup, false);

        TextView txtTitulo = v.findViewById(R.id.txt_titulo);
        TextView txtPrioridade = v.findViewById(R.id.txt_prioridade);
        TextView txtDescricao = v.findViewById(R.id.txt_descricao);
        TextView txtCor = v.findViewById(R.id.txt_cor);


        Item item = itens.get(i);
        txtTitulo.setText(item.getTitulo());
        txtPrioridade.setText(nf.format(item.getPrioridade()));
        txtDescricao.setText(item.getDescricao());
        txtCor.setText(item.getCor());

        return v;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
        notifyDataSetChanged();
    }
}
