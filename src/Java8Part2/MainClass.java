package Java8Part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainClass {

	public static void main(String[] args) {
		thirdVowel("My text to test the method third vowel");
		rotate("MyString");
		reverse("Reverse");
		palindrome("anitalavalatina");
		killSoldiers(100);
	}
	
	static void thirdVowel(String str) {
		String [] vowels = {"a","e","i","o","u"};
		for(int i = 2; i<str.length(); i+=3) {
			if(Arrays.asList(vowels).contains(""+str.charAt(i)) )
			  System.out.print(str.charAt(i));
		}
		System.out.println();
	}
	
	static void rotate(String str) {
//		char [] array = str.toCharArray();
		
//		for(int i =0 ; i<array.length; i++) {
//			char lastChar = array[array.length-1];
//			for(int j=array.length-1;j>0;j--) {
//				array[j]=array[j-1];
//			}
//			array[0]=lastChar;
			
			LinkedList<String> arrayList = new LinkedList<String>();
			for(int i =0 ; i<str.length(); i++) {
				arrayList.add(""+str.charAt(i));
			}
			
			for(int i = 0; i<3;i++) {
				arrayList.addFirst(arrayList.getLast());
				arrayList.removeLast();
				arrayList.forEach(c -> System.out.print(c));
				System.out.println();
			}
			
//			Arrays.asList(array).forEach(c -> System.out.print(c));
			
//		}
		
		
	}
	
	static void rotate2(String str,int numOfCharsToRotate) {
		String rotated = str.substring(str.length() - numOfCharsToRotate) + str.substring(numOfCharsToRotate);
	}

	static void reverse(String str) {
		LinkedList<String> list = new LinkedList<String>();
		for(int i =0 ; i < str.length(); i++) {
			list.add(""+str.charAt(i));
		}
		list.descendingIterator().forEachRemaining(string -> System.out.print(string));
		System.out.println();
	}

	static void palindrome(String str) {
		String reverseString="";
		int strLength=str.length()-1;
		
		for(int i = strLength; i >=0 ; i--) {
			reverseString=reverseString+str.charAt(i);
		}
		
		System.out.println(str.equals(reverseString));
		
		
	}

	static void killSoldiers(int numSoldiers) {
		LinkedList<Integer> list= new LinkedList<Integer>();
		
		for(int i=1 ; i<=numSoldiers;i++) {
			list.add(i);
		}
		
		int index=0;
		int killer=0;
		
		while(list.size()>1) {
			index=killer+1;
			if(index>=list.size())
				index=0;
			list.remove(index);
			if(killer+1>=list.size())
				killer=-1;
			killer=killer+1;
		}
		
		list.stream().forEach(soldier -> System.out.println("Soldier number: " +soldier + " keep alive"));
		
	}
}
