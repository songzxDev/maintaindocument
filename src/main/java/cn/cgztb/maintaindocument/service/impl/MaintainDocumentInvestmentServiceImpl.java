package cn.cgztb.maintaindocument.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cgztb.maintaindocument.dao.TabMaintainDocumentInvestmentMapper;
import cn.cgztb.maintaindocument.domain.MaintainDocumentInvestment;
import cn.cgztb.maintaindocument.domain.TemplateDataGrid;
import cn.cgztb.maintaindocument.model.TabMaintainDocumentInvestment;
import cn.cgztb.maintaindocument.service.MaintainDocumentInvestmentServiceI;

@Service("maintainDocumentInvestmentService")
public class MaintainDocumentInvestmentServiceImpl implements MaintainDocumentInvestmentServiceI {

	private TabMaintainDocumentInvestmentMapper investmentMapper;

	public TabMaintainDocumentInvestmentMapper getInvestmentMapper() {
		return investmentMapper;
	}

	@Autowired
	public void setInvestmentMapper(TabMaintainDocumentInvestmentMapper investmentMapper) {
		this.investmentMapper = investmentMapper;
	}

	@Override
	public MaintainDocumentInvestment save(MaintainDocumentInvestment model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MaintainDocumentInvestment edit(MaintainDocumentInvestment model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(MaintainDocumentInvestment model) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public MaintainDocumentInvestment getEntity(MaintainDocumentInvestment model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateDataGrid<MaintainDocumentInvestment> getEntities(MaintainDocumentInvestment model) throws Exception {
		TemplateDataGrid<MaintainDocumentInvestment> dataGrid = new TemplateDataGrid<MaintainDocumentInvestment>();
		List<MaintainDocumentInvestment> templateList = new ArrayList<MaintainDocumentInvestment>();
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
		List<TabMaintainDocumentInvestment> investmentTabList = investmentMapper.selectByConditions(conditions);
		if (investmentTabList != null && !investmentTabList.isEmpty()) {

			for (TabMaintainDocumentInvestment investmentTab : investmentTabList) {
				MaintainDocumentInvestment investment = new MaintainDocumentInvestment();
				org.springframework.beans.BeanUtils.copyProperties(investmentTab, investment);
				templateList.add(investment);
			}
		}
		dataGrid.setTemplateList(templateList);
		return dataGrid;
	}

}
