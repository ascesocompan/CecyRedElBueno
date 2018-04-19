package com.cecyred.ww.cecyred;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.ViewAnimationUtils.createCircularReveal;

public class LogginActivity extends AppCompatActivity {
    private TextView textviewusuario,textviewpassword;
    private ImageView cr,imagenregistro;
    private  static final long DURACION_REVELAR=1500;
    private  static  final long DURACION_QUITAR=800;
    public LogginActivity() {
    }
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void OnMostrar(final View view) {
        textviewusuario=findViewById(R.id.Usuario);
        textviewpassword=findViewById(R.id.Contraseña);

        Animator animator= createCircularReveal(textviewusuario,0,10,0,textviewusuario.getWidth()*1.5f);
        animator.setDuration(DURACION_REVELAR);

        Animator animator1= createCircularReveal(textviewpassword,0,10,0,textviewpassword.getWidth()*1.5f);
        animator1.setDuration(DURACION_REVELAR);

        textviewpassword.setVisibility(View.VISIBLE);
        animator1.start();
        textviewusuario.setVisibility(View.VISIBLE);
        animator.start();


        cr=findViewById(R.id.CR);
        Animator animator2= ViewAnimationUtils.createCircularReveal(cr,cr.getWidth()/2,cr.getHeight()/2,cr.getWidth(),0);
        animator2.setDuration(DURACION_QUITAR);

        animator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation){
                super.onAnimationEnd(animation);
                cr.setVisibility(View.INVISIBLE);
            } });
        animator2.start();
    }

    public void CambioRegistro(View view) {
        Intent intent= new Intent (LogginActivity.this, REGISTROUSUARIO.class);
        startActivity(intent);
    }
    public void CambioSolover(View view) {
        Intent intent= new Intent (LogginActivity.this, Perfil.class);
        startActivity(intent);
    }
}
