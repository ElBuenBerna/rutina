package com.iteso.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Erik Bustos M on 04/04/2018.
 */

public class Ejercicio implements Parcelable {
    private String id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String photoURL;

    public Ejercicio() {
    }

    public Ejercicio(String id, String nombre, String descripcion, String categoria, String photoURL) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.photoURL = photoURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.descripcion);
        dest.writeString(this.categoria);
        dest.writeString(this.photoURL);
    }

    protected Ejercicio(Parcel in) {
        this.id = in.readString();
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.categoria = in.readString();
        this.photoURL = in.readString();
    }

    public static final Creator<Ejercicio> CREATOR = new Creator<Ejercicio>() {
        @Override
        public Ejercicio createFromParcel(Parcel source) {
            return new Ejercicio(source);
        }

        @Override
        public Ejercicio[] newArray(int size) {
            return new Ejercicio[size];
        }
    };
}
