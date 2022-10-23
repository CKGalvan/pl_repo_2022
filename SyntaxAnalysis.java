package theoryOfProgramming;
import java.util.ArrayList;
class SyntaxAnalysis
{
    public static void main(String[] args)//CARL KEVIN M. GALVAN
    {   
        String output1 = "<data_type> <identifier> <assignment_operator> <delimiter> <value> ";
        String output = "<data_type> <identifier> <assignment_operator> <value> <delimiter>";
        System.out.println(analysingSyntax(output));
    }
    static String analysingSyntax(String output)//
    {
        String str = "<data_type><identifier><assignment_operator><value><delimiter>";
        String str1 = "<data_type><identifier><delimiter>";
        output = output.replace(" ", "");
        String ver = "";
        if(str.equals(output) || str1.equals(output))
        {
            ver = "Syntax is Correct!";
        }
        else if(!str.equals(output) || !str1.equals(output))
        {
            ver="Syntax is Error!";
        }
        return ver;
    }
}