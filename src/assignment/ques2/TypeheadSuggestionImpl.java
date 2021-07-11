package assignment.ques2;

import java.util.Arrays;
import java.util.List;

public class TypeheadSuggestionImpl {

	
	public static void main(String[] args) {
		List<String> list = Arrays.asList("abcg", "abd", "abch", "ac", "abcg");
		
		TypeHeadTasks headTasks = new TypeHeadTasks();
		
		for (String string : list) {
			headTasks.insert(string);
		}
		
		List<String> suggestions = headTasks.getSuggestions("ab");
		
//		List<String> suggestions = headTasks.getSuggestions("x");
		
//		List<String> suggestions = headTasks.getSuggestions("hel");
		//hel, hell, hello, help, helps, helping
		
//		System.out.println("desc>>> "+headTasks.getRootTrieNode());
		System.out.println("suggestions>>>>>> "+suggestions);
		//abcg, abd,, abch
	}
}
