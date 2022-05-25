package com.alg.hozon_01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class CuentasAdapter extends ListAdapter<ECuentas, CuentasAdapter.CuentasViewHolder> {
    // Referencia a la default Factory de la App, a usar cuando el ViewModel no recibe parámetros y usando su constructor por defecto
    private ViewModelProvider.AndroidViewModelFactory theAppFactory;

    //Declaramos una referencia al ingredientesViewModel
    private CuentasViewModel cuentasViewModel;

    protected CuentasAdapter(@NonNull DiffUtil.ItemCallback<ECuentas> diffCallback, ViewModelStoreOwner owner) {
        super(diffCallback);
        //Instanciamos el ingredientesViewModel
        //  El segundo parametro que recibimos debe ser el objeto en donde se creo por primera vez el ViewModel
        //     En este ejemplo es el  IngredientesFragment porque en el se instancia por primara vez
        //  Un (ViewModelStoreOwner) es un objeto con ciclo de vida (una Activity o un Fragment).
        cuentasViewModel = new ViewModelProvider(owner).get(CuentasViewModel.class);
    }

    @NonNull
    @Override
    public CuentasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.cuentas_row_layout, parent, false);
        return new CuentasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuentasViewHolder holder, int position) {
        ECuentas cuenta = getCurrentList().get(position);
        if (cuenta != null){
            holder.bind(cuenta.toString(),cuenta.getIntSaldo());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Seleccionado: " + cuenta.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CuentasViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCuentaItem;
        private TextView tvGastos;
        private ImageButton ibDelete;


        public CuentasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCuentaItem = itemView.findViewById(R.id.tvCuentasItem);
            tvGastos = itemView.findViewById(R.id.tvGastos);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cuentasViewModel.deleteCuenta(getLayoutPosition());
                }
            });
        }

        public void bind(String string,Integer integer) {
            tvCuentaItem.setText(string);
            tvGastos.setText(String.valueOf(integer));
        }

    }

}