package Assignment3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

class DFATEST {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		float c = 13.0f;
		short d = '\t';
		
		int b = (int)Math.ceil(2.0f/3);
	
		String[] states;
		String[] alphabet;
		HashMap<String,String[]> transitions = new HashMap<>();
		String startState;
		String[] acceptStates;
		String stringProcess;
		String exerciseNumber;
		String answer;
		String fileName;
		byte x = 127;
		String firstP = "Please enter the ";
		String secondP = " separated with a space: ";

		String[] messages = 	
			{ 
					firstP + "states" + secondP,
					firstP + "alphabet" + secondP,
					firstP + "transitions" + secondP,
					firstP + "first state: ",
					firstP + "accept states" + secondP,
					firstP + "exercise number: "
			};
		
		System.out.print("Do you want to add a dfa or test an existing one? <Add> or <exercise number>");
		answer = br.readLine();
		if(answer.toLowerCase().equals("add")) {
		
		System.out.print(messages[0]);
		states = br.readLine().split(" ");
		
		System.out.print(messages[1]);
		alphabet = br.readLine().split(" ");
		
		System.out.println(messages[2]);
		
		String[] row;
		int j;
		for(int i=0; i<states.length;i++) {
			j = i+1;
			System.out.print("Row " + j + ": ");
			row = br.readLine().split(" ");
			transitions.put(states[i], row);
		}
		
		System.out.print(messages[3]);
		startState = br.readLine();
		
		System.out.print(messages[4]);
		acceptStates = br.readLine().split(" ");
		
		System.out.print(messages[5]);
		exerciseNumber = br.readLine();
		
		DFA machine = new DFA(states,alphabet,transitions,startState,acceptStates);
		try
        {
		   fileName = exerciseNumber +".ser";
           FileOutputStream fileOut = new FileOutputStream(fileName);
           ObjectOutputStream out   = new ObjectOutputStream(fileOut);
           out.writeObject(machine);
           out.close();
           fileOut.close();
           
        }catch(IOException i)
        {
            i.printStackTrace();
        }
		
		} 
		else {		
		while(true) {
			System.out.println(firstP + "string: ");
			stringProcess = br.readLine();
			try
	         {
				fileName = answer +".ser";
	            FileInputStream fileIn = new FileInputStream(fileName);
	            ObjectInputStream in = new ObjectInputStream(fileIn);
	            DFA dfa = (DFA) in.readObject();
	            in.close();
	            fileIn.close();
	            System.out.println(dfa.accepts(stringProcess));
	            
	        }catch(IOException i)
	        {
	            i.printStackTrace();
	            return;
	        }catch(ClassNotFoundException c)
	        {
	            c.printStackTrace();
	            return;
	        }
			
		}

		
		
		
		}
		
	}

}

