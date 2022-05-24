package com.alg.hozon_01.data;

import com.alg.hozon_01.ECategorias;
import com.alg.hozon_01.CategoriasDao;

public class PopulateCategorias implements Runnable {
    private AppRoomDatabase INSTANCE;

    public PopulateCategorias(AppRoomDatabase instance) {
        INSTANCE = instance;
    }

    @Override
    public void run() {
        // Populate the database in the background.
        // If you want to start with more words, just add them.
        CategoriasDao categoriasDao = INSTANCE.categoriasDao();
        //dao.deleteAll();
        //Añadimos categorias por defecto al crear la base de datos.
        ECategorias categoria = new ECategorias("Transporte");
        categoriasDao.insert(categoria);

        categoria = new ECategorias("Rutina");
        categoriasDao.insert(categoria);

        categoria = new ECategorias("Familia");
        categoriasDao.insert(categoria);

        categoria = new ECategorias("Alimentación");
        categoriasDao.insert(categoria);

        categoria = new ECategorias("Regalos");
        categoriasDao.insert(categoria);

        categoria = new ECategorias("Educación");
        categoriasDao.insert(categoria);

        categoria = new ECategorias("Café");
        categoriasDao.insert(categoria);

        categoria = new ECategorias("Casa");
        categoriasDao.insert(categoria);

        categoria = new ECategorias("Ocio");
        categoriasDao.insert(categoria);

        categoria = new ECategorias("Salud");
        categoriasDao.insert(categoria);


    }
}