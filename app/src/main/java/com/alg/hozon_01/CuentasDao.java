package com.alg.hozon_01;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CuentasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ECuentas cuenta);

    @Query("DELETE FROM cuentas")
    void deleteAll();

    //Con la Anotation @Delet podemos pasar un objeto al método que pongamos.
    //Room buscará ese objeto por clave primaria en la base de datos para eliminarlo
    @Delete
    void delete(ECuentas cuenta);

    //Aquí para borrar utilizamos nostoros una query con parametro
    //La función recibirá ahora un string, no un objeto, y será el que ponemos como parametro en la query
    @Query("DELETE FROM cuentas WHERE nombre = :nombre")
    void deleteCuenta(String nombre);

    @Query("SELECT * FROM cuentas ORDER BY nombre ASC")
    List<ECuentas> getCuentas();

    @Query("SELECT * from cuentas WHERE nombre LIKE :strNombre")
    List<ECuentas> searchCuentas(String strNombre);

    @Query("SELECT saldo from cuentas WHERE nombre LIKE :strNombre")
    Integer getSaldoByCuenta(String strNombre);
}