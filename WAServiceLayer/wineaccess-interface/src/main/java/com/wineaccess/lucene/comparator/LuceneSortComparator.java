package com.wineaccess.lucene.comparator;

import java.io.IOException;

import org.apache.lucene.search.FieldComparator;
import org.apache.lucene.search.FieldComparatorSource;

public class LuceneSortComparator extends FieldComparatorSource {

	@Override
	public FieldComparator<?> newComparator(String fieldname, int numHits, int sortPos, boolean reversed) throws IOException {
		return new LuceneFieldComparator(numHits, fieldname);
	}
}
