package com.alg.hozon_01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoriasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ECategorias categoria);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCategorias(ECategorias... categorias);

    @Query("DELETE FROM categorias")
    void deleteAll();

    //Con la Anotation @Delet podemos pasar un objeto al método que pongamos.
    //Room buscará ese objeto por clave primaria en la base de datos para eliminarlo
    @Delete
    void delete(ECategorias categoria);

    //Aquí para borrar utilizamos nostoros una query con parametro
    //La función recibirá ahora un string, no un objeto, y será el que ponemos como parametro en la query
    @Query("DELETE FROM categorias WHERE categoria = :categoria")
    void deleteCategoria(String categoria);

    @Query("SELECT * FROM categorias ORDER BY categoria ASC")
    List<ECategorias> getCategorias();

    @Query("DELETE FROM categorias where categoria = 'dummy'")
    void dummyDelete();

    @Query("SELECT * from categorias WHERE categoria LIKE :strCategoria")
    List<ECategorias> searchCategorias(String strCategoria);
}
