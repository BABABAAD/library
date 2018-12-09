package com.oracle.ddbookmarket.biz;

import java.util.List;

import com.oracle.ddbookmarket.model.SmallType;

public interface SmallTypeBiz {

	boolean save(SmallType smallType);


	List<SmallType> findAllSmalltype(int bd);

	int findBidById(int sid);
}
