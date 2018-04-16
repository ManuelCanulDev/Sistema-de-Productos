package com.manuel.canul.productos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder>{

    private Context context;

    //List to store all plants
    List<Productos> productos_list;

    public ProductosAdapter(List<Productos> lista_productos, Context context){
        super();
        //Getting all plant
        this.productos_list = lista_productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosAdapter.ViewHolder holder, int position) {

        //Getting the particular item from the list
        Productos productos = productos_list.get(position);
        //Loading image from url

        //Showing data on the views
        holder.textViewIDProducto.setText(Integer.toString(productos.getId_producto()));
        holder.textViewNombreProducto.setText(productos.getNombre_producto());
        holder.textViewDescripcionProducto.setText(productos.getDescripcion_producto());
        holder.textViewPrecioProducto.setText(productos.getPrecio_producto());
    }

    @Override
    public int getItemCount() {
        return productos_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //Views
        public TextView textViewIDProducto;
        public TextView textViewNombreProducto;
        public TextView textViewDescripcionProducto;
        public TextView textViewPrecioProducto;

        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            textViewIDProducto = (TextView) itemView.findViewById(R.id.id_producto);
            textViewNombreProducto = (TextView) itemView.findViewById(R.id.nombre_producto);
            textViewDescripcionProducto = (TextView) itemView.findViewById(R.id.descripcion_producto);
            textViewPrecioProducto = (TextView) itemView.findViewById(R.id.precio_producto);

        }
    }
}
