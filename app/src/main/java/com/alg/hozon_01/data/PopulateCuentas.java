package com.alg.hozon_01.data;

import com.alg.hozon_01.CuentasDao;
import com.alg.hozon_01.ECuentas;

public class PopulateCuentas implements Runnable {
    private AppRoomDatabase INSTANCE;

    public PopulateCuentas(AppRoomDatabase instance) {
        INSTANCE = instance;
    }

    @Override
    public void run() {
        // Populate the database in the background.
        // If you want to start with more words, just add them.
        CuentasDao cuentasDao = INSTANCE.cuentasDao();
        //dao.deleteAll();
        //Añadimos categorias por defecto al crear la base de datos.
        ECuentas cuenta = new ECuentas("Cuenta 1",0);
        cuentasDao.insert(cuenta);
    }
}