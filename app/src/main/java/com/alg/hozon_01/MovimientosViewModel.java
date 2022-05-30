package com.alg.hozon_01;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.alg.hozon_01.data.CategoriasRepository;
import com.alg.hozon_01.data.CuentasRepository;
import com.alg.hozon_01.data.MovimientosRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovimientosViewModel extends AndroidViewModel {
    private MovimientosRepository movimientosRepository;
    private CuentasRepository cuentasRepository;
    private CategoriasRepository categoriasRepository;

    private static final ExecutorService repositoryExecutor = Executors.newFixedThreadPool(1);

    public MutableLiveData<List<EMovimientos>> ldMovimientos = new MutableLiveData<>();
    public MutableLiveData<List<EMovimientos>> ldCategorias = new MutableLiveData<>();

    private Application theApplication;

    public MovimientosViewModel(Application application) {
        super(application);
        movimientosRepository = new MovimientosRepository(application);
        ldMovimientos = new MutableLiveData<>();
        repositoryExecutor.execute(() -> {
            ldMovimientos.postValue(movimientosRepository.getAllMovimientos());
        });
    }
    public void deleteMovimiento(int position) {
        Integer Id = ldMovimientos.getValue().get(position).getId();
        repositoryExecutor.execute( () -> {
            movimientosRepository.deleteMovimiento(Id);
            ldMovimientos.postValue(movimientosRepository.getAllMovimientos());
        });
    }

    public void addMovimiento(EMovimientos movimiento) {
        repositoryExecutor.execute( () -> {
            movimientosRepository.insertMovimiento(movimiento);
            ldMovimientos.postValue(movimientosRepository.getAllMovimientos());
        });
    }
    public boolean findMovimientoByName(String movimientoName) {

        boolean retVal=false;
        if (ldMovimientos.getValue() != null) {
            for (EMovimientos movimiento : ldMovimientos.getValue()) {
                if (movimiento.toString().equals(movimientoName)){
                    retVal = true;
                    break;
                }
            }
        }
        else {
            retVal=false;
        }
        return retVal;
    }
    //-------------------------------


}