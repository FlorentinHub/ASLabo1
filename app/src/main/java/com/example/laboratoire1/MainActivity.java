package com.example.laboratoire1;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.laboratoire1.R;

public class MainActivity extends AppCompatActivity {

    // Déclaration des éléments de l'interface utilisateur
    private EditText etMotDePasse;
    private CheckBox cbAfficherMotDePasse;
    private TextView tvMessage;
    private Button btnValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des éléments avec id
        etMotDePasse = findViewById(R.id.et_mot_de_passe);
        cbAfficherMotDePasse = findViewById(R.id.cb_afficher_mot_de_passe);
        tvMessage = findViewById(R.id.tv_message);
        btnValider = findViewById(R.id.btn_valider);
        tvMessage.setTextColor(getResources().getColor(R.color.color_gray));

        // Gestionnaire d'événements pour la case à cocher
        cbAfficherMotDePasse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Affiche le mot de passe sous forme de texte lisible
                etMotDePasse.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                // Masque le mot de passe en utilisant le type de texte de mot de passe
                etMotDePasse.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        // Gestionnaire d'événements pour le bouton de validation
        btnValider.setOnClickListener(v -> {
            String password = etMotDePasse.getText().toString();
            if (isPasswordValid(password)) {
                // Si le mot de passe est valide, on le met bleu
                Resources r = getResources();
                int bleu=r.getColor(R.color.blue_color);
                tvMessage.setTextColor(bleu);
            } else {
                // Si le mot de passe n'est pas valide, on le met en rouge
                tvMessage.setTextColor(getResources().getColor(R.color.red_color));
            }
        });
    }

    // Méthode pour valider le mot de passe
    private boolean isPasswordValid(String password) {
        // Conditions de validation du mot de passe
        // 1. Doit avoir une longueur minimale de 10 caractères.
        // 2. Doit avoir au moins une lettre MAJUSCULE, une lettre minuscule, un chiffre et un caractère spécial autorisé.

        // Vérification de la longueur minimale (10 caractères)
        if (password.length() < 10) {
            return false;
        }
        // Check Majuscules
        Pattern uppercasePattern = Pattern.compile("[A-Z]");
        Matcher uppercaseMatcher = uppercasePattern.matcher(password);
        //Check Minuscules
        Pattern lowercasePattern = Pattern.compile("[a-z]");
        Matcher lowercaseMatcher = lowercasePattern.matcher(password);
        //Check Chiffres
        Pattern digitPattern = Pattern.compile("[0-9]");
        Matcher digitMatcher = digitPattern.matcher(password);
        //Check Caractere Speciaux
        Pattern specialCharPattern = Pattern.compile("[@#$%&()\\[\\]{}_=<>+\\-!?*/|:;,.'\"~^]");
        Matcher specialCharMatcher = specialCharPattern.matcher(password);
        // Valide finalement le mot de passe.
        return uppercaseMatcher.find() && lowercaseMatcher.find() && digitMatcher.find() && specialCharMatcher.find();
    }
}
