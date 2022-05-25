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
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuentasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuentasFragment extends Fragment {

    private RecyclerView rvCuentas;
    private CuentasAdapter cuentasAdapter;
    private Button btnAnadir;
    private EditText etNuevoCuenta;


    //Una referencia a categoriasViewModel
    private CuentasViewModel cuentasViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CuentasFragment() {
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
    public static CuentasFragment newInstance(String param1, String param2) {
        CuentasFragment fragment = new CuentasFragment();
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
        cuentasViewModel = new ViewModelProvider(getActivity()).get(CuentasViewModel.class);
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
        return inflater.inflate(R.layout.fragment_cuentas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNuevoCuenta = (EditText) getView().findViewById(R.id.etNuevoCuenta);

        btnAnadir = (Button) getView().findViewById(R.id.btnAnadir);
        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strCuenta = etNuevoCuenta.getText().toString();
                if (strCuenta.length()>0) {
                    if (!cuentasViewModel.findCuentasByName(strCuenta)) {
                        cuentasViewModel.addCuenta(new ECuentas(strCuenta,0));
                        etNuevoCuenta.setText("");
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

        rvCuentas = (RecyclerView) getView().findViewById(R.id.rvCuentas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvCuentas.setLayoutManager(layoutManager);
        //En el IngredientesAdapter queremos usar también el ingredientesViewModel por eso le pasamos
        //una referencia a la activity, para que pueda ponerla en el ViewModelProvider al instanciarlo
        cuentasAdapter = new CuentasAdapter(ECuentas.cuentaDiffCallback,getActivity());
        rvCuentas.setAdapter(cuentasAdapter);


        cuentasViewModel.ldCuentas.observe(getViewLifecycleOwner(), new Observer<List<ECuentas>>() {
            @Override
            public void onChanged(List<ECuentas> cuentas) {

                cuentasAdapter.submitList(cuentas);
            }
        });
    }
}