package com.airtel.spchacker.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

	private List<String> matchedWord = new ArrayList<>();
	
	private Map<String,String> similarWord = new HashMap<>();

	public List<String> getMatchedWord() {
		return matchedWord;
	}

	public void setMatchedWord(List<String> matchedWord) {
		this.matchedWord = matchedWord;
	}

	public Map<String, String> getSimilarWord() {
		return similarWord;
	}

	public void setSimilarWord(Map<String, String> similarWord) {
		this.similarWord = similarWord;
	}
	
	
	
}
