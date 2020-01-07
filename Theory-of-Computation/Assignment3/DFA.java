package Assignment3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class DFA implements Serializable {
	private String[] states;
	private String[] alphabet;
	private HashMap<String,String[]> transitions;
	private String startState;
	private String[] acceptStates;
	
	   public DFA(String[] s, String[] a, HashMap<String,String[]> t, String ss, String[] as) {
		this.states = s;
		this.alphabet = a;
		this.transitions = t;
		this.startState = ss;
		this.acceptStates = as;
	}
	
	boolean accepts(String string) {
    	if(string=="") {
		for(String as: acceptStates)
			if(startState.equals(as))
				return true;
    	}

     	else { 
		String currentState = startState;
		String currentInput = "";
		
		for(int i=0;i<string.length();i++) {
			currentInput = string.charAt(i) + "";
			String[] t = transitions.get(currentState);
			
			for(int j=0; j<alphabet.length; j++) 
				if(alphabet[j].equals(currentInput)) {
					currentState = t[j];
					break;
				}
		}
		
		for(String accept : acceptStates) 
			if(accept.equals(currentState))
				return true;
	
	}
    	
    	return false;
	}
}
