package cn.cgztb.maintaindocument.dao;

import java.util.List;
import java.util.Map;

import cn.cgztb.maintaindocument.model.TabMaintainDocumentCost;

public interface TabMaintainDocumentCostMapper {
	int deleteByPrimaryKey(Long costId);

	int insert(TabMaintainDocumentCost record);

	int insertSelective(TabMaintainDocumentCost record);

	TabMaintainDocumentCost selectByPrimaryKey(Long costId);

	int updateByPrimaryKeySelective(TabMaintainDocumentCost record);

	int updateByPrimaryKey(TabMaintainDocumentCost record);

	List<TabMaintainDocumentCost> selectByConditions(Map<String, Object> params);
}