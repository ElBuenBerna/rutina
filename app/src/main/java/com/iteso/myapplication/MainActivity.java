package com.iteso.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class MainActivity extends AppCompatActivity {

    private TextView ejrcActual;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button empezarRutina;

    private TextView repAnterior;
    private TextView ejrcAnterior;
    private TextView repSiguiente;
    private TextView ejrcSiguiente;
    private TextView repActual;
    private TextView setsRestantes;

    private MaterialProgressBar progressCountDown;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;
    private boolean finished;
    private boolean rutinaEmpezada;
    private boolean setTerminado;
    private boolean descansando;


    private long mTimeLeftInMillis;
    private int i = 0;
    private int reps;
    private int sets;
    private int descanso;

    private String nombre;

    private ArrayList<Ejercicio> arrEjercicio = new ArrayList<Ejercicio>();
    private ArrayList<DetallesEjercicio> arrDetEjercicio = new ArrayList<DetallesEjercicio>();

    private Ejercicio ex1 = new Ejercicio("0", "Sentadillas", "ALSDJF", "ASLDf", "LASDFJ");
    private Ejercicio ex2 = new Ejercicio("1", "Lagartijas", "ALSDJF", "ASLDf", "LASDFJ");
    private Ejercicio ex3 = new Ejercicio("2", "Abdominales", "ALSDJF", "ASLDf", "LASDFJ");

    private DetallesEjercicio dex1 = new DetallesEjercicio("0", 10, 2, 5);
    private DetallesEjercicio dex2 = new DetallesEjercicio("1", 12, 3, 6);
    private DetallesEjercicio dex3 = new DetallesEjercicio("2", 15, 4, 7);




    private String[] ejercicios = new String[]{"Saltos", "Descansa 5 segundos", "Press de Banca", "Descansa 5 segundos", "Bicep"};
    private long [] tiempos = new long[]{6000, 6000, 11000, 6000, 11000};
    private String[] repeticiones = new String[]{"10", "", "20", "", "30"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        ejrcActual = findViewById(R.id.text_view_prueba);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        empezarRutina = findViewById(R.id.button_rutina);
        repAnterior = findViewById(R.id.rutina_repeticiones_anterior);
        ejrcAnterior = findViewById(R.id.rutina_ejercicio_anterior);
        repSiguiente = findViewById(R.id.rutina_repeticiones_siguiente);
        ejrcSiguiente = findViewById(R.id.rutina_ejercicio_siguiente);
        repActual = findViewById(R.id.rutina_repeticiones_actual);
        progressCountDown = findViewById(R.id.progress_bar);
        setsRestantes = findViewById(R.id.rutina_sets_faltantes);



        arrEjercicio.add(ex1);
        arrEjercicio.add(ex2);
        arrEjercicio.add(ex3);

        arrDetEjercicio.add(dex1);
        arrDetEjercicio.add(dex2);
        arrDetEjercicio.add(dex3);


        empezarRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rutinaEmpezada){
                    pauseTimer();
                    showDialog();
                } else {
                    i = 0;
                    mTimerRunning = true;
                    rutinaEmpezada = true;

                    nombre = arrEjercicio.get(i).getNombre();
                    reps = arrDetEjercicio.get(i).getReps();
                    sets = arrDetEjercicio.get(i).getSets();
                    descanso = arrDetEjercicio.get(i).getDescansoSeg();


                    progressCountDown.setMax(31000);//Treinta segundos cada set
                    progressCountDown.setProgress(0);

                    empezarRutina.setText(R.string.terminar_rutina);

                    repAnterior.setText("");
                    ejrcAnterior.setText("");

                    ejrcSiguiente.setText(arrEjercicio.get(i+1).getNombre());
                    repSiguiente.setText(Integer.toString(arrDetEjercicio.get(i+1).getReps()));

                    ejrcActual.setText(nombre);
                    repActual.setText(Integer.toString(reps));
                    setsRestantes.setText(Integer.toString(sets-1));
                    mTimeLeftInMillis = 31000;
                    setTerminado = true;
                    descansando = false;
                    startTimer();
                }



            }
        });

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });


        updateCountDownText();
    }

    private void startTimer() {

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                progressCountDown.setProgress(0);
                if(sets==0){
                    i++;
                    ejrcAnterior.setText(nombre);
                    repAnterior.setText(Integer.toString(reps));
                    try{
                        progressCountDown.setMax(31000);
                        nombre = arrEjercicio.get(i).getNombre();
                        reps = arrDetEjercicio.get(i).getReps();
                        sets = arrDetEjercicio.get(i).getSets();
                        descanso = arrDetEjercicio.get(i).getDescansoSeg();

                        ejrcActual.setText(nombre);
                        repActual.setText(Integer.toString(reps));
                        setsRestantes.setText(Integer.toString(sets-1));
                        mTimeLeftInMillis = 31000;
                        try{
                            ejrcSiguiente.setText(arrEjercicio.get(i+1).getNombre());
                            repSiguiente.setText(Integer.toString(arrDetEjercicio.get(i+1).getReps()));
                        } catch (Exception e){
                            ejrcSiguiente.setText("");
                            repSiguiente.setText("");
                        }
                        startTimer();
                    } catch (Exception e){
                        mTimerRunning = false;
                        mButtonStartPause.setVisibility(View.INVISIBLE);
                        mCountDownTimer.cancel();
                    }
                } else if(setTerminado){
                    Log.e("Ejercicios", "Si entró");
                    ejrcActual.setText(R.string.descanso_rutina);
                    repActual.setText("");
                    progressCountDown.setMax(descanso);
                    mTimeLeftInMillis = descanso;
                    descansando = true;
                    startTimer();

                } else {
                    Log.e("Ejercicios", "ELSE");
                    setsRestantes.setText(Integer.toString(sets-1));
                    progressCountDown.setMax(31000);
                    mTimeLeftInMillis = 31000;
                    ejrcActual.setText(nombre);
                    repActual.setText(Integer.toString(reps));
                    setTerminado = true;
                    sets--;
                    descansando = false;
                    startTimer();
                }


            }
        }.start();


        mTimerRunning = true;
        mButtonStartPause.setText("pause");
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        if(descansando){
            progressCountDown.setProgress((int)(descanso - mTimeLeftInMillis));
        } else {
            progressCountDown.setProgress((int)(31000 - mTimeLeftInMillis));
        }



        mTextViewCountDown.setText(timeLeftFormatted);
    }

    public void showDialog(){
        DialogFragment dialogo = new DialogoCancelarRutina();
        dialogo.show(getSupportFragmentManager(), "cancelar_rutina");
    }

}


