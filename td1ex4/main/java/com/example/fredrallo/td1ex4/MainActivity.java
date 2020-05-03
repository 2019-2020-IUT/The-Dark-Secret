package com.example.fredrallo.td1ex4;
    import android.app.Activity;
    import android.os.Bundle;
    import android.view.*;
    import android.widget.*;

    /**
     * activité forcée en mode paysage dans le manifest
     */
    public class MainActivity extends Activity {
        Personne laPers= new Personne();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        /**
         * méthode appelée lorsqu'on appuie sur le bouton
         * @param v : obligatoire....
         */
        public void afficherResultat(View v){
            TextView txtReponse= findViewById(R.id.textView);
            recupererNom();
            recupererSexe();
            recupererLangage();
            txtReponse.setText(laPers+"");
        }


    public void recupererNom(){
        EditText nom = findViewById(R.id.editText);
        laPers.setNom(nom.getText().toString());
    }


    public void recupererSexe(){
        RadioButton genre= findViewById(R.id.radioButton1);
        if(genre.isChecked()) laPers.setSexe(1); else laPers.setSexe(2);
    }


    public void recupererLangage(){
        CheckBox chLangageC= findViewById(R.id.checkBoxC);
        CheckBox chLangageCpp= findViewById(R.id.checkBoxCpp);
        CheckBox chLangageJava= findViewById(R.id.checkBoxJava);
        laPers.setLangC(chLangageC.isChecked());
        laPers.setLangCPP(chLangageCpp.isChecked());
        laPers.setLangJava(chLangageJava.isChecked());
    }




}
