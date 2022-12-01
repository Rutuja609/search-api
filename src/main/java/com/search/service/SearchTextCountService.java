package com.search.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.search.beans.SearchTextCount;

@Service
public interface SearchTextCountService {

	List<SearchTextCount> countTextOccurrence(List<String> searchTexts);

	List<SearchTextCount> maxTextOccurrence(int numberOfTextLimit);
}