package com.iteso.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class DetallesEjercicio implements Parcelable {
    private String idEjercicio;
    private int reps;
    private int sets;
    private int descansoSeg;

    public DetallesEjercicio(String idEjercicio, int reps, int sets, int descansoSeg) {
        this.idEjercicio = idEjercicio;
        this.reps = reps;
        this.sets = sets;
        this.descansoSeg = descansoSeg;
    }

    public String getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(String idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getDescansoSeg() {
        return descansoSeg;
    }

    public void setDescansoSeg(int descansoSeg) {
        this.descansoSeg = descansoSeg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idEjercicio);
        dest.writeInt(this.reps);
        dest.writeInt(this.sets);
        dest.writeInt(this.descansoSeg);
    }

    protected DetallesEjercicio(Parcel in) {
        this.idEjercicio = in.readString();
        this.reps = in.readInt();
        this.sets = in.readInt();
        this.descansoSeg = in.readInt();
    }

    public static final Creator<DetallesEjercicio> CREATOR = new Creator<DetallesEjercicio>() {
        @Override
        public DetallesEjercicio createFromParcel(Parcel source) {
            return new DetallesEjercicio(source);
        }

        @Override
        public DetallesEjercicio[] newArray(int size) {
            return new DetallesEjercicio[size];
        }
    };
}
