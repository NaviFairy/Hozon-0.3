package com.alg.hozon_01.data;


import android.app.Application;

import com.alg.hozon_01.CuentasDao;
import com.alg.hozon_01.ECategorias;
import com.alg.hozon_01.ECuentas;

import java.util.List;

public class CuentasRepository {
    private CuentasDao cuentasDao;

    //Constructor
    public CuentasRepository(Application application) {
        //DB Creation
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);

        //Obteniendo los DAO
        cuentasDao= db.cuentasDao();
    }

    public List<ECuentas> getAllCuentas() {
        return cuentasDao.getCuentas();
    }

    public void insertCuenta(ECuentas cuenta) {
        cuentasDao.insert(cuenta);
    }

    public void getSaldoByCuenta(String strNombre) {cuentasDao.getSaldoByCuenta(strNombre);}

    public void deleteCuenta(String strNombre){
        cuentasDao.deleteCuenta(strNombre);
    }
    public void deleteAllCuentas(){
        cuentasDao.deleteAll();
    }
}