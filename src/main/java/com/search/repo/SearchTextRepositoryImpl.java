package com.search.repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import static com.search.constants.SearchTextCountConstants.REPLACEALL;
import static com.search.constants.SearchTextCountConstants.NOSPACE;
import static com.search.constants.SearchTextCountConstants.SPACE;
import static com.search.constants.SearchTextCountConstants.SPLIT;

/**
 * This SearchTextRepositoryImpl class reads the value from specified text file.
 *
 */

@Repository
public class SearchTextRepositoryImpl implements SearchTextRepository {

	@Value("${data.repository.fileLocation:}")
	private Resource textData;

	@Override
	public List<String> getTextData() {
		List<String> textData = Collections.emptyList();
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(this.textData.getInputStream()))){
			String rawTextData = buffer.lines().map(line -> line.replaceAll(REPLACEALL, NOSPACE)).collect(Collectors.joining(SPACE));
			textData = Arrays.asList(rawTextData.replaceAll(REPLACEALL,SPACE).split(SPLIT));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return textData;
	}
}