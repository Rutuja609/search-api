package com.search.repo;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchTextRepository {

	List<String> getTextData();
}