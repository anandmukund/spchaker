package com.airtel.spchacker.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airtel.spchacker.dao.Dictionary;
import com.airtel.spchacker.dto.Response;


@Service
public class SpellCheck {
	
	@Autowired    
    private Dictionary dict;
	
    final static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

   public  Response run(List<String> inputList) {
	   
	   Response result = new  Response();
	   List<String> match = result.getMatchedWord();
          for(String input : inputList) {
            if (dict.contains(input)) {
                System.out.println("\n" + input + " is spelled correctly");
                 match.add(input);
            } else {
                System.out.print("is not spelled correctly, ");
                findSuggestions(input , result);
            }
        }
        return result;
    }

    private void  findSuggestions(String input , Response resp) {
        ArrayList<String> print = makeSuggestions(input);
        if (print.size() == 0) {
        	resp.getSimilarWord().put(input, "I have no idea what word you could mean.\n");
            System.out.println( "I have no idea what word you could mean.\n");
        }
        for (String s : print) {
        	if(resp.getSimilarWord().containsKey(input)) {
        		String result = resp.getSimilarWord().get(input);
        		result = result + ", " + s;
        		resp.getSimilarWord().put(input, result);
        	} else {
        		resp.getSimilarWord().put(input, "The Suggested words is/are  ---" + s);
        	}
        }
    }

    private ArrayList<String> makeSuggestions(String input) {
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.addAll(charAppended(input));
        toReturn.addAll(charMissing(input));
        toReturn.addAll(charsSwapped(input));
        return toReturn;
    }

    private ArrayList<String> charAppended(String input) { 
        ArrayList<String> toReturn = new ArrayList<>();
        for (char c : alphabet) {
            String atFront = c + input;
            String atBack = input + c;
            if (dict.contains(atFront)) {
                toReturn.add(atFront);
            }
            if (dict.contains(atBack)) {
                toReturn.add(atBack);
            }
        }
        return toReturn;
    }

    private ArrayList<String> charMissing(String input) {   
        ArrayList<String> toReturn = new ArrayList<>();

        int len = input.length() - 1;
        if (dict.contains(input.substring(1))) {
            toReturn.add(input.substring(1));
        }
        for (int i = 1; i < len; i++) {
            String working = input.substring(0, i);
            working = working.concat(input.substring((i + 1), input.length()));
            if (dict.contains(working)) {
                toReturn.add(working);
            }
        }
        if (dict.contains(input.substring(0, len))) {
            toReturn.add(input.substring(0, len));
        }
        return toReturn;
    }

    private ArrayList<String> charsSwapped(String input) {   
        ArrayList<String> toReturn = new ArrayList<>();

        for (int i = 0; i < input.length() - 1; i++) {
            String working = input.substring(0, i);
            working = working + input.charAt(i + 1); 
            working = working + input.charAt(i);
            working = working.concat(input.substring((i + 2)));
            if (dict.contains(working)) {
                toReturn.add(working);
            }
        }
        return toReturn;
    }

    public String run(String word){
        int T[][] = new int[word.length()][word.length()];
        for(int i=0; i < T.length; i++){
            for(int j=0; j < T[i].length ; j++){
                T[i][j] = -1;
            }
        }
        for(int l = 1; l <= word.length(); l++){
            for(int i=0; i < word.length() -l + 1 ; i++){
                int j = i + l-1;
                String str = word.substring(i,j+1);
                if(dict.contains(str)){
                    T[i][j] = i;
                    continue;
                }
                for(int k=i+1; k <= j; k++){
                    if(T[i][k-1] != -1 && T[k][j] != -1){
                        T[i][j] = k;
                        break;
                    }
                }
            }
        }
        if(T[0][word.length()-1] == -1){
            return "sentense is wrong";
        }
        
        StringBuffer buffer = new StringBuffer();
        int i = 0; int j = word.length() -1;
        while(i < j){
            int k = T[i][j];
            if(i == k){
                buffer.append(word.substring(i, j+1));
                break;
            }
            buffer.append(word.substring(i,k) + " ");
            i = k;
        }
        String result = "sentense is wrong";
        if(buffer.length() >  0) {
        	result = buffer.toString();
        }
        		
        return result ;
    }

}