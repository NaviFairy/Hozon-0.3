package com.alg.hozon_01;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.alg.hozon_01.data.CategoriasRepository;
import com.alg.hozon_01.data.CuentasRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CuentasViewModel extends AndroidViewModel {
    private CuentasRepository cuentasRepository;

    private static final ExecutorService repositoryExecutor = Executors.newFixedThreadPool(1);

    public MutableLiveData<List<ECuentas>> ldCuentas = new MutableLiveData<>();

    private Application theApplication;

    public CuentasViewModel(Application application) {
        super(application);
        cuentasRepository = new CuentasRepository(application);
        ldCuentas = new MutableLiveData<>();
        repositoryExecutor.execute( () -> {
            ldCuentas.postValue(cuentasRepository.getAllCuentas());
        });
    }

    public void deleteCuenta(int position) {
        String strNombre = ldCuentas.getValue().get(position).getStrNombre();
        repositoryExecutor.execute( () -> {
            cuentasRepository.deleteCuenta(strNombre);
            ldCuentas.postValue(cuentasRepository.getAllCuentas());
        });
    }

    public void addCuenta(ECuentas cuenta) {
        repositoryExecutor.execute( () -> {
            cuentasRepository.insertCuenta(cuenta);
            ldCuentas.postValue(cuentasRepository.getAllCuentas());
        });
    }
    public boolean findCuentasByName(String cuentasName) {

        boolean retVal=false;
        if (ldCuentas.getValue() != null) {
            for (ECuentas cuenta : ldCuentas.getValue()) {
                if (cuenta.toString().equals(cuentasName)){
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
