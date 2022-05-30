package com.alg.hozon_01.data;

import com.alg.hozon_01.CategoriasDao;
import com.alg.hozon_01.CuentasDao;
import com.alg.hozon_01.ECategorias;
import com.alg.hozon_01.ECuentas;
import com.alg.hozon_01.EMovimientos;
import com.alg.hozon_01.MovimientosDao;

import java.util.List;

public class PopulateMovimientos implements Runnable {
    private AppRoomDatabase INSTANCE;

    public PopulateMovimientos(AppRoomDatabase instance) {
        INSTANCE = instance;
    }

    @Override
    public void run() {

        MovimientosDao movimientosDao = INSTANCE.movimientosDao();
        //dao.deleteAll();
        //Añadimos categorias por defecto al crear la base de datos.
        EMovimientos movimiento = new EMovimientos(1,"Transporte","Prueba1",3);
        movimientosDao.insert(movimiento);
    }
}