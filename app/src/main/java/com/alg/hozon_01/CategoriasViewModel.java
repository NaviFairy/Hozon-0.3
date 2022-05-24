package com.alg.hozon_01;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alg.hozon_01.ECategorias;
import com.alg.hozon_01.data.CategoriasRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoriasViewModel extends AndroidViewModel {

    private CategoriasRepository categoriasRepository;

    private static final ExecutorService repositoryExecutor = Executors.newFixedThreadPool(1);

    public MutableLiveData<List<ECategorias>> ldCategorias;

    public CategoriasViewModel(Application application) {
        super(application);
        categoriasRepository = new CategoriasRepository(application);
        ldCategorias = new MutableLiveData<>();
        repositoryExecutor.execute( () -> {
            ldCategorias.postValue(categoriasRepository.getAllCategorias());
        });
    }

    public void initList(String[] arrayCategorias) {
        repositoryExecutor.execute( () -> {
            categoriasRepository.deleteAllCategorias();
            categoriasRepository.insertCategorias(arrayCategorias);
            ldCategorias.postValue(categoriasRepository.getAllCategorias());
        });
    }

    public void forceDBCreation() {
        repositoryExecutor.execute( () -> {
           categoriasRepository.forceDBCreation();
        });
    }

    public void deleteCategoria(int position) {
        String strCategoria = ldCategorias.getValue().get(position).getStrCategoria();
        repositoryExecutor.execute( () -> {
           categoriasRepository.deleteCategoria(strCategoria);
            ldCategorias.postValue(categoriasRepository.getAllCategorias());
        });
    }

    public void addCategoria(ECategorias categoria) {
        repositoryExecutor.execute( () -> {
            categoriasRepository.insertCategoria(categoria);
            ldCategorias.postValue(categoriasRepository.getAllCategorias());
        });
    }
    public boolean findCategoriaByName(String ingredienteName) {
        //NO necesitamos acceder a la base de datos,
        //se supone que la lista ldIngredientes esta actualizada
        boolean retVal=false;
        if (ldCategorias.getValue() != null) {
            for (ECategorias categoria : ldCategorias.getValue()) {
                if (categoria.toString().equals(ingredienteName)){
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
