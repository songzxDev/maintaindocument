package cn.cgztb.maintaindocument.dao;

import java.util.List;
import java.util.Map;

import cn.cgztb.maintaindocument.model.TabMaintainDocumentCostOther;

public interface TabMaintainDocumentCostOtherMapper {
	int deleteByPrimaryKey(Long costId);

	int insert(TabMaintainDocumentCostOther record);

	int insertSelective(TabMaintainDocumentCostOther record);

	TabMaintainDocumentCostOther selectByPrimaryKey(Long costId);

	int updateByPrimaryKeySelective(TabMaintainDocumentCostOther record);

	int updateByPrimaryKey(TabMaintainDocumentCostOther record);

	List<TabMaintainDocumentCostOther> selectByConditions(Map<String, Object> params);
}