package cn.cgztb.maintaindocument.dao;

import java.util.List;
import java.util.Map;

import cn.cgztb.maintaindocument.model.TabMaintainDocumentInvestmentOther;

public interface TabMaintainDocumentInvestmentOtherMapper {
	int deleteByPrimaryKey(Long investmentId);

	int insert(TabMaintainDocumentInvestmentOther record);

	int insertSelective(TabMaintainDocumentInvestmentOther record);

	TabMaintainDocumentInvestmentOther selectByPrimaryKey(Long investmentId);

	int updateByPrimaryKeySelective(TabMaintainDocumentInvestmentOther record);

	int updateByPrimaryKey(TabMaintainDocumentInvestmentOther record);

	List<TabMaintainDocumentInvestmentOther> selectByConditions(Map<String, Object> params);
}