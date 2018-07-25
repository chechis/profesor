package com.example.chechis.profesor.adapter.profesor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chechis.profesor.R;

import java.util.List;

public class ProfesorAdapter extends RecyclerView.Adapter<ProfesorAdapter.ProfesorHolder> {

    private Context context;
    private List<Profesor> profesores;

    public ProfesorAdapter(Context context, List<Profesor> profesores) {
        this.context = context;
        this.profesores = profesores;
    }

    protected class ProfesorHolder extends RecyclerView.ViewHolder{
        protected TextView txtId, txtUsuario, txtContrasena, txtRol;

        public ProfesorHolder (View itemView){
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.txt_profesor_id);
            txtUsuario = (TextView) itemView.findViewById(R.id.txt_profesor_usuario);
            txtContrasena = (TextView) itemView.findViewById(R.id.txt_profesor_contrasena);
            txtRol = (TextView) itemView.findViewById(R.id.txt_profesor_rol);

        }

    }

    @Override
    public ProfesorHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.plantilla_profesor, parent, false);

        return new ProfesorHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfesorHolder holder, int position) {

        holder.txtId.setText("Id :"+profesores.get(position).getId());
        holder.txtUsuario.setText("Nombre :"+profesores.get(position).getNombreUsuario());
        holder.txtContrasena.setText("Contraseña :"+profesores.get(position).getContrasena());
        holder.txtRol.setText("Rol :"+profesores.get(position).getRol());

    }

    @Override
    public int getItemCount() {
        return profesores.size();
    }
}
