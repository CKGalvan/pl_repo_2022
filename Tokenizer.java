package theoryOfProgramming;
import java.util.ArrayList;
import java.util.Scanner;
public class Tokenizer 
{
    static String strString = "";
	static ArrayList<String> output = new ArrayList<String>();
    static ArrayList<String> output1 = new ArrayList<String>();
	static ArrayList<String> dataType = new ArrayList<String>();
	static ArrayList<String> operator = new ArrayList<String>();
	static String delimeter = ";";
	static char quoteForString= '"';
	public static void identify(String str)
	{
		String str1="";
        int strLength = str.length();
		int count =0,count1=0;
        boolean stringQuoteOpen = false;    
		for(int x=0;x<strLength;x++)//
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
                if(Character.isDigit(str.charAt(x+1))==false)
                {
                    output.add(str1);
                    output1.add("<value>");
                    str1="";
                }
            }
             //-------------------------------------------------------     
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
	}
	public static void main(String[] args) 
	{
		String[] dataType1 = {"String", "int", "double", "char", "float"};
		String[] operator1  = {"=", "+", "-"};
		for(int i=0;i<dataType1.length;i++)
		{
			dataType.add(dataType1[i]);
		}	
		for(int i=0; i<operator1.length;i++)
		{
			operator.add(operator1[i]);
		}	
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Source Language: ");     
		String str = scan.nextLine();
		identify(str);	
		//output.forEach(output ->{System.out.print(output);});	
        System.out.println();
        System.out.print("Output is: ");
        output1.forEach(output1 ->{System.out.print(output1);});		
	}   
}
