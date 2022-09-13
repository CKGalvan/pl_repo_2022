package theoryOfProgramming;

public class Palindrome
 {
    public static void main(String[] args) 
	{
		//Sample 2
		String s = "Noonw",new_s = "";
		for(int i=s.length()-1;i>=0;i--)
		{
			new_s += Character.toString(s.charAt(i));
		}
		if(s.equalsIgnoreCase(new_s))
		{
			System.out.println("Is a Palindrome!");
		}
		else 
		{
			System.out.println("Not a Palindrome!");
		}
	}
}
