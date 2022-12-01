package com.search.resource;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.search.beans.CSVDataBean;
import com.search.beans.SearchRequestBean;
import com.search.beans.SearchResponseBean;
import com.search.beans.SearchTextCountBean;
import com.search.exceptions.NegativeValueException;
import com.search.service.SearchTextCountService;

@RestController
@RequestMapping("/counter-api")
public class SearchTextCountResource {

	@Autowired
	protected SearchTextCountService searchTextCountService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<SearchResponseBean> search(@RequestBody SearchRequestBean searchRequest){
		List<SearchTextCountBean> textCounts = searchTextCountService.countTextOccurrence(searchRequest.getSearchText()).stream()
				.map(it -> new SearchTextCountBean(it.getText(), it.getCount())).collect(Collectors.toList());
		return new ResponseEntity<SearchResponseBean>(new SearchResponseBean(textCounts), HttpStatus.OK);
	}

	@RequestMapping(value = "/top/{limit}", produces = "text/csv")
	public ResponseEntity<CSVDataBean> top(@PathVariable("limit") int limit){
		if(limit > 0) {
		List<String[]> textCounts = searchTextCountService.maxTextOccurrence(limit).stream()
				.map(it -> new String[] {it.getText(), String.valueOf(it.getCount())}).collect(Collectors.toList());
		return new ResponseEntity<CSVDataBean>(new CSVDataBean(textCounts), HttpStatus.OK);
		}else {
			throw new NegativeValueException("Value " + limit + " should not be negative.");
		}
	}
}
