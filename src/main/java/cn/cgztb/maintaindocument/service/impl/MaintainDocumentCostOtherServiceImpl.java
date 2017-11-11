package cn.cgztb.maintaindocument.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cgztb.maintaindocument.dao.TabMaintainDocumentCostOtherMapper;
import cn.cgztb.maintaindocument.domain.MaintainDocumentCostOther;
import cn.cgztb.maintaindocument.domain.TemplateDataGrid;
import cn.cgztb.maintaindocument.model.TabMaintainDocumentCostOther;
import cn.cgztb.maintaindocument.service.MaintainDocumentCostOtherServiceI;

@Service("maintainDocumentCostOtherService")
public class MaintainDocumentCostOtherServiceImpl implements MaintainDocumentCostOtherServiceI {

	private TabMaintainDocumentCostOtherMapper costOtherMapper;

	public TabMaintainDocumentCostOtherMapper getCostOtherMapper() {
		return costOtherMapper;
	}

	@Autowired
	public void setCostOtherMapper(TabMaintainDocumentCostOtherMapper costOtherMapper) {
		this.costOtherMapper = costOtherMapper;
	}

	@Override
	public MaintainDocumentCostOther save(MaintainDocumentCostOther model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MaintainDocumentCostOther edit(MaintainDocumentCostOther model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(MaintainDocumentCostOther model) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public MaintainDocumentCostOther getEntity(MaintainDocumentCostOther model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateDataGrid<MaintainDocumentCostOther> getEntities(MaintainDocumentCostOther model) throws Exception {
		TemplateDataGrid<MaintainDocumentCostOther> dataGrid = new TemplateDataGrid<MaintainDocumentCostOther>();
		List<MaintainDocumentCostOther> templateList = new ArrayList<MaintainDocumentCostOther>();
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

		List<TabMaintainDocumentCostOther> costOtherTabList = costOtherMapper.selectByConditions(conditions);
		if (costOtherTabList != null && !costOtherTabList.isEmpty()) {
			for (TabMaintainDocumentCostOther costOtherTab : costOtherTabList) {
				MaintainDocumentCostOther costOther = new MaintainDocumentCostOther();
				org.springframework.beans.BeanUtils.copyProperties(costOtherTab, costOther);
				templateList.add(costOther);
			}
		}
		dataGrid.setTemplateList(templateList);
		return dataGrid;
	}
}
