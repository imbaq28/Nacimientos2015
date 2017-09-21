package com.example.corei7.nacimientos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.corei7.nacimientos.R;
import com.example.corei7.nacimientos.model.Nacimientos;

import java.util.ArrayList;
import java.util.List;

public class NacimientosAdapter  extends RecyclerView.Adapter<NacimientosAdapter.DeviceViewHolder>{

    private Context context;
    private List<Nacimientos> dataset;
    private OnNacimientoSelectedListener OnNacimientoSelectedListener;

    public interface OnNacimientoSelectedListener {
        void onNacimientoSelected(Nacimientos departamento);
    }

    public NacimientosAdapter(Context context, OnNacimientoSelectedListener listener) {
        this.dataset = new ArrayList<>();
        this.context = context;
        this.OnNacimientoSelectedListener = listener;

    }


    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unidad_educativa, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        Nacimientos u = dataset.get(position);
        holder.nombreTextView.setText(u.getDepartamento());
        holder.direccionTextView.setText(u.getMunicipio());
        holder.distritoTextView.setText(u.getNacidos());

        holder.setDeviceSelectedListener(u, OnNacimientoSelectedListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

        View cardView;

        TextView nombreTextView;
        TextView direccionTextView;
        TextView distritoTextView;

        public DeviceViewHolder(View itemView) {
            super(itemView);

          cardView = itemView.findViewById(R.id.cardView);

            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            direccionTextView = (TextView) itemView.findViewById(R.id.direccionTextView);
            distritoTextView = (TextView) itemView.findViewById(R.id.distritoTextView);
        }

        public void setDeviceSelectedListener(final Nacimientos departamento, final OnNacimientoSelectedListener OnNacimientoSelectedListener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnNacimientoSelectedListener.onNacimientoSelected(departamento);
                }
            });
        }
    }

    public void add(Nacimientos Nacimiento) {
        dataset.add(Nacimiento);
        notifyDataSetChanged();
    }

    public void setDataset(List<Nacimientos> Nacimiento) {
        if (Nacimiento == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = Nacimiento;
        }
        notifyDataSetChanged();
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }
}
