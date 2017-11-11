package cn.cgztb.maintaindocument.dao;

import java.util.List;
import java.util.Map;

import cn.cgztb.maintaindocument.model.TabMaintainDocumentInvestment;

public interface TabMaintainDocumentInvestmentMapper {
	int deleteByPrimaryKey(Long investmentId);

	int insert(TabMaintainDocumentInvestment record);

	int insertSelective(TabMaintainDocumentInvestment record);

	TabMaintainDocumentInvestment selectByPrimaryKey(Long investmentId);

	int updateByPrimaryKeySelective(TabMaintainDocumentInvestment record);

	int updateByPrimaryKey(TabMaintainDocumentInvestment record);

	List<TabMaintainDocumentInvestment> selectByConditions(Map<String, Object> params);
}