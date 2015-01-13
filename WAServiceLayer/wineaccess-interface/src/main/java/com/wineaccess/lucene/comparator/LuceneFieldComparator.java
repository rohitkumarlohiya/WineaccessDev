package com.wineaccess.lucene.comparator;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.FieldComparator;

public class LuceneFieldComparator extends FieldComparator<String> {

	private String[] values;
	private String[] currentReaderValues;
	private String field;
	private String bottom;

	public LuceneFieldComparator(int numHits, String field) {
		values = new String[numHits];
		this.field = field;
	}

	/**
	 * (override)
	 * 
	 * @see org.apache.lucene.search.FieldComparator#compare(int, int)
	 */
	@Override
	public int compare(int slot1, int slot2) {
		final String val1 = values[slot1];
		final String val2 = values[slot2];
		int retVal;
		if (val1 == null) {
			if (val2 == null) {
				retVal = 0;
			}
			retVal = -1;
		} else if (val2 == null) {
			retVal = 1;
		} else {
			retVal = String.CASE_INSENSITIVE_ORDER.compare(val1, val2);
		}
		return retVal;
	}

	/**
	 * (override)
	 * 
	 * @see org.apache.lucene.search.FieldComparator#compareBottom(int)
	 */
	@Override
	public int compareBottom(int doc) {
		final String val2 = currentReaderValues[doc];
		int retVal;
		if (bottom == null) {
			if (val2 == null) {
				retVal = 0;
			}
			retVal = -1;
		} else if (val2 == null) {
			retVal = 1;
		} else {
			retVal = bottom.compareTo(val2);
		}
		return retVal;
	}

	/**
	 * (override)
	 * 
	 * @see org.apache.lucene.search.FieldComparator#copy(int, int)
	 */
	@Override
	public void copy(int slot, int doc) {
		values[slot] = currentReaderValues[doc];
	}

	/**
	 * (override)
	 * 
	 * @see org.apache.lucene.search.FieldComparator#setNextReader(org.apache.lucene.index.IndexReader,
	 *      int)
	 */
	@Override
	public void setNextReader(IndexReader reader, int docBase)
			throws IOException {
		currentReaderValues = FieldCache.DEFAULT.getStrings(reader, field);
	}

	/**
	 * (override)
	 * 
	 * @see org.apache.lucene.search.FieldComparator#setBottom(int)
	 */
	@Override
	public void setBottom(final int bottom) {
		this.bottom = values[bottom];
	}

	/**
	 * (override)
	 * 
	 * @see org.apache.lucene.search.FieldComparator#value(int)
	 */
	@Override
	public String value(int slot) {
		return values[slot];
	}

	/**
	 * (override)
	 * 
	 * @see org.apache.lucene.search.FieldComparator#compareValues(java.lang.Object,
	 *      java.lang.Object)
	 */
	@Override
	public int compareValues(String val1, String val2) {
		int retVal;
		if (val1 == null) {
			if (val2 == null) {
				retVal = 0;
			}
			retVal = -1;
		} else if (val2 == null) {
			retVal = 1;
		} else {
			retVal = String.CASE_INSENSITIVE_ORDER.compare(val1, val2);
		}
		return retVal;
	}
}
