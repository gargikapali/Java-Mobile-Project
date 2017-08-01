/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import test.Globals;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Su-Ting Chen
 */
public class CardSet { 
   
    
    private final String[] randomCards;
    
    public CardSet(){
        randomCards = getShuffledCards();
    }
    
    public String[] getCardSet() {
        return randomCards;
    }
    
    private String[] getShuffledCards(){
        
        Cards allCards = new Cards();
        
        ArrayList<String> list = allCards.getCardsList();
        
        ArrayList<String> list2 = new ArrayList<>();
        
        for(int i = 0; i < Globals.numOfPairs ; i++){
            list2.add(list.get(i));
        }
        
        for(int i = 0 ; i < Globals.numOfPairs ; i++){
            list2.add(list2.get(i)); 
        }
        
        Collections.shuffle(list2);
        Collections.shuffle(list2);
        
        String[] items = new String[Globals.numOfPairs*2];
        
        for(int i = 0; i < list2.size();i++){
            items[i] = list2.get(i);
        }
        
        return items;
        
    }
    
    
}
