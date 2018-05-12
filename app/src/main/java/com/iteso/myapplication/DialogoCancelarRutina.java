package com.iteso.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class DialogoCancelarRutina extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.mensaje_aliento).setPositiveButton(R.string.rutina_continuar_boton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast toast = Toast.makeText(getActivity(), "Excelente, tú continúa! Presiona Start de nuevo.",  Toast.LENGTH_LONG);
                toast.show();
            }
        }).setNegativeButton(R.string.rutina_cancelar_boton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                Toast toast = Toast.makeText(getActivity(), "Lástima Margarito!",  Toast.LENGTH_LONG);
                toast.show();
            }
        });
        builder.setTitle(R.string.rutina_cancelar_titulo_dialogo);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
