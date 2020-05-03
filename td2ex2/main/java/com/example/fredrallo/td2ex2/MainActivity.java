package com.example.fredrallo.td2ex2;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


/**
 * MainActivity dispose d'un clé pour l'enregistrement
 * et pour la récupération de la variable dureeAnimation
 *
 * La durée (en millisecondes) de l'animation "réapparition" est modifiée quand on déplace la SeekBar
 * La valeur est sauvegardée dans les SharedPreferences lorsque l'activité se pause --> onPause().
 */
public class MainActivity extends Activity {
    private static final String ANIM_LENGTH_PREF = "dureeAnimation";
    private int dureeAnimation;     //durée de l'animation


    /**
     * crée un écouteur sur un bouton et lui associe une animation à jouer
     * @param b le bouton à écouter
     * @param animationAJouer l'animation à jouer
     */
    private void creerEcouteurDuBouton(Button b, final int animationAJouer){
        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(
                        MainActivity.this, animationAJouer));
                v.setVisibility(View.INVISIBLE);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		//Récupération de la valeur sauvegardée de dureeAnimation (ou 400 par défaut)
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        dureeAnimation = preferences.getInt(ANIM_LENGTH_PREF, 400);

        //récupération des widgets
        Button boutonFondue = findViewById(R.id.vFondu);
        Button boutonRetrecissement = findViewById(R.id.vRetrecissement);
        Button boutonSortie = findViewById(R.id.vSortie);
        Button boutonFondueRetrecissement = findViewById(R.id.vFonduAgrandissement);
        Button boutonTVOff = findViewById(R.id.vTvOff);
        Button boutonReapparition = findViewById(R.id.vReapparition);
        final TextView tvAnimDuration = findViewById(R.id.tvAnimDuration); //final car dans innerclass
        SeekBar seekBar = findViewById(R.id.seekBar);

        // création d'une liste de boutons (pour visibilité des boutons)
        final ArrayList<Button> buttons = new ArrayList();      //final car dans innerclass

        //peuplement de la liste de boutons
        buttons.add(boutonFondue);
        buttons.add(boutonRetrecissement);
        buttons.add(boutonSortie);
        buttons.add(boutonFondueRetrecissement);
        buttons.add(boutonTVOff);

        // créer les écouteurs sur les boutons de lia liste (lancer l'animation et rendre invisible)
        creerEcouteurDuBouton(boutonFondue, R.anim.fondue);
        creerEcouteurDuBouton(boutonRetrecissement, R.anim.retrecissement);
        creerEcouteurDuBouton(boutonSortie, R.anim.sortie);
        creerEcouteurDuBouton(boutonFondueRetrecissement, R.anim.fondu_agrandissement);
        creerEcouteurDuBouton(boutonTVOff, R.anim.tv_off);


        // écouteur sur le bouton réapparition
        // Pour chaque bouton de la liste, s'il n'est pas visible, lancer l'animation de réapparition et le rendre visible
        boutonReapparition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation reapparition = AnimationUtils.loadAnimation( getApplicationContext() , R.anim.reapparition);
                reapparition.setDuration(dureeAnimation);
                for (View button : buttons) {
                    if (button.getVisibility() != View.VISIBLE) {
                        button.startAnimation(reapparition);
                        button.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


        //écouteur sur la seelbar : modifier dureeAnimation et val affichée
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar1) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar1) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar1, int progress, boolean fromUser) {
                dureeAnimation = progress;
                tvAnimDuration.setText(dureeAnimation+"");
            }
        });


		//modifier la valeur de la seekbar --> appel de la méthode de l'écouteur
        seekBar.setProgress(dureeAnimation);
    }





    /**
     * sauvegarde la valeur dureeAnimation
     */
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        preferences.edit().putInt(ANIM_LENGTH_PREF, dureeAnimation).apply();
    }

}
