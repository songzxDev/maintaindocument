package cn.cgztb.maintaindocument.service.impl;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.cgztb.maintaindocument.dao.TabMaintainDocumentCostMapper;
import cn.cgztb.maintaindocument.domain.MaintainDocumentCost;
import cn.cgztb.maintaindocument.domain.TemplateDataGrid;
import cn.cgztb.maintaindocument.model.TabMaintainDocumentCost;
import cn.cgztb.maintaindocument.service.MaintainDocumentCostServiceI;

@Service("maintainDocumentCostService")
public class MaintainDocumentCostServiceImpl implements MaintainDocumentCostServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MaintainDocumentCostServiceImpl.class);

	private TabMaintainDocumentCostMapper costMapper;

	public TabMaintainDocumentCostMapper getCostMapper() {
		return costMapper;
	}

	@Autowired
	public void setCostMapper(TabMaintainDocumentCostMapper costMapper) {
		this.costMapper = costMapper;
	}

	@Override
	public MaintainDocumentCost save(MaintainDocumentCost model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MaintainDocumentCost edit(MaintainDocumentCost model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(MaintainDocumentCost model) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public MaintainDocumentCost getEntity(MaintainDocumentCost model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateDataGrid<MaintainDocumentCost> getEntities(MaintainDocumentCost model) throws Exception {
		TemplateDataGrid<MaintainDocumentCost> dataGrid = new TemplateDataGrid<MaintainDocumentCost>();
		List<MaintainDocumentCost> templateList = new ArrayList<MaintainDocumentCost>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		String contractCode = "";
		String reservedColumn2 = "";
		String reservedColumn3 = "";
		String department = "";
		String professionType = "";
		if (model != null) {
			contractCode = model.getContractCode();
			reservedColumn2 = model.getReservedColumn2();
			reservedColumn3 = model.getReservedColumn3();
			department = model.getDepartment();
			professionType = model.getProfessionType();
		}
		conditions.put("contractCode", contractCode);
		conditions.put("reservedColumn2", reservedColumn2);
		conditions.put("reservedColumn3", reservedColumn3);
		conditions.put("department", department);
		conditions.put("professionType", professionType);

		List<TabMaintainDocumentCost> costTabList = costMapper.selectByConditions(conditions);

		if (costTabList != null && !costTabList.isEmpty()) {
			for (TabMaintainDocumentCost costTab : costTabList) {
				MaintainDocumentCost cost = new MaintainDocumentCost();
				org.springframework.beans.BeanUtils.copyProperties(costTab, cost);
				templateList.add(cost);
			}
		}
		dataGrid.setTemplateList(templateList);

		logger.info(JSON.toJSON(dataGrid));
		return dataGrid;
	}

	
}
