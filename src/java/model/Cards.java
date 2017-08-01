/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Su-Ting Chen
 */
public class Cards {
    
    private ArrayList<String> cardsList;
    
    public Cards(){
        
        cardsList=new ArrayList<>();
        
        cardsList.add("1");
        cardsList.add("2");
        cardsList.add("3");
        cardsList.add("4");
        cardsList.add("5");
        cardsList.add("6");
        cardsList.add("7");
        cardsList.add("8");
        cardsList.add("9");
        cardsList.add("10");
        cardsList.add("11");
        cardsList.add("12");
        cardsList.add("13");
        cardsList.add("14");
        cardsList.add("15"); 
    }

    public ArrayList<String> getCardsList() {
        
        Collections.shuffle(cardsList);
        
        return cardsList;
    }
    
}
