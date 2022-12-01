package com.search.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import com.search.beans.SearchTextCount;
import com.search.repo.SearchTextRepository;

@SpringBootTest(classes= {SearchTextCountServiceImplTest.class})
public class SearchTextCountServiceImplTest {

	@Spy
	private SearchTextRepository textDataRepository = new SearchTextRepository() {

		@Override
		public List<String> getTextData() {
			return Arrays.asList(new String[] { "Donec", "Duis", "Sed", "Duis", "Sed", "Duis" });
		}
	};

	@InjectMocks
	SearchTextCountServiceImpl stService;

	private void assertTextCount(List<SearchTextCount> textCounts, String expectedText, int expectedCount) throws Exception {
		Predicate<SearchTextCount> filterPredicate = textCount -> expectedText.equals(textCount.getText());
		assertEquals(1, textCounts.stream().filter(filterPredicate).count());
		Optional<SearchTextCount> textCount = textCounts.stream().filter(filterPredicate).findFirst();
		assertTrue(textCount.isPresent());
		assertEquals(expectedText, textCount.get().getText());
		assertEquals(expectedCount, textCount.get().getCount());
	}

	@Test
	public void testCountTextOccurrence() throws Exception {
		List<String> searchTexts = Arrays.asList(new String[] { "Duis", "Sed", "Donec" });
		List<SearchTextCount> textCounts = stService.countTextOccurrence(searchTexts);

		assertNotNull(textCounts);
		assertEquals(3, textCounts.size());

		assertTextCount(textCounts, "Duis", 3);
		assertTextCount(textCounts, "Sed", 2);
		assertTextCount(textCounts, "Donec", 1);
	}

	private void assertTextCount(List<SearchTextCount> textCounts, String expectedText, int expectedCount, int orderIndex)
			throws Exception {
		SearchTextCount textCount = textCounts.get(orderIndex);
		assertEquals(expectedText, textCount.getText());
		assertEquals(expectedCount, textCount.getCount());
	}

	@Test
	public void testMaxTextOccurrence() throws Exception {
		List<SearchTextCount> textCounts = stService.maxTextOccurrence(3);

		assertNotNull(textCounts);
		assertEquals(3, textCounts.size());

		assertTextCount(textCounts, "duis", 3, 0);
		assertTextCount(textCounts, "sed", 2, 1);
		assertTextCount(textCounts, "donec", 1, 2);
	}
}