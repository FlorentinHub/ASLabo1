package com.example.laboratoire1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etMotDePasse;
    private CheckBox cbAfficherMotDePasse;
    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etMotDePasse = findViewById(R.id.et_mot_de_passe);
        tv_message = findViewById(R.id.tv_message);
        //tv_message.setVisibility(View.TEXT); ici
        cbAfficherMotDePasse = findViewById(R.id.cb_afficher_mot_de_passe);

        cbAfficherMotDePasse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                etMotDePasse.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                etMotDePasse.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
    }

}
