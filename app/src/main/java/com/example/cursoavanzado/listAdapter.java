package com.example.cursoavanzado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class listAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private int layaoutID;
    private ArrayList<Usuario> usuarios;

    public listAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Usuario> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context= context;
        this.layaoutID= resource;
        this. usuarios= new ArrayList<>(objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(layaoutID,null);

            holder= new ViewHolder();
            holder.imgIcono=convertView.findViewById(R.id.img_listitem_genero);
            holder.txtnombre=convertView.findViewById(R.id.txtlistitem_nombre);
            holder.txtcorreo=convertView.findViewById(R.id.txtlistitem_correo);
            holder.txtID=convertView.findViewById(R.id.txtlistitem_correo);
            holder.txtrol=convertView.findViewById(R.id.txtlistitem_rol);

        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        Usuario usuario=usuarios.get(position);
        if (usuario.getGenero().equals("Masculino")){
                        holder.imgIcono.setImageResource(R.mipmap.genderfluid);
        }else  if (usuario.getGenero().equals("Femenino")){
            holder.imgIcono.setImageResource(R.mipmap.woman);
        }else {
            holder.imgIcono.setImageResource(R.mipmap.male);
        }
        holder.txtnombre.setText(usuario.getNombre());
        holder.txtcorreo.setText(usuario.getCorreo());
        holder.txtrol.setText(usuario.getRol());
        holder.txtID.setText(usuario.getId());

        return convertView;
    }
    public class ViewHolder{
        ImageView imgIcono;
        TextView txtnombre, txtcorreo,txtID,txtrol;
    }
}
