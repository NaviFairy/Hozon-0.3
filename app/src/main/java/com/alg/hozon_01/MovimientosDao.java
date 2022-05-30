package com.alg.hozon_01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface MovimientosDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(EMovimientos movimiento);

    @Query("DELETE FROM movimientos")
    void deleteAll();

    //Con la Anotation @Delet podemos pasar un objeto al método que pongamos.
    //Room buscará ese objeto por clave primaria en la base de datos para eliminarlo
    @Delete
    void delete(EMovimientos movimiento);

    //Aquí para borrar utilizamos nostoros una query con parametro
    //La función recibirá ahora un string, no un objeto, y será el que ponemos como parametro en la query
    @Query("DELETE FROM movimientos WHERE id = :id")
    void deleteMovimiento(Integer id);

    @Query("SELECT * FROM movimientos ORDER BY id ASC")
    List<EMovimientos> getMovimientos();

    @Query("SELECT * from movimientos WHERE concepto LIKE :strConcepto")
    List<EMovimientos> searchMovimientosByConcepto(String strConcepto);

    @Query("SELECT gasto from movimientos WHERE concepto LIKE :strConcepto")
    Integer getGastoByConcepto(String strConcepto);

    @Query("SELECT concepto FROM movimientos WHERE id = :id")
    String conceptoById(Integer id);

    @Query("SELECT categoria_mov FROM movimientos WHERE id = :id")
    String categoriaById(Integer id);

    @Query("SELECT gasto FROM movimientos WHERE id = :id")
    Integer gastoById(Integer id);

    // @Query("SELECT fecha FROM movimientos WHERE id = :id")
    // Date fechaById(Integer id);
}