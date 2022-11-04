package THEORYOFPROGRAMMING;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SemanticsAnalysis
{
    public static ArrayList<String>coll_lexemes = new ArrayList<>();
    private static final ArrayList<String> dataTypes = new ArrayList<String>();
    private static final char doub_quote='"';
    private static final String single_quote="'";
    private static final String numbers = "1234567890";
    public static void main(String[] args)
    {    
        Scanner scan = new Scanner(System.in);
		System.out.println("Enter Source Language: ");     
		String input = scan.nextLine();
        tokenize(input);
    }
    static void analysingSemantics(String ver,ArrayList<String> element,ArrayList<String> output1){
        int indexOfDataType = output1.indexOf("<data_type>");
        int indeOfValue = output1.indexOf("<value>");
        if(element.get(indexOfDataType).equals("String") && ver.equals("Semantically Correct!")){
            ver = analyzingString(ver,element.get(indeOfValue));
        }
        else if(element.get(indexOfDataType).equals("int")&& ver.equals("Semantically Correct!")){
            ver = analyzingInteger(ver,element.get(indeOfValue));
        }
        else if(element.get(indexOfDataType).equals("char")&& ver.equals("Semantically Correct!")){
            ver = analyzingCharacter(ver,element.get(indeOfValue));
        }
        else if(element.get(indexOfDataType).equals("double")&& ver.equals("Semantically Correct!"))
        {
            ver = analyzingDouble(ver,element.get(indeOfValue));
        }
        else if(element.get(indexOfDataType).equals("double")&& ver.equals("Semantically Correct!")){
            ver =analyzingBoolean(ver,element.get(indeOfValue));
        }
        System.out.println(ver);
    }
    static String analyzingString(String ver, String value){
        Pattern specialCharacters = Pattern.compile("[@./*#$%^&(){} || a-zA-z || [0-9] ]");   
        String strValue1 = Character.toString(value.charAt(0)), 
        strValue2 = Character.toString(value.charAt(value.length()-1));
        Matcher m = specialCharacters.matcher(strValue1);
        boolean b = m.find();
        if(strValue1.equals("'")||strValue1.equals("'") || b){
            ver = "Semantically Incorrect!";
        }
        else{           
            ver = "Semantically Correct!";
        }
        return ver;
    }
    static String analyzingInteger(String ver, String value){
        Pattern specialCharacters = Pattern.compile("[@./*#$%^&(){} || a-zA-z || ]");//[0-9]
        Matcher m = specialCharacters.matcher(value);
        boolean b = m.find();
        for(int i=0; i<1;i++){
           if(b || value.indexOf('"')!=-1 || value.indexOf("'")!=-1){
              ver = "Semantically Incorrect!";
           }
           else{            
              ver = "Semantically Correct!";
           }
        }
        return ver;
    }
    static String analyzingCharacter(String ver, String value){
        String strvalue1= Character.toString(value.charAt(0)), strvalue2= Character.toString(value.charAt(value.length()-1));  
        if(strvalue1.equals("'") && strvalue2.equals("'")&&value.length()==3){  
            ver = "Semantically Correct!";
        }
       else{  
        ver = "Semantically Incorrect!";
       }
        return ver;
    }
    static String analyzingDouble(String ver, String value){
        Pattern specialCharacters = Pattern.compile("[@,/*#$%^&(){} || a-zA-z || ]");//[0-9]
        Matcher m = specialCharacters.matcher(value);
        boolean b = m.find();
        for(int i=0; i<1;i++){
           if(b || value.indexOf('"')!=-1 || value.indexOf("'")!=-1 || value.contains(".")==false){
              ver = "Semantically Incorrect!";
           }
           else{        
              ver = "Semantically Correct!";
           }
        }
        return ver;
    }
    static String analyzingBoolean(String ver, String value){
        if(value.equals("true")||value.equals("false")){
            ver = "Semantically Correct!";
        }
        else{
            ver = "Semantically Incorrect!";
        }
        return ver;
    }
    static void analysingSyntax(String lexeme, ArrayList<String> element, ArrayList<String> output1)//String lexeme, ArrayList<String> data
    {   
        String str = "<data_type><identifier><assignment_operator><value><delimeter>";
        String str1 = "<data_type><identifier><delimeter>";
        lexeme = lexeme.replace(" ", "");
        String ver = "";
        if(str.equals(lexeme) || str1.equals(lexeme) && lexeme.contains("<delimeter>")){
            ver = "Semantically Correct!";
        }
        else if(!str.equals(lexeme) || !str1.equals(lexeme) && lexeme.contains("<delimeter>")==false){
            ver="Semantically Incorrect!";
        }
        analysingSemantics(ver,element,output1);
    }
    public static void tokenize(String input){
	dataTypes.add("int");
	dataTypes.add("double");
	dataTypes.add("String");
	dataTypes.add("char");
	dataTypes.add("boolean");
    String tokens = "";
    coll_lexemes=splitLexemes(coll_lexemes,input);
    tokens = matchLexemes(coll_lexemes);
    ArrayList<String> output1 = new ArrayList<String>();
        String str = "";
        for(int i=0; i<tokens.length();i++){       
            if(tokens.charAt(i)=='>'){
                str+=tokens.charAt(i);
                output1.add(str);
                str="";
            }
            else{
                str+=tokens.charAt(i);
            }
        }		
    analysingSyntax(tokens, coll_lexemes, output1);
  }
  public static ArrayList<String> splitLexemes(ArrayList<String> coll_lexemes, String input){
		String lexeme="";
		for(int x=0;x<input.length();x++){
			String temp = Character.toString(input.charAt(x)); 
			if(input.charAt(x) == '"' || temp.equals("'")){
				int count = 1;
				String tempStr="";
				for(int y=x;y<input.length();y++){
					char input_char = input.charAt(y);
					if((input_char == '"'|| Character.toString(input_char).equals("'")) && count==1){
						count++;
						tempStr+= Character.toString(input_char);
					}
					else if ((input_char == '"'|| Character.toString(input_char).equals("'")) && count==2){
						count++;
						tempStr = tempStr+Character.toString(input_char);
						coll_lexemes.add(tempStr);
						x=y;
						break;
					}
					else{
						tempStr+=Character.toString(input_char);
					}
				}
				lexeme="";		
			}
			else if(temp.equals("=")){
				setColl_Lexemes(lexeme);
				coll_lexemes.add("=");
				lexeme="";
			}
			else if(temp.equals(";")){
				setColl_Lexemes(lexeme);
				coll_lexemes.add(";");
				lexeme="";
			}
			else if(x==input.length()-1){
				lexeme+=temp;
				setColl_Lexemes(lexeme);
			}
			else if(dataTypes.contains(lexeme)&& input.charAt(x+1)==' ') {
				setColl_Lexemes(lexeme);
				lexeme="";
			}
			else if(lexeme.isEmpty()==false && (temp.equals(" "))){
				setColl_Lexemes(lexeme);
				lexeme="";
			}
			else{			
				if(temp.equals(" ")){			
				}
				else{
					lexeme+=temp;
				}			
			}
		}
		return coll_lexemes;
	}
	public static void setColl_Lexemes(String lexeme){
		if(lexeme.isEmpty()==false){
			coll_lexemes.add(lexeme);
		}
    }
	public static String matchLexemes(ArrayList<String> coll_lexemes){
		String arr_token="";
		for(int y = 0;y<coll_lexemes.size();y++){
			String element = coll_lexemes.get(y);
			if(element.equals(";")){
				arr_token+= "<delimeter>";
			}
			else if (element.equals("=")){
				arr_token+= "<assignment_operator>";
			}
			else if(dataTypes.contains(element)){
				arr_token+= "<data_type> ";
			}
			else{
				element = element.replaceAll(" ","");
				if(element.charAt(0)==doub_quote)//(element.substring(0, element.length()-1).equals(Character.toString('"')))
                {
					arr_token+="<value>";
				}
				else if(Character.toString(element.charAt(0)).equals(single_quote)){
					arr_token+="<value>";
				}
				else if (element.contains(".")){
					arr_token+="<value>";
				}
				else if(element.equalsIgnoreCase("true")||element.equalsIgnoreCase("false")){
					arr_token+="<value>";
				}
				else{
					  boolean isNum=true;
					for(int i=0;i<element.length();i++){
					    String temp = String.valueOf(element.charAt(i));
					    if(numbers.contains(temp)){
							
						}
						else{
							isNum=false;
						}
					 }
					 if(isNum==true){
						arr_token+="<value>";
					 }
					 else{
						arr_token+="<identifier>";
					 }
				}
            }
        }	
		return arr_token;
	}
}
