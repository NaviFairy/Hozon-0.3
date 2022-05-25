package com.alg.hozon_01;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "cuentas")
public class ECuentas implements Comparable<ECuentas> {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String strNombre;

    @NonNull
    @ColumnInfo(name = "saldo")
    private Integer IntSaldo;

    public ECuentas(@NonNull String strNombre,Integer IntSaldo) {
        this.strNombre = strNombre;
        this.IntSaldo = IntSaldo;
    }

    @Override
    public String toString() {
        return getStrNombre();
    }

    public String getStrNombre(){
        return strNombre;
    }

    public void setNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public void setSaldo(Integer IntSaldo) {
        this.IntSaldo = IntSaldo;
    }



    @Override
    public int compareTo(ECuentas cuenta) {
        return strNombre.compareTo(cuenta.toString());
    }



    public static DiffUtil.ItemCallback<ECuentas> cuentaDiffCallback = new DiffUtil.ItemCallback<ECuentas>() {
        @Override
        public boolean areItemsTheSame(@NonNull ECuentas oldItem, @NonNull ECuentas newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
        @Override
        public boolean areContentsTheSame(@NonNull ECuentas oldItem, @NonNull ECuentas newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    };

    @NonNull
    public Integer getIntSaldo() {
        return IntSaldo;
    }

    public void setIntSaldo(@NonNull Integer intSaldo) {
        IntSaldo = intSaldo;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }
}
