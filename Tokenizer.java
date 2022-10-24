package theoryOfProgramming;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Tokenizer 
{
	static String identifyingLexeme(String str)//carl kevin galvan
    {
        String strString = "",str1="",delimeter = ";";
	    ArrayList<String> output = new ArrayList<String>();
        ArrayList<String> output1 = new ArrayList<String>();
        String[] dataType1 = {"String", "int", "double", "char", "float"};
		String[] operator1  = {"=", "+", "-"};
        int strLength = str.length();;
		int count=0,count1=0;
        boolean stringQuoteOpen = false; 

        List dataType = Arrays.asList(dataType1);
        List operator = Arrays.asList(operator1);  
		for(int x=0;x<strLength;x++)//=
		{                             
			//identification of the dataType
			strString = Character.toString(str.charAt(x));    
            //---------------------------------delimeter----------------------------
            if(strString.equals(delimeter))
            {              
                str1+=strString;
                output.add(str1);
                output1.add("<delimeter>");
            }
            //---------------------String(value)-------------------------------      
            else if((str.charAt(x)=='"') && (count==0))
            {           
                str1+=strString;
                count=1;
                stringQuoteOpen=true;      
            }
            else if((stringQuoteOpen==true) && (count==1) && (str.charAt(x)!='"'))
            {          
                str1+=strString;
            }           
            else if((str.charAt(x)=='"') && (count==1))
            {
                str1+=strString;
                output.add(str1);
                output1.add("<value>");
                str1="";
                stringQuoteOpen=false; 
            }
             //---------------------Character(value)-------------------------------      
             else if((strString.equals("'")) && (count1==0))
             {             
                 str1+=strString;
                 count1=1;
                 stringQuoteOpen=true;      
             }
             else if((stringQuoteOpen==true) && (count1==1) && (strString.equals("'")==false))
             {          
                 str1+=strString;
             }           
             else if(strString.equals("'") && (count1==1))
             {
                 str1+=strString;
                 output.add(str1);
                 output1.add("<value>");
                 str1="";
                 stringQuoteOpen=false; 
                 count1=0;
             }
            //------------------------------int(value)-------------------------------
            else if(((Character.isDigit(str.charAt(x))||str.charAt(x)=='.') && (stringQuoteOpen==false)))
            {
                str1+=strString;                
                if(Character.isDigit(str.charAt(x+1))==false && str.charAt(x+1)!='.')
                {
                    output.add(str1);
                    output1.add("<value>");
                    str1="";
                }
            }
             //------------------------------------------------------------------
            else if((Character.isLetter(str.charAt(x))==true) && (stringQuoteOpen==false))
            {               
                str1+=strString;
            //-------------------------data_type----------------------------------
                if(dataType.contains(str1))
                {
                    output.add(str1);
                    output1.add("<data_type>");
                    str1="";
                }
            //--------------------------identifier----------------------------
                else if((str.charAt(x+1)==' ' && dataType.contains(str1)==false) || (str.charAt(x+1)==';' && dataType.contains(str1)==false))
                {
                    output.add(str1);
                    output1.add("<identifier>");
                    str1="";
                }               
            }
            //----------------------------assignment_operator-----------------------------
            else if(operator.contains(strString))//12
            {
                output.add(strString);
                output1.add("<assignment_operator>");
            }                 
		}
        String result = output1.toString().replace(" ", "").replace("]", "").
        replace("[", "").replace(",", "");
        //return result;
        System.out.println("output: "+output);
        System.out.println(result);
        return result;
	}
	public static void main(String[] args) 
	{	
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Source Language: ");     
		String str = scan.nextLine();
        String lexemes=identifyingLexeme(str);
        System.out.println();
        System.out.print("Output is: ");	
        System.out.println(lexemes);
	}   
}
