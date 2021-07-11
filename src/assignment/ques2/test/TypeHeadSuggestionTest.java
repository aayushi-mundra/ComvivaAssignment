package assignment.ques2.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import assignment.ques2.TypeHeadTasks;

class TypeHeadSuggestionTest {

	@Test
	void test1() {
		TypeHeadTasks typeHeadTasks = new TypeHeadTasks();
		
		List<String> list = Arrays.asList("abcg", "abd", "abch", "ac");
		
		for (String string : list) {
			typeHeadTasks.insert(string);
		}
		
		List<String> suggestions = typeHeadTasks.getSuggestions("ab");
		Collections.sort(suggestions);
		
		List<String> outputList = Arrays.asList("abcg", "abch","abd");
		
		//1. test
		assertEquals(outputList, suggestions);
		
		//******************
		
		suggestions = typeHeadTasks.getSuggestions("a");
		Collections.sort(suggestions);
		
		outputList = Arrays.asList("abcg", "abch", "abd", "ac");
		
		//2. test
		assertEquals(outputList, suggestions);
		
		//*************
		
		suggestions = typeHeadTasks.getSuggestions("x");
		//3. test
		assertEquals(Collections.EMPTY_LIST, suggestions);
	}
	
	@Test
	void testDuplicateWordsInInputList() {
		TypeHeadTasks typeHeadTasks = new TypeHeadTasks();
		
		List<String> list = Arrays.asList("abcg", "abd", "abch", "ac", "abcg", "ac");
		
		for (String string : list) {
			typeHeadTasks.insert(string);
		}
		
		List<String> suggestions = typeHeadTasks.getSuggestions("ab");
		Collections.sort(suggestions);
		
		List<String> outputList = Arrays.asList("abcg", "abch","abd");
		
		//1. test
		assertEquals(outputList, suggestions);
	}
	
	@Test
	void test2() {
		TypeHeadTasks typeHeadTasks = new TypeHeadTasks();
		
		List<String> list = Arrays.asList("amaz", "amazon", "amazonprime", "amazing", "xyz", "forest", "rain", "letter", "get");
		
		for (String string : list) {
			typeHeadTasks.insert(string);
		}
		
		List<String> suggestions = typeHeadTasks.getSuggestions("amaz");
		Collections.sort(suggestions);
		
		
		List<String> outputList = Arrays.asList("amaz", "amazing","amazon", "amazonprime");

		//1. test
		assertEquals(outputList, suggestions);
		
		//******************
		
		suggestions = typeHeadTasks.getSuggestions("amazi");
		Collections.sort(suggestions);

		outputList = Arrays.asList("amazing");
		
		//2. test
		assertEquals(outputList, suggestions);

		//*************
		suggestions = typeHeadTasks.getSuggestions("p");
		//3. test
		assertEquals(Collections.EMPTY_LIST, suggestions);
	}

}
