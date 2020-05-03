package com.example.fredrallo.td1ex4;

/**
 * Created by F.Rallo on 28/01/2018.
 */
public class Personne {
    private String nom;
    private int sexe;
    private  boolean langC;
    private  boolean langCPP;
    private boolean langJava;

//----------------- constructeurs ---------------------

    /**
     * constructeur par défaut
     * une personne par défaut est une femme nommée "Grande Prêtresse"
     */
    public Personne(){
        nom="Grande Prêtresse";
        sexe=2;
        langC=true;
        langCPP=true;
        langJava=true;
    }

    /**
     * constructeur normal
     * @param n le nom
     * @param s le sexe
     * @param c connait le C
     * @param cpp connait le C++
     * @param java connait le java
     */
    public Personne(String n,int s,boolean c,boolean cpp,boolean java){
        nom=n;
        langC=c;
        langCPP=cpp;
        langJava=java;
        sexe=s;
    }


    //----------- accesseurs -----------------------------
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public boolean isLangC() {
        return langC;
    }

    public void setLangC(boolean langC) {
        this.langC = langC;
    }

    public boolean isLangCPP() {
        return langCPP;
    }

    public void setLangCPP(boolean langCPP) {
        this.langCPP = langCPP;
    }

    public boolean isLangJava() {
        return langJava;
    }

    public void setLangJava(boolean langJava) {
        this.langJava = langJava;
    }





    @Override
   public  String toString(){
        String leSexe;
        if (sexe==1) leSexe="un homme"; else leSexe="une femme";
        String connaissance="";
        if(langC) connaissance+="le langage C ";
        if(langCPP) connaissance+="le langage C++ ";
        if(langJava) connaissance+="le langage Java ";

        return nom.toString() + " vous êtes " + leSexe.toString()+ " vous connaissez " + connaissance.toString();
    }

}
