package com.alg.hozon_01;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alg.hozon_01.data.CategoriasRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuentasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WellcomeFragment extends Fragment {

    private RecyclerView rvMovimientos;
    private MovimientosAdapter movimientosAdapter;
    private CategoriasRepository categoriasRepository;
    private Button btnAnadir;
    private EditText etConceptoMovimiento;
    private Spinner spinnerCuentas;
    private Spinner spinnerCategorias;
    List <String> listaCategorias;


    //Una referencia a categoriasViewModel
    private MovimientosViewModel movimientosViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WellcomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IngredientesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WellcomeFragment newInstance(String param1, String param2) {
        WellcomeFragment fragment = new WellcomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        //El owner es un ViewModelStoreOwner que es un objeto con ciclo de vida es decir una Activity o un Fragment
        //en este caso estamos en un Fragment, por eso pasamos this a ViewModelProvider
        movimientosViewModel = new ViewModelProvider(getActivity()).get(MovimientosViewModel.class);
        //Aquí instanciamos por primera vez el ingredientesViewModel, es decir, lo ubicamos en el Fragment
        //Cualquier otra referencia debe acceder a esta instacia de ingredientesViewModel
        //para ello tendrá que pasar a ViewModelProvider una referencia al Fragment.

//        //Si la lista de ingredientes no ha sido cargada todavía en el ViewModel (por una ejecución anterior de este fragment, por ejemplo)
//        if (ingredientesViewModel.getIngredientes().getValue() == null) {
//            String[] theArray = getResources().getStringArray(R.array.ingredientes_array);
//            ingredientesViewModel.initList(theArray);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wellcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etConceptoMovimiento = (EditText) getView().findViewById(R.id.etConceptoMovimiento);
        spinnerCuentas = (Spinner) getView().findViewById(R.id.spinnerCuentas);
        spinnerCategorias = (Spinner) getView().findViewById(R.id.spinnerCategorias);
        btnAnadir = (Button) getView().findViewById(R.id.btnAnadir);

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strMovimiento = etConceptoMovimiento.getText().toString();
                if (strMovimiento.length()>0) {
                    if (!movimientosViewModel.findMovimientoByName(strMovimiento)) {
                     //   movimientosViewModel.addMovimiento(new EMovimientos(,0));
                        etConceptoMovimiento.setText("");
                    }
                    else{
                        Toast.makeText(getContext(),"El nombre de la cuenta ya existe",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getContext(),"Debe escribir un nuevo nombre para la cuenta",Toast.LENGTH_LONG).show();
                }
            }
        });

        rvMovimientos = (RecyclerView) getView().findViewById(R.id.rvMovimientos);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMovimientos.setLayoutManager(layoutManager);
        //En el IngredientesAdapter queremos usar también el ingredientesViewModel por eso le pasamos
        //una referencia a la activity, para que pueda ponerla en el ViewModelProvider al instanciarlo
        movimientosAdapter = new MovimientosAdapter(EMovimientos.movimientosDiffCallback,getActivity());
        rvMovimientos.setAdapter(movimientosAdapter);


        movimientosViewModel.ldMovimientos.observe(getViewLifecycleOwner(), new Observer<List<EMovimientos>>() {
            @Override
            public void onChanged(List<EMovimientos> movimientos) {

                movimientosAdapter.submitList(movimientos);
            }
        });
    }
}