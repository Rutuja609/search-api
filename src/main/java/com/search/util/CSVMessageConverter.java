package com.search.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import com.search.beans.CSVDataBean;
import static com.search.constants.SearchTextCountConstants.TEXT;
import static com.search.constants.SearchTextCountConstants.CSV;

/**
 * 
 * This converter will convert the response object to csv data.
 *
 */

public class CSVMessageConverter extends AbstractHttpMessageConverter<CSVDataBean>{

	public CSVMessageConverter() {
		super(new MediaType(TEXT, CSV));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return CSVDataBean.class.isAssignableFrom(clazz);
	}

	@Override
	protected CSVDataBean readInternal(Class<? extends CSVDataBean> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return null;
	}

	@Override
	protected void writeInternal(CSVDataBean t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withDelimiter('|').withRecordSeparator("\n");
		try (CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputMessage.getBody()), csvFileFormat)) {
			for(String[] record : t.getData()) {
				csvPrinter.printRecord(Arrays.asList(record));
			}
		}
	}
}
