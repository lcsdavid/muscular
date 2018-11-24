package fr.davidlegras.serviceMarketing;

import java.util.ArrayList;

public class FlashOffer {

    public FlashOffer(float reduction, ArrayList<String> target){
        used = false;
        this.reduction  = reduction;
        this.target = target;
    }

    private float reduction; //quel est le montant de la réduction en pourcentage
    private Boolean used; // sert à savoir si une offre de ce type à déjà été utilisée sur ce produit
    private ArrayList<String> target; //soit les noms des produits

    public Boolean getUsed(){return used;}


}
