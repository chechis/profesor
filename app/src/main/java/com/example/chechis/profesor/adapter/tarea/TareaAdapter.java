package com.example.chechis.profesor.adapter.tarea;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chechis.profesor.R;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaHolder>{


    private Context context;
    private List<Tarea> tareas;

    public TareaAdapter(Context context, List<Tarea> tareas) {
        this.context = context;
        this.tareas = tareas;
    }

    protected class TareaHolder extends RecyclerView.ViewHolder{
        protected TextView txtId, txtTarea, txtNota;

        public TareaHolder (View itemView){
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.txt_tarea_id);
            txtTarea = (TextView) itemView.findViewById(R.id.txt_tarea_tarea);
            txtNota = (TextView) itemView.findViewById(R.id.txt_tarea_nota);
        }

    }

    @Override
    public TareaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.plantilla_tarea, parent, false);

        return new TareaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TareaAdapter.TareaHolder holder, int position) {

        holder.txtId.setText(tareas.get(position).getId());
        holder.txtTarea.setText(tareas.get(position).getTarea());
        holder.txtNota.setText(tareas.get(position).getNota());

    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }
}
