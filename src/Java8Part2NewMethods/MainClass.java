package Java8Part2NewMethods;

import java.util.ArrayList;

//1. Given a string print every third character only if it a vowel
//2. rotate string
//3. reverse the given string
//4. check whether a given string is palindrome
//

//5. A group of soldiers sitting in circle and have only one sword with them. Instead of getting caught by
//enemies they decide to die. So the plan is every soldier kills person sitting right to him
//and passes sword. But one soldier intends to get caught, problem is where should he sit in circle
//so that we wont get killed.
//



import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainClass {

	public static void main(String[] args) {
	
		
		thirdVowel("Helloeverybody");
		rotate("Helloeverybody", 4);
		reverse("Helloeverybody");
		palindrome("Helloeverybody");
		palindrome("anitalavalatina");
		reverse2("Helloeverybody");
		
		List<String> soldierList = Stream.iterate(1, num -> num+1).limit(100)
				.map(num ->{ 
					String str = String.valueOf(num);
					return str;})
				.collect(Collectors.toList());
		killSoldiers(soldierList);
		
		System.out.println(killSoldiers(soldierList));

	}
	
	static void thirdVowel(String str) {
		Character [] vowels = {'a','e','i','o','u'};
		List<Character> chars = IntStream.iterate(2, n -> n<str.length(),n -> n += 3).boxed()
		.filter(index -> {
			//boolean result = str.charAt(index) == 'a' || str.charAt(index) == 'e'||str.charAt(index) == 'i'|| str.charAt(index) == 'o'|| str.charAt(index) == 'u';
			boolean result = Arrays.asList(vowels).contains(str.charAt(index));
			return result;
			})
		.map(index -> {
			return str.charAt(index);})
		.collect(Collectors.toList());
		
		chars.stream().forEach(c -> System.out.print(c));
		System.out.println();
	}
	
	static void rotate(String str,int numRoration) {
		List<String> stringList = Arrays.asList(str.split(""));
		Collections.rotate(stringList,numRoration);
		String newString =  stringList.stream().reduce("", (acum,string) -> acum+string);
		System.out.println(newString);
	}
	
	static void reverse(String str) {
		List<String> stringList = Arrays.asList(str.split(""));
		stringList.stream();
		String reversed = str.chars()
        .mapToObj(c -> (char)c)
        .reduce("", (s,c) -> {
//        	System.out.println("s " + s);
//        	System.out.println("c " + c);
        	return c+s;}, (s1,s2) -> {
//            	System.out.println("s1 " +s1);
//            	System.out.println("s2 " +s2);
        		return s2+s1;});
		System.out.println(reversed);
	}
	
	static void palindrome(String str) {
		boolean result = str.chars()
        .mapToObj(c -> (char)c)
        .reduce("", (s,c) -> {
//        	System.out.println("s " + s);
//        	System.out.println("c " + c);
        	return c+s;}, (s1,s2) -> {
//            	System.out.println("s1 " +s1);
//            	System.out.println("s2 " +s2);
        		return s2+s1;})
        .equalsIgnoreCase(str);
		
		System.out.println(result);
	}
	
	static void reverse2(String str) {
		String result=str.chars()
		        .mapToObj(c -> String.valueOf((char)c))
		        .reduce("", (acum,myChar) -> myChar+acum);
		System.out.println(result);
	}
	
	private static List<String> killSoldiers(final List<String> soldiersList) { //By Ranjitha
		Predicate<Integer> isEven = i -> i % 2 == 0;
		Predicate<Integer> isOdd = Predicate.not(isEven);

		Predicate<String> isEvenIndex = str -> (soldiersList.indexOf(str)+1)%2 == 0;
		List<String> soldiersList1 = new ArrayList<>();

		if(soldiersList.size() == 2) return soldiersList;

		if(soldiersList.size() > 2) {
		soldiersList1 = soldiersList.stream().filter(Predicate.not(isEvenIndex)).collect(Collectors.toList());
		}
		if(soldiersList.size()%2 != 0)
		Collections.rotate(soldiersList1, 1);

		soldiersList1 = killSoldiers(soldiersList1);//Recursive function.

		return soldiersList1;
		}
	
	

}
