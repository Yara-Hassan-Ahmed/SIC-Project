package HTErecord;

import java.util.*;
import java.io.*;

public class Main {
	
	private static final String[][] OPTAB = new String[59][3];//private string of array that holds a part of a memory
	public static void main(String[] args) throws Exception {
		//create array list type string to create tables that holds the input data from inSIC.txt
	ArrayList<String> locctr = new ArrayList<>();
	ArrayList<String> label = new ArrayList<>();
	ArrayList<String> instr = new ArrayList<>();
	ArrayList<String> ref = new ArrayList<>();
	ArrayList<String> objCode = new ArrayList<>();
	
	String line;
    boolean end = false;
    int count = 3;
    String temp;
    int loc;
    String length;
    String opcode = null;
    
//////////////////////Task 1//////////////////////
//read from file (scan the inSIC file)
    try {
    	//FileReader is meant to read streams of characters
        FileReader file = new FileReader("inSIC.txt");
        try (//BufferedReader will buffer the input from the specified file
        BufferedReader br = new BufferedReader(file)) {
            //readLine() reads the line from the file txt
            //while loop to check the end of the line
            while ((line = br.readLine()) != null) {
                line = line.trim();//it checks the whole line, it returns the string and until it goes to the end 
                String[] values = line.split("\t");//when it reaches the end of the line it split it to the next line
                //for loop to check the values length of the line 
                for (int i = 0; i < values.length; i++) {
                	//if the arr[0] is end that means the end of reading the file
                    if (values[0].equals("end")) {
                        end = true;
                        break;
                    }
                }
//if it didn't reach the end of the program then it will go in if conditions
                if (!end) {
                	//if the length of the line is equal 3 then it will add every read line to its place in the arr
                    if (values.length == 3) {
                        label.add(values[0]);
                        instr.add(values[1]);
                        ref.add(values[2]);
                    }
                    //if the length of the line is equal 2 then it will add every read line to its place in the arr and # in the empty label part
                    else if (values.length == 2) {
                        label.add("#####");
                        instr.add(values[0]);
                        ref.add(values[1]);
                    }
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
//print the file in the output
    System.out.println("******************Program 1******************" +"\n");
    System.out.println("Label " + "Instructions" + " Reference");
    for (int i = 0; i < label.size(); i++) {
        System.out.println(label.get(i) + " ---> " + instr.get(i) + " ---> " 
                + ref.get(i) + "\n");
    }
 // filling locations 
    locctr.add(ref.get(0));
    locctr.add(ref.get(0));

//////////////////////Pass 1//////////////////////
    for (int i = 1; i < instr.size()-1; i++) //for loop to return the number of elements in the list
    {
        if (instr.get(i).equals("BYTE")) //Returns the element at the specified position in this list and Compares it to the specified object
        	
        {
            int x = Integer.parseInt(locctr.get(i-1), 16);//convert from integer to hex
            int z = instr.get(i).length();//to get the length of the byte

            loc = x + (z/2);//to calculate the locctr of a BYTE ((count/2) & convert to hex then add)
//to check if the locctr is 4 digits, first we convert the int number to str to count the digits
            temp = Integer.toHexString(loc);
            String number = "";
//to check if the length of the string is less than 4 then go into the for loop
            if(temp.length() < 4)
            {	//if it's less than 4 digits then fill it with "0" 
                for(int y=4; y>temp.length(); y--){
                    number += "0";
                }
            }
            //increment the temp +1 until it completes the 4 digits 
            number += temp;
            //save it in the arr list locctr
            locctr.add(number);
        }
//if it's not BYTE then it will check if it's RESW
        else if (instr.get(i).equals("RESW")) //value*3 -> hex + prev add
        {
            int x = Integer.parseInt(locctr.get(i), 16);//convert from integer to hex
            int z = (Integer.parseInt(ref.get(i))) * 3;//gets the reference number and multiply by 3
            
            loc = x + z;//to calculate the locctr of RESW ((ref*3)& convert to hex then add)
//to check if the locctr is 4 digits, first we convert the int number to str to count the digits     
            temp = Integer.toHexString(loc);
            String number = "";
//to check if the length of the string is less than 4 then go into the for loop
            if(temp.length() < 4){
            	//if it's less than 4 digits then fill it with "0" 
                for(int y=4; y>temp.length(); y--){
                    number += "0";
                }
            }
            //increment the temp +1 until it completes the 4 digits 
            number += temp;
            //save it in the arr list locctr
            locctr.add(number);
        }
//if it's not BYTE nor RESW then it will check if it's RESB
        else if (instr.get(i).equals("RESB")) {
            int x = Integer.parseInt(locctr.get(i), 16);//convert from integer to hex
            int z = (Integer.parseInt(ref.get(i)));//gets the reference number
            loc = x + z;//to calculate the locctr of RESW (ref & convert to hex then add)
//to check if the locctr is 4 digits, first we convert the int number to str to count the digits     
                      temp = Integer.toHexString(loc);
                      String number = "";
//to check if the length of the string is less than 4 then go into the for loop
                      if(temp.length() < 4){
                      	//if it's less than 4 digits then fill it with "0" 
                          for(int y=4; y>temp.length(); y--){
                              number += "0";
                          }
                      }
                      //increment the temp +1 until it completes the 4 digits 
                      number += temp;
                      //save it in the arr list locctr
                      locctr.add(number);
        }
//if it's not BYTE nor RESW nor RESB then it will check if it's word
        else {
            int x = Integer.parseInt(locctr.get(i), 16);//convert from integer to hex
            loc = x + count;//to calculate the locctr of a WORD
          //to check if the locctr is 4 digits, first we convert the int number to str to count the digits     
            temp = Integer.toHexString(loc);
            String number = "";
//to check if the length of the string is less than 4 then go into the for loop
            if(temp.length() < 4){
            	//if it's less than 4 digits then fill it with "0" 
                for(int y=4; y>temp.length(); y--){
                    number += "0";
                }
            }
            //increment the temp +1 until it completes the 4 digits 
            number += temp;
            //save it in the arr list locctr
            locctr.add(number);
        }
    }
//to print out the location counter after doing the calculation
    System.out.println("******************Location Counter******************" +"\n");
    for (int i=0; i<locctr.size(); i++){
        System.out.println(locctr.get(i));
    }
    
//////////////////////Program Length//////////////////////
//to get the length of the program by calculating (End - Start)                                                             
    int s = locctr.size(); //to get the size of the locctr
    int startOfProg = Integer.parseInt(locctr.get(0), 16);//to get the start locctr 
    int endOfProg = Integer.parseInt(locctr.get(s-1), 16);//to get the end locctr 

    loc = endOfProg - startOfProg;//Length of the program = End - Start
    length = Integer.toHexString(loc);//Returns a string representation of the integer argument as hexa
    

//to print out the length of the program
    System.out.println("******************Length Of Program******************" +"\n");
    System.out.println("Length = " +length +"\n");
    
//////////////////////Symbol Table//////////////////////
//to print out the Symbol table
    System.out.println("******************Symbol Table******************"+"\n");
    System.out.println("Locctr " + " Label");
    for (int i=1; i<locctr.size(); i++) {
        if (label.get(i) != "#####  ") {
            System.out.println(locctr.get(i) + "--->" + label.get(i));
        }
    }
//////////////////////Pass 2//////////////////////
    initialize();
    int pos = 0;
//in pass 2 we don't calculate the object code for the "Start, RESW, RESB, End"
    for (int i = 0; i < instr.size(); i++) {
//by going by the list and checking if this word are similar
        if(instr.get(i).equals("Start") || instr.get(i).equals("RESW") || 
        		instr.get(i).equals("RESB") || instr.get(i).equals("End")) {

            objCode.add("######");
        }
//if it was BYTE then we get the reference number and save it in a string temp
        else if (instr.get(i).equals("BYTE"))
        {
            String[] values = ref.get(i).split("\t");
            if (values.length >= 2) {
                temp = values[1];
            
            //then add temp in the arraylist objCode
            objCode.add(temp);
            } else {
                System.out.println("Invalid line: " + ref.get(i));
            }
        }
//if it was WORD then we get the reference number and save it in an int number to convert it from decimal to hexa
        else if (instr.get(i).equals("WORD"))
        {
            temp = "";
            int number = Integer.parseInt(ref.get(i));

            String word = Integer.toHexString(number);
//checking if it's 6 digits after converting it to hexa number, if not we will add "0" until it's 6 digits
            for(int w = 6; w>word.length(); w--){
                temp += "0";
            }
          //increment the temp +word until it completes the 6 digits 
            temp += word;
          //save it in the arr list objCode
            objCode.add(temp);
        }
//if it's not Start nor RESW nor RESB nor End nor BYTE nor WORD then we will use OPTAB to normally calculate objCode for direct & indexed
        else{
        	//for loop to check the opcode length to start calculating every object code 
            for (int j = 0; j < OPTAB.length; j++) {
            	//if it
                if (instr.get(i).equals(OPTAB[j][0])) {
                    opcode = OPTAB[j][2];
                }
            }
            //to get the first half of object code "label" position and get if from the OPTAB 
            for (int j = 0; j < label.size(); j++) 
            {
                if (ref.get(i).contains(label.get(j))) {
                    pos = j;
                }
            }
            //indexed (X=1)
            if (ref.get(i).contains(",X")) {
                int indexed = Integer.parseInt("8000", 16);//to convert the number from hexa to decimal (the 1 that is in the indexed X)
                int address = Integer.parseInt(locctr.get(pos), 16);//get the label position to get the locctr and convert it from hexa to decimal
                int sum = indexed + address; //adding the indexed to the address part
                String num = Integer.toHexString(sum);//converting the sum from decimal to string
                objCode.add(opcode + num);//adding the opcode part to sum to get the object code
            } else //direct X=0
                objCode.add(opcode + locctr.get(pos));
            //if the X is direct then we will only get the opcode from the label from the OPTAB then the address (the position of the locctr)
        }
    }
//to print out the Object Code after doing the calculation
    System.out.println("\n" + "*************Obj Code***********");
    System.out.println("*************Pass 2*************"+"\n");
    for (int i=0; i<objCode.size(); i++){
        System.out.println(objCode.get(i)); 
    }
    
//////////////////////HTE Record//////////////////////
	System.out.println("\n"+"*************HTE record*************" +"\n");
//////////////////////H record//////////////////////
    System.out.println("H."+label.get(0)+".00"+locctr.get(1)+".000"+length);
    
//////////////////////T record//////////////////////
    int lengthObjCode=0;
    String tRecord="";
    int a;
    temp = locctr.get(0);//start point
    pos = 0;
    int r = 0;
    //to get the first 10 instructions
    for( a=1; a < objCode.size()-1 ; a++) {
    	//checks if it's not RESW or RESB
        if(!instr.get(a).equals("RESW") && !instr.get(a).equals("RESB")) {
            tRecord+= objCode.get(a) + "";
            r = a;
        }
        //stop before RESW or RESB and get the size
        if(a % 10 == 0 || instr.get(a + 1).equals("RESW") ||
                instr.get(a + 1).equals("RESB") || objCode.size() -a < 10){

            if(instr.get(a).equals("RESW") || instr.get(a).equals("RESB")){
                continue;
            }
            //length of this text record
            lengthObjCode=Integer.parseInt(locctr.get(r + 1), 16)-
                    Integer.parseInt(locctr.get(pos), 16);

            String hexlength = Integer.toHexString(lengthObjCode);
            //if it less than 2 bits then add "0"
            if(hexlength.length() < 2){
                hexlength = "0" + hexlength;
            }
            
            if(instr.get(a).equals("WORD"))
            {
                temp = locctr.get(a);
                tRecord = "";
                int w = 0;

                for(w = a; w < objCode.size()-1; w++){
                    tRecord += objCode.get(w) + "";
                }

                lengthObjCode=Integer.parseInt(locctr.get(w), 16)-
                        Integer.parseInt(locctr.get(a), 16);

                hexlength = Integer.toHexString(lengthObjCode);

                if(hexlength.length() < 2)
                {
                    hexlength = "0" + hexlength;
                }
            }
            //print out T record
            System.out.println("T.00" + temp + "." + hexlength + "." + tRecord);

            if(instr.get(a).equals("WORD"))
            {
                break;
            }

            pos = a + 1;
            temp = locctr.get(a + 1);
            tRecord = "";
        }
    }
//////////////////////E record//////////////////////
    System.out.println("E.00"+locctr.get(0));

//////////////////////The whole SIC project//////////////////////
    System.out.println("\n" + "**************************SIC Project************************");
    System.out.println("Locctr " + "    Label " + " Instructions " + " Reference " + " Object Code");
    for (int i = 0; i < locctr.size(); i++) {
        if (i < label.size() && i < instr.size() && i < ref.size() && i < objCode.size()) {
            System.out.println(locctr.get(i) + " ---> " + label.get(i) + " ---> " + instr.get(i) + " ---> " + ref.get(i) + " ---> " + objCode.get(i));
        }
    }
    
    
	}
	public static void initialize () {
        OPTAB[0] = new String[] {"FIX", "1", "C4"};
        OPTAB[1] = new String[] {"FLOAT", "1", "C0"};
        OPTAB[2] = new String[] {"HIO", "1", "F4"};
        OPTAB[3] = new String[] {"NORM", "1", "C8"};
        OPTAB[4] = new String[] {"SIO", "1", "F0"};
        OPTAB[5] = new String[] {"TIO", "1", "F8"};
        OPTAB[6] = new String[] {"ADDR", "2", "90"};
        OPTAB[7] = new String[] {"CLEAR", "2", "B4"};
        OPTAB[8] = new String[] {"COMPR", "2", "A0"};
        OPTAB[9] = new String[] {"DIVR", "2", "9C"};
        OPTAB[10] = new String[] {"MULR", "2", "98"};
        OPTAB[11] = new String[] {"RMO", "2", "AC"};
        OPTAB[12] = new String[] {"SHIFTL", "2", "A4"};
        OPTAB[13] = new String[] {"SHIFTR", "2", "A8"};
        OPTAB[14] = new String[] {"SUBR", "2", "94"};
        OPTAB[15] = new String[] {"SVC", "2", "B0"};
        OPTAB[16] = new String[] {"TIXR", "2", "B8"};
        OPTAB[17] = new String[] {"ADD", "3", "18"};
        OPTAB[18] = new String[] {"ADDF", "3", "58"};
        OPTAB[19] = new String[] {"AND", "3", "40"};
        OPTAB[20] = new String[] {"COMP", "3", "28"};
        OPTAB[21] = new String[] {"COMPF", "3", "88"};
        OPTAB[22] = new String[] {"DIV", "3", "24"};
        OPTAB[23] = new String[] {"DIVF", "3", "64"};
        OPTAB[24] = new String[] {"J", "3", "3C"};
        OPTAB[25] = new String[] {"JEQ", "3", "30"};
        OPTAB[26] = new String[] {"JGT", "3", "34"};
        OPTAB[27] = new String[] {"JLT", "3", "38"};
        OPTAB[28] = new String[] {"JSUB", "3", "48"};
        OPTAB[29] = new String[] {"LDA", "3", "00"};
        OPTAB[30] = new String[] {"LDB", "3", "68"};
        OPTAB[31] = new String[] {"LDCH", "3", "50"};
        OPTAB[32] = new String[] {"LDF", "3", "70"};
        OPTAB[33] = new String[] {"LDL", "3", "08"};
        OPTAB[34] = new String[] {"LDS", "3", "6C"};
        OPTAB[35] = new String[] {"LDT", "3", "74"};
        OPTAB[36] = new String[] {"LDX", "3", "04"};
        OPTAB[37] = new String[] {"LPS", "3", "D0"};
        OPTAB[38] = new String[] {"MUL", "3", "20"};
        OPTAB[39] = new String[] {"MULF", "3", "60"};
        OPTAB[40] = new String[] {"OR", "3", "44"};
        OPTAB[41] = new String[] {"RD", "3", "D8"};
        OPTAB[42] = new String[] {"RSUB", "3", "4C"};
        OPTAB[43] = new String[] {"SSK", "3", "EC"};
        OPTAB[44] = new String[] {"STA", "3", "0C"};
        OPTAB[45] = new String[] {"STB", "3", "78"};
        OPTAB[46] = new String[] {"STCH", "3", "54"};
        OPTAB[47] = new String[] {"STF", "3", "80"};
        OPTAB[48] = new String[] {"STI", "3", "D4"};
        OPTAB[49] = new String[] {"STL", "3", "14"};
        OPTAB[50] = new String[] {"STS", "3", "7C"};
        OPTAB[51] = new String[] {"STSW", "3", "E8"};
        OPTAB[52] = new String[] {"STT", "3", "84"};
        OPTAB[53] = new String[] {"STX", "3", "10"};
        OPTAB[54] = new String[] {"SUB", "3", "1C"};
        OPTAB[55] = new String[] {"SUBF", "3", "5C"};
        OPTAB[56] = new String[] {"TD", "3", "E0"};
        OPTAB[57] = new String[] {"TIX", "3", "2C"};
        OPTAB[58] = new String[] {"WD", "3", "DC"};
    }

}
   