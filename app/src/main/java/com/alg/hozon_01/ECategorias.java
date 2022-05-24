package com.alg.hozon_01;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "categorias")
public class ECategorias implements Comparable<ECategorias> {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "categoria")
    private String strCategoria;

    public ECategorias(@NonNull String strCategoria) {
        this.strCategoria = strCategoria;
    }

    @Override
    public String toString() {
        return getStrCategoria();
    }

    public String getStrCategoria(){
        return strCategoria;
    }

    @Override
    public int compareTo(ECategorias categoria) {
        return strCategoria.compareTo(categoria.toString());
    }

    public void setName(String strCategoria) {
        this.strCategoria = strCategoria;
    }

    public static DiffUtil.ItemCallback<ECategorias> categoriaDiffCallback = new DiffUtil.ItemCallback<ECategorias>() {
        @Override
        public boolean areItemsTheSame(@NonNull ECategorias oldItem, @NonNull ECategorias newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
        @Override
        public boolean areContentsTheSame(@NonNull ECategorias oldItem, @NonNull ECategorias newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    };

}
