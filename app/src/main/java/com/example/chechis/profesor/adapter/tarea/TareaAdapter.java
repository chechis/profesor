package com.example.chechis.profesor.adapter.tarea;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chechis.profesor.R;

import org.w3c.dom.Text;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaHolder>{


    private Context context;
    private TareaListener tareaListener;
    public interface TareaListener{
        void deleteTarea(int position);
        void editTarea(int position);

    }
    private List<Tarea> tareas;

    public TareaAdapter(Context context, List<Tarea> tareas) {
        this.context = context;
        this.tareas = tareas;
    }

    protected class TareaHolder extends RecyclerView.ViewHolder{
        protected TextView  txtTarea, txtEstudiante, txtAsignatura ,txtNota;
        protected ImageButton btnEditar, btnBorrar;

        public TareaHolder (View itemView){
            super(itemView);

            txtTarea = (TextView) itemView.findViewById(R.id.txt_tarea_tarea);
            txtEstudiante = (TextView) itemView.findViewById(R.id.txt_tarea_estudiante);
            txtAsignatura = (TextView) itemView.findViewById(R.id.txt_tarea_asignatura);
            txtNota = (TextView) itemView.findViewById(R.id.txt_tarea_nota);

            btnEditar = (ImageButton) itemView.findViewById(R.id.btn_edit);
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tareaListener != null){
                        tareaListener.editTarea(getAdapterPosition());
                    }
                }
            });

            btnBorrar= (ImageButton) itemView.findViewById(R.id.btn_delete);
            btnBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tareaListener != null){
                        tareaListener.deleteTarea(getAdapterPosition());
                    }
                }
            });
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

        //Tarea tarea = tareas.get(position);


        holder.txtTarea.setText(tareas.get(position).getTarea());
        holder.txtEstudiante.setText(tareas.get(position).getEstudiante());
        holder.txtAsignatura.setText(tareas.get(position).getAsignatura());
        holder.txtNota.setText(tareas.get(position).getNota());
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }


    public TareaListener getTareaListener() {
        return tareaListener;
    }

    public void setTareaListener(TareaListener tareaListener) {
        this.tareaListener = tareaListener;
    }
}
