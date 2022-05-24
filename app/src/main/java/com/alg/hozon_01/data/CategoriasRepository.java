package com.alg.hozon_01.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.alg.hozon_01.ECategorias;
import com.alg.hozon_01.CategoriasDao;

import java.util.List;

public class CategoriasRepository {
    //Ingredientes Specifics --------------------------------------
    private CategoriasDao categoriasDao;

    //Constructor
    public CategoriasRepository(Application application) {
        //DB Creation
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);

        //Obteniendo los DAO
        categoriasDao= db.categoriasDao();
    }

    public List<ECategorias> getAllCategorias() {
        return categoriasDao.getCategorias();
    }

    public void insertCategoria(ECategorias categoria) {
        categoriasDao.insert(categoria);
    }

    public void insertCategorias(String[] categorias) {
        for (int i = 0; i < categorias.length; i++) {
            categoriasDao.insert(new ECategorias(categorias[i]));
        }
    }

    public void deleteCategoria(String strCategoria){
        categoriasDao.deleteCategoria(strCategoria);
    }

    public void deleteAllCategorias(){
        categoriasDao.deleteAll();
    }

    public void forceDBCreation(){
        categoriasDao.dummyDelete();
    }
}