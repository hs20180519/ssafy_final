package com.ssafy.model.service;

import com.ssafy.model.dao.HouseDealDao;
import com.ssafy.model.dao.HouseDealDaoImpl;
import com.ssafy.model.dto.HouseDeal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.xml.dtm.ref.DTMDefaultBaseIterators.ParentIterator;

public class HouseDealServiceImpl implements HouseDealService {

    private HouseDealDao houseDealDao = HouseDealDaoImpl.getInstance();

    private static final HouseDealService instance = new HouseDealServiceImpl();
    private HouseDealServiceImpl(){};

    public static HouseDealService getInstance() {return instance;}

    @Override
    public void addHouseDeal(HouseDeal houseDealDto) {
        HouseDealDaoImpl.getInstance().insertHouseDeal(houseDealDto);
    }

    @Override
    public List<HouseDeal> searchListAll() {
        return HouseDealDaoImpl.getInstance().selectList();
    }

    @Override
    public List<HouseDeal> searchListByLocation(String sidoCode, String gugunCode, String dongCode) {
        return HouseDealDaoImpl.getInstance().selectByLocation(sidoCode, gugunCode, dongCode);
    }
    
    @Override
    public List<HouseDeal> searchListByKeyword(String keyword, String year) {

        List<HouseDeal> houseDealList =  HouseDealDaoImpl.getInstance().selectRecentlyList(year);
        System.out.println(houseDealList);
        return kmp(houseDealList, keyword);
    }
    
    private int[] makeTable(String keyword) {
    	int keywordSize = keyword.length();
    	int[] table = new int[keywordSize];
    	int j = 0;
    	
    	for (int i = 1; i < keywordSize; i++) {
    		while (j > 0 && keyword.charAt(i) != keyword.charAt(j)) {
    			j = table[j - 1];
    		}
    		if (keyword.charAt(i) == keyword.charAt(j)) {
    			table[i] = ++j;
    		}
    	}
    	return table;
    }
    
    private List<HouseDeal> kmp(List<HouseDeal> houseDealList, String keyword) {
    	List<HouseDeal> searchDeal = new ArrayList<>();
    	int[] table = makeTable(keyword);
    	int keywordSize = keyword.length();
    	
    	for (HouseDeal houseDeal : houseDealList) {
    		String word = houseDeal.getAptNm();
			int wordSize = word.length();
			int j = 0;
			
    		for (int i = 0; i < wordSize; i++) {
        		while (j > 0 && word.charAt(i) != keyword.charAt(j)) {
        			j = table[j - 1];
    			}
        		if (word.charAt(i) == keyword.charAt(j)) {
        			if (j == keywordSize - 1) {
        				searchDeal.add(houseDeal);
        				break;
        			} else {
        				j++;
        			}
        		}
        	}
    	}
    	return searchDeal;
    }
}
