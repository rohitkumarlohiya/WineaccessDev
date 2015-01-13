package com.wineaccess.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;

public class SearchUtils {
	
	public static String refineSearchKeyword(String keyword) {
		keyword = keyword.replace("-", " * ");
		return keyword.replace(" ", "* AND *");
	}
	
	public static void doExclusion(BooleanQuery luceneQuery, List<SearchExclusionPO> exclusions) throws ParseException {
		if (exclusions != null && !exclusions.isEmpty()) {
			QueryParser queryParserExclusion = new MultiFieldQueryParser(Version.LUCENE_34, new String [] {"id"}, new StandardAnalyzer(Version.LUCENE_34));
			String queryStringExclusion = "";
			int i = 0;
			for(SearchExclusionPO searchExclusionPO : exclusions) {
				i++;
				if (exclusions.size() == i){
					queryStringExclusion = queryStringExclusion + "id:" + searchExclusionPO.getId();
				} else {
					queryStringExclusion = queryStringExclusion + "id:" + searchExclusionPO.getId() + " OR ";
				}
			}
			Query luceneQueryExclusion = queryParserExclusion.parse(queryStringExclusion);
			luceneQuery.add(luceneQueryExclusion, Occur.MUST_NOT);
		}
	}
	
	public static FullTextQuery getFullTextQuery(FullTextEntityManager fullTextEntityManager, Query query, Class clazz, Sort sort, int offSet, int limit) {
		
		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, clazz);
		fullTextQuery.setSort(sort);
		fullTextQuery.setFirstResult(offSet - 1);
		if (limit != -1) {
			fullTextQuery.setMaxResults(limit);	
		}
		return fullTextQuery;
	}
	
	public static final Map<String, String> getMapFromObject1(Class objectClass) {
		
        Map<String, String> propertyMap = new HashMap<String, String>();
        Method[] methods = objectClass.getMethods();

        for (Method m : methods) {
        	Class<?> returnType = m.getReturnType();
    		if (m.getName().startsWith("get") && !returnType.equals(Class.class)) {
    			 String key = m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4);
    			 propertyMap.put(key, returnType.getSimpleName());
            }
        }
        return propertyMap;
    }
	
	public static Sort getSort(String sortBy, boolean orderDirect,  Map<String, String> propertyMap) {
		if (propertyMap.get(sortBy).equals("Long")) {
			return new Sort(new SortField(sortBy, SortField.LONG, orderDirect));
		} else if (propertyMap.get(sortBy).equals("Boolean")) {
			return new Sort(new SortField(sortBy, SortField.INT, orderDirect));
		} else if (propertyMap.get(sortBy).equals("String")) {
			return new Sort(new SortField(sortBy, SortField.STRING, orderDirect));
		}
		return new Sort(new SortField("id", SortField.LONG, orderDirect));
	}
	
	public static boolean getSortOrderDirection(String sortOrederBy) {
		return sortOrederBy.equals("1") ? false : true;
	}
}
