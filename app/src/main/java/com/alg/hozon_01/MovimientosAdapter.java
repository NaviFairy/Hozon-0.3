package com.alg.hozon_01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.alg.hozon_01.data.CuentasRepository;

public class MovimientosAdapter extends ListAdapter<EMovimientos, MovimientosAdapter.MovimientosViewHolder> {
    // Referencia a la default Factory de la App, a usar cuando el ViewModel no recibe parámetros y usando su constructor por defecto
    private ViewModelProvider.AndroidViewModelFactory theAppFactory;

    //Declaramos una referencia al ingredientesViewModel
    private MovimientosViewModel movimientosViewModel;

    protected MovimientosAdapter(@NonNull DiffUtil.ItemCallback<EMovimientos> diffCallback, ViewModelStoreOwner owner) {
        super(diffCallback);
        //Instanciamos el ingredientesViewModel
        //  El segundo parametro que recibimos debe ser el objeto en donde se creo por primera vez el ViewModel
        //     En este ejemplo es el  IngredientesFragment porque en el se instancia por primara vez
        //  Un (ViewModelStoreOwner) es un objeto con ciclo de vida (una Activity o un Fragment).
        movimientosViewModel = new ViewModelProvider(owner).get(MovimientosViewModel.class);
    }

    @NonNull
    @Override
    public MovimientosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.movimiento_row_layout, parent, false);
        return new MovimientosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovimientosViewHolder holder, int position) {
        EMovimientos movimiento = getCurrentList().get(position);
        if (movimiento != null){
            holder.bind(movimiento.getStrConcepto(), movimiento.getCategoria(), movimiento.getIntGasto());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Seleccionado: " + movimiento.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MovimientosViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMovimientoItem;
        private TextView tvCargo;
        private TextView tvCategoria;
        private ImageButton ibDelete;
        private Spinner spinnerCuentas;
        private Spinner spinnerCategorias;

        public MovimientosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovimientoItem = itemView.findViewById(R.id.tvMovimientoItem);
            tvCargo = itemView.findViewById(R.id.tvCargo);
            tvCategoria = itemView.findViewById(R.id.tvCategoria);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            spinnerCuentas = itemView.findViewById(R.id.spinnerCuentas);
            spinnerCategorias = itemView.findViewById(R.id.spinnerCategorias);;

            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movimientosViewModel.deleteMovimiento(getLayoutPosition());
                }
            });
        }

        public void bind(String string,String string2,Integer integer) {
            tvMovimientoItem.setText(string);
            tvCategoria.setText(string2);
            tvCargo.setText(String.valueOf(integer));

        }

    }

}