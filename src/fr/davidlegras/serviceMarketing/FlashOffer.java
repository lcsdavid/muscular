package fr.davidlegras.serviceMarketing;

import java.util.ArrayList;

public class FlashOffer {

    public FlashOffer(float reduction, ArrayList<String> target){
        used = false;
        this.reduction  = reduction;
        this.target = target;
        this.type = 0;
    }

    public FlashOffer(float reduction, ArrayList<String> target, int type){
        used = false;
        this.reduction  = reduction;
        this.target = target;
        this.type = type;
    }

    private float reduction; //quel est le montant de la réduction en pourcentage
    private Boolean used; // sert à savoir si une offre de ce type à déjà été utilisée sur ce produit
    private ArrayList<String> target; //soit les noms des produits soit leur categorie
    private int type; //le type est 0 si l'offre concerne des produit, 1 si elle concerne des categories

    public Boolean getUsed(){return used;}

    
}
