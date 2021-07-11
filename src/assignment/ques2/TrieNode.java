package assignment.ques2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {

	private char nodeChar;
	private Map<Character, TrieNode> childNodes;
	boolean isWord;
	private List<String> wordList;
	

	public List<String> getWordList() {
		return wordList;
	}

	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}

	public TrieNode(char nodeChar) {
		this.nodeChar = nodeChar;
		this.childNodes = new HashMap<Character, TrieNode>();
		wordList = new ArrayList<String>();
	}
	
	public TrieNode(char nodeChar, TrieNode parentNode) {
		this.nodeChar = nodeChar;
		this.childNodes = new HashMap<Character, TrieNode>();
	}
	
	public char getNodeChar() {
		return nodeChar;
	}
	public void setNodeChar(char nodeChar) {
		this.nodeChar = nodeChar;
	}
	public Map<Character, TrieNode> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(Map<Character, TrieNode> childNodes) {
		this.childNodes = childNodes;
	}
	
	public boolean isWord() {
		return isWord;
	}

	public void setIsWord(boolean isWord) {
		this.isWord = isWord;
	}
	
	@Override
	public String toString() {
		return "["+nodeChar+","+isWord+"  "+wordList+"  "+childNodes+"]";
	}
	
}
