import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException
	{		
		ArrayList<String> dict = new ArrayList<String>();
		File text = new File("Dictionary.txt");
	    Scanner scnr = new Scanner(text);
	    System.out.println("Start Dict In");
	    while(scnr.hasNextLine())
	    {
	        String line = scnr.nextLine();
	        dict.add(line);       
	    }
	    System.out.println("Done");
	   // scnr.close();
	    Stream<String> dictStream = dict.stream();
	      
		ArrayList<String> usrIn = new ArrayList<String>();
	    Scanner scnr2 = new Scanner(System.in);
	    String input = scnr2.nextLine();
	    System.out.println("UserInput recieved");
	    String[] inArr = input.split(" ");
	    for(int x = 0; x< inArr.length;x++)
	    {
	        String nxt = inArr[x];
	        usrIn.add(nxt);       
	    }
	    scnr2.close();
	    scnr.close();
	    Stream<String> usrInStream = usrIn.stream();  		
        for (int x : frequency(usrIn))
        	System.out.print(x +" ");
        System.out.println();
        ArrayList<String> mutateUsrIn = new ArrayList<String>();
        for (int j = 0;j<usrIn.size();j++)
        {
        	mutateUsrIn.add(mutateString(usrIn.get(j),'a','e'));
        }
        for(String s:mutateUsrIn)
        	System.out.println(s);
      
	for(int f = 0; f<mutateUsrIn.size();f++)
      {
    	Stream<String> dictCopy = dict.stream();  
      Stream<String> filterResult = dictCopy.filter(exists(mutateUsrIn.get(f)));
	 List<String> holden = filterResult.collect(Collectors.toList());
	 for(String str : holden){
		 System.out.print(str + " ");
	 }
	 holden.clear();
	 System.out.println("");
	  
      }
	}
	
	public static Predicate<String> exists(String c)
	{
		return s -> (s.length() == c.length())&& (exists2(s,c));

	}
	public static Function<String, String> mapTst(char r, char t)
	{
		return y->mutateString(y,r,t);
	}
	private static boolean exists2(String s, String b)
	{
		char[] c = b.toCharArray();
		if(s.length()!=c.length)
			return false;
		for(int x = 0;x<c.length;x++)
			if((c[x]!= '*')&&(c[x]!=s.charAt(x)))
					return false;
		//System.out.println(s);
		for(int z=0;z<c.length;z++)
			if(c[z]!='*')
				return true;	
		return false;
		
	}
    public static String mutateString(String s, char r, char t)
    {
    	
    	char[] c = s.toCharArray();
    	for(int x = 0; x<c.length;x++)
    		if (c[x]== r)
    			c[x]=t;
    		else
    			c[x]='*';
    	String lilS = new String(c);
    	return lilS;
    }
    public static int[] frequency(ArrayList<String> s)
    {
    	 String alphabet = "abcdefghijklmnopqrstuvwxyz";
    	 int[] countArray = new int[26];
    	for(int b=0;b<s.size();b++)
    	{
        char[] c = s.get(b).toCharArray();
        for (char x : c) 
        {
            for (int i = 0; i < alphabet.length(); i++) {
                if (alphabet.charAt(i) == x) {
                    countArray[i]++;
                }}}}
    	return countArray;
    }  
}

