package com.manuel.canul.productos;

import android.os.Parcel;
import android.os.Parcelable;

public class Productos implements Parcelable{

    //Data Variables
    private int id_producto;
    private String nombre_producto;
    private String descripcion_producto;
    private String precio_producto;

    public Productos(){

    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public String getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(String precio_producto) {
        this.precio_producto = precio_producto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_producto);
        dest.writeString(nombre_producto);
        dest.writeString(descripcion_producto);
        dest.writeString(precio_producto);
    }
    protected Productos(Parcel in) {
        id_producto = in.readInt();
        nombre_producto = in.readString();
        descripcion_producto = in.readString();
        precio_producto = in.readString();
    }

    public static final Creator<Productos> CREATOR = new Creator<Productos>() {
        @Override
        public Productos createFromParcel(Parcel in) {
            return new Productos(in);
        }

        @Override
        public Productos[] newArray(int size) {
            return new Productos[size];
        }
    };
}
