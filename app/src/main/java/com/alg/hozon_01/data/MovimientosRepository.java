package com.alg.hozon_01.data;

import android.app.Application;

import com.alg.hozon_01.CuentasDao;
import com.alg.hozon_01.ECuentas;
import com.alg.hozon_01.EMovimientos;
import com.alg.hozon_01.MovimientosDao;

import java.util.List;

public class MovimientosRepository {
    private MovimientosDao movimientosDao;

    //Constructor
    public MovimientosRepository(Application application) {
        //DB Creation
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);

        //Obteniendo los DAO
        movimientosDao= db.movimientosDao();
    }

    public List<EMovimientos> getAllMovimientos() {
        return movimientosDao.getMovimientos();
    }

    public void insertMovimiento(EMovimientos movimiento) {
        movimientosDao.insert(movimiento);
    }
    public void getConceptoById(Integer id) {movimientosDao.conceptoById(id);}
    public void getCategoriasById(Integer id) {movimientosDao.categoriaById(id);}
    public void getGastoById(Integer id) {movimientosDao.gastoById(id);}
    // public void getFechaById(Integer id) {movimientosDao.fechaById(id);}

    public void deleteMovimiento(Integer id){
        movimientosDao.deleteMovimiento(id);
    }
    public void deleteAllCuentas(){
        movimientosDao.deleteAll();
    }
}