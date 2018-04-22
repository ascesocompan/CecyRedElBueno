package com.cecyred.ww.cecyred;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class REGISTROUSUARIO extends AppCompatActivity {
EditText Et_nom,Et_pass,Et_bol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrousuario);
       Et_nom=(EditText)findViewById(R.id.TXT_NOMBRE);
       Et_pass=(EditText)findViewById(R.id.TXT_CONTRA);
       Et_bol=(EditText)findViewById(R.id.TXT_BOLETA);
    }
    public void registro(View v) {
        String usuario = Et_nom.getText().toString();
        String contrasena = Et_pass.getText().toString();
        String boleta = Et_bol.getText().toString();
        if (usuario.isEmpty() && contrasena.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingresa el usuario y la contraseña", Toast.LENGTH_SHORT).show();
        }else {
            Response.Listener<String> respolistener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    // jsonResponse= null;
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean succes = jsonResponse.getBoolean("success");
                        if (succes) {
                            Toast.makeText(getApplicationContext(), "Registrado con exito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(REGISTROUSUARIO.this, LogginActivity.class);
                            REGISTROUSUARIO.this.startActivity(intent);
                        } else {

                            Toast.makeText(getApplicationContext(), "Ocurrio un error en el registro", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        //e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "" + e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            };
            requestregistro requestreg = new requestregistro(boleta, usuario, contrasena, respolistener);
            RequestQueue queue = Volley.newRequestQueue(REGISTROUSUARIO.this);
            queue.add(requestreg);
        }
    }

}
