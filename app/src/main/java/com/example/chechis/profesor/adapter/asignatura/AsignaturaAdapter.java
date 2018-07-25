package com.example.chechis.profesor.adapter.asignatura;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chechis.profesor.R;

import java.util.List;

public class AsignaturaAdapter extends RecyclerView.Adapter<AsignaturaAdapter.AsignaturaHolder>{

    private Context context;
    private List<Asignatura> asignaturas;

    public AsignaturaAdapter(Context context, List<Asignatura> asignaturas) {
        this.context = context;
        this.asignaturas = asignaturas;
    }

    protected class AsignaturaHolder extends RecyclerView.ViewHolder{

        protected TextView txtId, txtAsignatura;

        public AsignaturaHolder (View itemView){
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.txt_asignatura_id);
            txtAsignatura = (TextView) itemView.findViewById(R.id.txt_asignatura_asignatura);
        }
    }

    @Override
    public AsignaturaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.plantilla_asignatura, parent, false);

        return new AsignaturaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AsignaturaHolder holder, int position) {

        holder.txtId.setText("Id :" +asignaturas.get(position).getId());
        holder.txtAsignatura.setText("Asignatura :"+asignaturas.get(position).getAsignatura());

    }

    @Override
    public int getItemCount() {
        return asignaturas.size();
    }
}
