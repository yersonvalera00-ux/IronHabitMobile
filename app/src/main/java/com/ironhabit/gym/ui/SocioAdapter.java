package com.ironhabit.gym.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ironhabit.gym.R;
import com.ironhabit.gym.model.Socio;
import com.ironhabit.gym.util.CalculadoraIMC;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SocioAdapter extends RecyclerView.Adapter<SocioAdapter.SocioViewHolder> {

    private List<Socio> datos = new ArrayList<>();

    public void setDatos(List<Socio> nuevos) {
        this.datos = nuevos;
        notifyDataSetChanged(); // para listas grandes usar DiffUtil
    }

    @NonNull
    @Override
    public SocioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_socio, parent, false);
        return new SocioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SocioViewHolder holder, int position) {
        holder.bind(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    static class SocioViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvIniciales, tvNombre, tvMembresia, tvImc;

        SocioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIniciales = itemView.findViewById(R.id.tvIniciales);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvMembresia = itemView.findViewById(R.id.tvMembresia);
            tvImc = itemView.findViewById(R.id.tvImc);
        }

        void bind(Socio s) {
            tvIniciales.setText(s.getIniciales());
            tvNombre.setText(s.getNombre());
            tvMembresia.setText(s.getMembresia());

            if (s.getPeso() <= 0 || s.getEstaturaCm() <= 0) {
                tvImc.setText("Sin ficha");
            } else {
                double imc = CalculadoraIMC.calcular(s.getPeso(), s.getEstaturaCm());
                tvImc.setText(String.format(Locale.US, "IMC %.1f · %s",
                        imc, CalculadoraIMC.clasificar(imc)));
            }
        }
    }
}