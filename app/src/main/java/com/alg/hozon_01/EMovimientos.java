package com.alg.hozon_01;

import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "movimientos", foreignKeys = {
        @ForeignKey(entity = ECuentas.class,
                parentColumns = "id",
                childColumns = "id_cuenta",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = ECategorias.class,
                parentColumns = "categoria",
                childColumns = "categoria_mov",
                onDelete = ForeignKey.CASCADE),
})
public class EMovimientos {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "id_cuenta")
    private Integer id_cuenta;
    //public porque si pongo private no funciona
    @NonNull
    @ColumnInfo(name = "categoria_mov")
    public String categoria_mov;
    //public porque si pongo private no funciona
    @NonNull
    @ColumnInfo(name = "concepto")
    private String strConcepto;

    @NonNull
    @ColumnInfo(name = "gasto")
    private Integer IntGasto;

    //  @NonNull
    //   @ColumnInfo(name = "fecha")
    //  @TypeConverters(DateConverter.class)
    //   public Date fecha;


    public EMovimientos(@NonNull Integer id_cuenta,String categoria_mov, String strConcepto,Integer IntGasto) {
        this.id_cuenta = id_cuenta;
        this.categoria_mov = categoria_mov;
        this.strConcepto = strConcepto;
        this.IntGasto = IntGasto;
        //  this.fecha = fecha;
    }

    @Override
    public String toString() {
        return getStrConcepto();
    }

    public String getStrConcepto(){
        return strConcepto;
    }

    public void setConcepto(String strConcepto) {
        this.strConcepto = strConcepto;
    }

    public void setSaldo(Integer IntSaldo) {
        this.IntGasto = IntGasto;
    }

    public static DiffUtil.ItemCallback<EMovimientos> movimientosDiffCallback = new DiffUtil.ItemCallback<EMovimientos>() {
        @Override
        public boolean areItemsTheSame(@NonNull EMovimientos oldItem, @NonNull EMovimientos newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
        @Override
        public boolean areContentsTheSame(@NonNull EMovimientos oldItem, @NonNull EMovimientos newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    };

    @NonNull
    public Integer getIntGasto() {
        return IntGasto;
    }

    public void setIntGasto(@NonNull Integer intGasto) {
        IntGasto= intGasto;
    }

    @NonNull
    public Integer getId() {
        return id;
    }
    @NonNull
    public Integer getId_cuenta() { return id_cuenta;}
    @NonNull
    public String getCategoria() { return categoria_mov;}
    @NonNull
    //paso el tipo date a string para poder representarlo
    //  public String getFecha() { return fecha.toString();}

    public void setId(@NonNull Integer id) {
        this.id = id;
    }
}