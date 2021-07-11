package assignment.ques2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeHeadTasks {

	TrieNode rootTrieNode;
	
	public TrieNode getRootTrieNode() {
		return rootTrieNode;
	}

	public TypeHeadTasks() {
		this.rootTrieNode = new TrieNode(Character.MIN_VALUE);
	}
	
	
	public void insert(String string) {
		if(string == null || string.length() == 0 || string.trim().length() == 0) return;
		string = string.toLowerCase();
		char[] stringArr = string.toCharArray();
		
		Map<Character, TrieNode> map = rootTrieNode.getChildNodes();
		for (int i = 0; i < stringArr.length; i++) {
			char ch = stringArr[i];
			TrieNode childNode = map.get(ch);
			if(childNode == null) {
				childNode = new TrieNode(ch);
				map.put(ch, childNode);
			} 
			
			map = childNode.getChildNodes();
			if(i==stringArr.length-1) {
				List<String> strings = childNode.getWordList();
				if(!strings.contains(string)) {
					strings.add(string);
				}
				childNode.setIsWord(true);
			}
		}
	}
	
	public List<String> getSuggestions(String input) {
		List<String> list = new ArrayList<String>();
		if(input == null || input.length() == 0) return list;
		if(rootTrieNode.getChildNodes().isEmpty()) return list;
		if(!rootTrieNode.getChildNodes().containsKey(input.charAt(0))) return list;
		int index = 0;
		getSuggestionsRec(rootTrieNode, input, index, list);
		return list;
	}

	private void getSuggestionsRec(TrieNode root, String input, int index, List<String> list) {
		if (root == null)
			return;
		if (index < input.length()) {
			char currentChar = input.charAt(index);
			TrieNode node = root.getChildNodes().get(currentChar);
			getSuggestionsRec(node, input, index + 1, list);
			List<String> wordList = node.getWordList();
			for(int i=0;i<wordList.size();i++) {
				String str = wordList.get(i);
				if(str.length() >= input.length()) {
					list.add(str);
				}
			}
		} else {
			Map<Character, TrieNode> map = root.getChildNodes();
			for (Map.Entry<Character, TrieNode> entry : map.entrySet()) {
				TrieNode node = entry.getValue();
				getSuggestionsRec(node, input, index, list);
				if (node.isWord()) {
					List<String> wordList = node.getWordList();
					for(int i=0;i<wordList.size();i++) {
						String str = wordList.get(i);
						if(str.length() >= input.length()) {
							list.add(str);
						}
					}
				}
			}
		}
	}
	
	//	public List<String> getSuggestions(String input) {
		//	if(input == null || input.length() == 0) return Collections.EMPTY_LIST;
		//	List<List<String>> list = new ArrayList<List<String>>();
		//	int index = 0;
		//	getSuggestionsRec(rootTrieNode, input, index, list);
		//	List<String> outputList = new ArrayList<String>();
		//	for (List<String> strings : list) {
		//		outputList.addAll(strings);
		//	}
		//	return outputList;
	//}

//	private void getSuggestionsRec(TrieNode root, String input, int index, List<List<String>> list) {
//		
//		if(root == null) return;
//		System.out.println("in func>> "+root.getNodeChar());
////		if(root.isWord()) {
////			List<String> subList = new ArrayList<String>();
////			subList.add(root.getNodeChar()+"");
////			list.add(subList);
////			System.out.println("returning>>>>>> "+subList);
//////			return;
////		}
//		
//		if(index < input.length()) {
//			char currentChar = input.charAt(index);
//			System.out.println("1  currentChar>>>>>> "+currentChar);
//			TrieNode node = root.getChildNodes().get(currentChar);
//			getSuggestionsRec(node, input, index+1, list);
//			System.out.println("1 after currentChar>>>>>> "+currentChar+"  "+list+"  "+node.isWord());
//			for(int i=0;i<list.size();i++) {
//				List<String> innerList = list.get(i);
//				for(int k=0;k<innerList.size();k++) {
//					String str = innerList.get(k);
//					str = currentChar+str;
//					innerList.set(k, str);
//				}
//			}
////			list.add(subList);
//			System.out.println("currentChar>>>>>> "+list);
//		} else {
//			Map<Character, TrieNode> map = root.getChildNodes();
//			for(Map.Entry<Character, TrieNode> entry : map.entrySet()) {
//				TrieNode node = entry.getValue();
//				char currentChar = entry.getKey();
//				getSuggestionsRec(node, input, index, list);
//				System.out.println("2 current >>> "+currentChar+"   "+list+"  "+node.isWord);
//				
//				if(node.isWord()) {
//					if(!node.getChildNodes().isEmpty()) {
//						for(int i=0;i<list.size();i++) {
//							List<String> innerList = list.get(i);
//							for(int k=0;k<innerList.size();k++) {
//								String str = innerList.get(k);
//								str = currentChar+str;
//								innerList.set(k, str);
//							}
//						}
//					}
//					List<String> subList = new ArrayList<String>();
//					subList.add(currentChar+"");
//					list.add(subList);
//				} else if(!node.getChildNodes().isEmpty()) {
//					for(int i=0;i<list.size();i++) {
//						List<String> innerList = list.get(i);
//						for(int k=0;k<innerList.size();k++) {
//							String str = innerList.get(k);
//							str = currentChar+str;
//							innerList.set(k, str);
//						}
//					}
//				}
//				
////				System.out.println("after 11 current >>> "+currentChar+"   "+list);
////				if(!node.getChildNodes().isEmpty()) {
////					for(int i=0;i<list.size();i++) {
////						List<String> innerList = list.get(i);
////						for(int k=0;k<innerList.size();k++) {
////							String str = innerList.get(k);
////							str = currentChar+str;
////							innerList.set(k, str);
////						}
////					}
////				}
//				
////				if(!node.isWord()) {
////					for(int i=0;i<list.size();i++) {
////						List<String> innerList = list.get(i);
////						for(int k=0;k<innerList.size();k++) {
////							String str = innerList.get(k);
////							str = currentChar+str;
////							innerList.set(k, str);
////						}
////					}
////				}  else if(node.isWord()) {
////					List<String> subList = new ArrayList<String>();
////					subList.add(currentChar+"");
////					list.add(subList);
////				}
////				System.out.println("after current >>> "+currentChar+"   "+list);
//			}
////			list.addAll(subList);
////			System.out.println("22>>>>>> "+list);
//		}
//	}
	
//	private boolean isRootNode(TrieNode trieNode) {
//		if(trieNode == null) return false;
//		if(trieNode.getNodeChar() == Character.MIN_VALUE) return true;
//		return false;
//	}
	
}
