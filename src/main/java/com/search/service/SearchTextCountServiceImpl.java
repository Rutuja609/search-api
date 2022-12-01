package com.search.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.search.beans.SearchTextCount;
import com.search.repo.SearchTextRepository;

@Service
public class SearchTextCountServiceImpl implements SearchTextCountService {

	@Autowired
	protected SearchTextRepository searchTextRepository;

	@Value("${data.isCaseSensitive:}")
	private boolean isCaseSensitive;

	/**
	 * This method counts all text occurrences for user input texts from data.txt file
	 * @return List of text and number of text counts.
	 */
	
	@Override
	public List<SearchTextCount> countTextOccurrence(List<String> searchTexts) {
		List<SearchTextCount> searchTextCountList = new ArrayList<>();
		Map<String, Integer> searchTextCountMap = countAllTextOccurrence();
		for(String searchText : searchTexts) {
			Integer count = searchTextCountMap.get(isCaseSensitive ? searchText : searchText.toLowerCase());
			if(count != null) {
				searchTextCountList.add(new SearchTextCount(searchText, count));
			}else {
				searchTextCountList.add(new SearchTextCount(searchText, 0));
			}
		}
		return searchTextCountList;
	}

	/**
	 * This method finds all text occurrences in descending manner from data.txt file
	 * @return List of text and number of text counts.
	 */
	
	@Override
	public List<SearchTextCount> maxTextOccurrence(int numberOfTextLimit) {
		List<SearchTextCount> searchTextCountList = Collections.emptyList();
		Map<String, Integer> searchTextCountMap = countAllTextOccurrence();
		List<SearchTextCount> searchTextCountSorterList = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : searchTextCountMap.entrySet()) {
			searchTextCountSorterList.add(new SearchTextCount(entry.getKey(), entry.getValue()));
		}
		Collections.sort(searchTextCountSorterList, (o1, o2) -> Integer.compare(o2.getCount(), o1.getCount()));
		if(numberOfTextLimit > searchTextCountSorterList.size()) {
			numberOfTextLimit = searchTextCountSorterList.size();
		}
		searchTextCountList = searchTextCountSorterList.subList(0, numberOfTextLimit);
		return searchTextCountList;
	}

	/**
	 * This method counts the all text occurrences from data.txt file
	 * @return Map of text and number of text counts.
	 */
	
	private Map<String, Integer> countAllTextOccurrence() {
		Map<String, Integer> searchTextCountMap = new HashMap<>();
		List<String> textData = searchTextRepository.getTextData();
		for(String textDataItem : textData) {
			if(!isCaseSensitive) {
				textDataItem = textDataItem.toLowerCase();
			}
			Integer count = searchTextCountMap.get(textDataItem);
			if(count == null) {
				count = 1;
			} else {
				count++;
			}
			searchTextCountMap.put(textDataItem, count);
		}
		return searchTextCountMap;
	}
}
