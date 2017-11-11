package cn.cgztb.maintaindocument.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cgztb.maintaindocument.dao.TabMaintainDocumentInvestmentOtherMapper;
import cn.cgztb.maintaindocument.domain.MaintainDocumentInvestmentOther;
import cn.cgztb.maintaindocument.domain.TemplateDataGrid;
import cn.cgztb.maintaindocument.model.TabMaintainDocumentInvestmentOther;
import cn.cgztb.maintaindocument.service.MaintainDocumentInvestmentOtherServiceI;

@Service("maintainDocumentInvestmentOtherService")
public class MaintainDocumentInvestmentOtherServiceImpl implements MaintainDocumentInvestmentOtherServiceI {

	private TabMaintainDocumentInvestmentOtherMapper investmentOtherMapper;

	public TabMaintainDocumentInvestmentOtherMapper getInvestmentOtherMapper() {
		return investmentOtherMapper;
	}

	@Autowired
	public void setInvestmentOtherMapper(TabMaintainDocumentInvestmentOtherMapper investmentOtherMapper) {
		this.investmentOtherMapper = investmentOtherMapper;
	}

	@Override
	public MaintainDocumentInvestmentOther save(MaintainDocumentInvestmentOther model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MaintainDocumentInvestmentOther edit(MaintainDocumentInvestmentOther model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(MaintainDocumentInvestmentOther model) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public MaintainDocumentInvestmentOther getEntity(MaintainDocumentInvestmentOther model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateDataGrid<MaintainDocumentInvestmentOther> getEntities(MaintainDocumentInvestmentOther model) throws Exception {
		TemplateDataGrid<MaintainDocumentInvestmentOther> dataGrid = new TemplateDataGrid<MaintainDocumentInvestmentOther>();
		List<MaintainDocumentInvestmentOther> templateList = new ArrayList<MaintainDocumentInvestmentOther>();
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
		List<TabMaintainDocumentInvestmentOther> investmentOtherTabList = investmentOtherMapper.selectByConditions(conditions);
		if (investmentOtherTabList != null && !investmentOtherTabList.isEmpty()) {
			for (TabMaintainDocumentInvestmentOther investmentOtherTab : investmentOtherTabList) {
				MaintainDocumentInvestmentOther investmentOther = new MaintainDocumentInvestmentOther();
				org.springframework.beans.BeanUtils.copyProperties(investmentOtherTab, investmentOther);
				templateList.add(investmentOther);
			}
		}
		dataGrid.setTemplateList(templateList);
		return dataGrid;
	}

}
