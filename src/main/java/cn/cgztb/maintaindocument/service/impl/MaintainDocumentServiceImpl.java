package cn.cgztb.maintaindocument.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cgztb.maintaindocument.domain.MaintainDocument;
import cn.cgztb.maintaindocument.domain.MaintainDocumentCost;
import cn.cgztb.maintaindocument.domain.MaintainDocumentCostOther;
import cn.cgztb.maintaindocument.domain.MaintainDocumentInvestment;
import cn.cgztb.maintaindocument.domain.MaintainDocumentInvestmentOther;
import cn.cgztb.maintaindocument.domain.TemplateDataGrid;
import cn.cgztb.maintaindocument.service.MaintainDocumentCostOtherServiceI;
import cn.cgztb.maintaindocument.service.MaintainDocumentCostServiceI;
import cn.cgztb.maintaindocument.service.MaintainDocumentInvestmentOtherServiceI;
import cn.cgztb.maintaindocument.service.MaintainDocumentInvestmentServiceI;
import cn.cgztb.maintaindocument.service.MaintainDocumentServiceI;
import cn.cgztb.maintaindocument.util.MaintainDocumentUniversalUtil;

@Service("maintainDocumentService")
public class MaintainDocumentServiceImpl implements MaintainDocumentServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MaintainDocumentServiceImpl.class);

	private static MaintainDocumentUniversalUtil universalUtil = MaintainDocumentUniversalUtil.getInstance();

	private MaintainDocumentCostServiceI costService;

	private MaintainDocumentCostOtherServiceI costOtherService;

	private MaintainDocumentInvestmentServiceI investmentService;

	private MaintainDocumentInvestmentOtherServiceI investmentOtherService;

	public MaintainDocumentCostServiceI getCostService() {
		return costService;
	}

	@Autowired
	public void setCostService(MaintainDocumentCostServiceI costService) {
		this.costService = costService;
	}

	public MaintainDocumentCostOtherServiceI getCostOtherService() {
		return costOtherService;
	}

	@Autowired
	public void setCostOtherService(MaintainDocumentCostOtherServiceI costOtherService) {
		this.costOtherService = costOtherService;
	}

	public MaintainDocumentInvestmentServiceI getInvestmentService() {
		return investmentService;
	}

	@Autowired
	public void setInvestmentService(MaintainDocumentInvestmentServiceI investmentService) {
		this.investmentService = investmentService;
	}

	public MaintainDocumentInvestmentOtherServiceI getInvestmentOtherService() {
		return investmentOtherService;
	}

	@Autowired
	public void setInvestmentOtherService(MaintainDocumentInvestmentOtherServiceI investmentOtherService) {
		this.investmentOtherService = investmentOtherService;
	}

	/**
	 * 
	 * @Title: getMaintainDocument
	 * @Description: 获取Excel文档中的成本类页签【22~39】、投资类页签【1~22】的所有数据信息
	 * @param params
	 * @return
	 * @throws Exception
	 * @see cn.cgztb.maintaindocument.service.MaintainDocumentServiceI#getMaintainDocument(java.util.Map)
	 */
	@Override
	public MaintainDocument getMaintainDocument(Map<String, Object> params) throws Exception {
		String costSheetNames[] = {};
		String costOtherSheetNames[] = {};
		String investmentSheetNames[] = {};
		String investmentOtherSheetNames[] = {};
		
		MaintainDocument maintainDocument = new MaintainDocument();
		// 用于存储成本类页签【22、23、24、25、26、27、29、30、31、32、33、34、35、37、38、39】的 Map<String, Object> 集合
		Map<String, Object> costBeans = new HashMap<String, Object>();
		// 用于存储成本类页签【36】的 Map<String, Object> 集合
		Map<String, Object> costOtherBeans = new HashMap<String, Object>();
		// 用于存储投资类页签【1、2、3、4、5、6、7、8、9、14、15、16、17、18、19、20、21】的 Map<String, Object> 集合
		Map<String, Object> investmentBeans = new HashMap<String, Object>();
		// 用于存储投资类页签【10、11、12、13】的 Map<String, Object> 集合
		Map<String, Object> investmentOtherBeans = new HashMap<String, Object>();

		if (params != null && !params.isEmpty()) {
			costSheetNames = (String[]) params.get("costSheetNames");
			costOtherSheetNames = (String[]) params.get("costOtherSheetNames");
			investmentSheetNames = (String[]) params.get("investmentSheetNames");
			investmentOtherSheetNames = (String[]) params.get("investmentOtherSheetNames");
		}
		// 获取成本类页签【22、23、24、25、26、27、29、30、31、32、33、34、35、37、38、39】的 Map<String, Object> 集合
		if (costSheetNames != null && costSheetNames.length > 0) {
			for (String costsheetName : costSheetNames) {
				// 成本类页签【25、27、30、31、37】区分专业类型，每一个专业类型的后续审批环节均不相同，故添加此方法 {method : filterSpecialContractCode} 进行相应的数据处理
				MaintainDocumentCost model = filterSpecialContractCode(costsheetName);
				TemplateDataGrid<MaintainDocumentCost> dataGrid = new TemplateDataGrid<MaintainDocumentCost>();
				dataGrid = costService.getEntities(model);
				// 查询：当前页签中的所有二级部门信息
				List<MaintainDocumentCost> costTemplateList = dataGrid.getTemplateList();
				List<MaintainDocumentCost> costList = new ArrayList<MaintainDocumentCost>();
				for (MaintainDocumentCost cost : costTemplateList) {
					model.setReservedColumn3(cost.getDepartment());
					dataGrid = costService.getEntities(model);
					// 查询：当前页签中的某个二级部门的所有三级处室的信息
					List<MaintainDocumentCost> costListChildren = dataGrid.getTemplateList();
					cost.setCostChildren(costListChildren);
					costList.add(cost);
				}
				String costKey = generateSheetName(costsheetName);
				costBeans.put(costKey, costList);
			}
		}
		// 获取存储成本类页签【36】的 Map<String, Object> 集合
		if (costOtherSheetNames != null && costOtherSheetNames.length > 0) {
			for (String costOtherSheetName : costOtherSheetNames) {
				MaintainDocumentCostOther model = new MaintainDocumentCostOther();
				TemplateDataGrid<MaintainDocumentCostOther> dataGrid = new TemplateDataGrid<MaintainDocumentCostOther>();
				model.setReservedColumn3("");
				model.setContractCode(costOtherSheetName);
				dataGrid = costOtherService.getEntities(model);
				// 查询：当前页签中的所有二级部门信息
				List<MaintainDocumentCostOther> costOtherTemplateList = dataGrid.getTemplateList();
				List<MaintainDocumentCostOther> costOtherList = new ArrayList<MaintainDocumentCostOther>();
				for (MaintainDocumentCostOther costOther : costOtherTemplateList) {
					model.setReservedColumn3(costOther.getDepartment());
					dataGrid = costOtherService.getEntities(model);
					// 查询：当前页签中的某个二级部门的所有三级处室的信息
					List<MaintainDocumentCostOther> costOtherListChildren = dataGrid.getTemplateList();
					costOther.setCostOtherChildren(costOtherListChildren);
					costOtherList.add(costOther);
				}
				String costOtherKey = generateSheetName(costOtherSheetName);
				costOtherBeans.put(costOtherKey, costOtherList);
			}
		}
		// 获取投资类页签【1、2、3、4、5、6、7、8、9、14、15、16、17、18、19、20、21】的 Map<String, Object> 集合
		if (investmentSheetNames != null && investmentSheetNames.length > 0) {
			for (String investmentSheetName : investmentSheetNames) {
				MaintainDocumentInvestment model = new MaintainDocumentInvestment();
				TemplateDataGrid<MaintainDocumentInvestment> dataGrid = new TemplateDataGrid<MaintainDocumentInvestment>();
				model.setReservedColumn3("");
				model.setContractCode(investmentSheetName);
				dataGrid = investmentService.getEntities(model);
				// 查询：当前页签中的所有二级部门信息
				List<MaintainDocumentInvestment> investmentTemplateList = dataGrid.getTemplateList();
				List<MaintainDocumentInvestment> investmentList = new ArrayList<MaintainDocumentInvestment>();
				for (MaintainDocumentInvestment investment : investmentTemplateList) {
					model.setReservedColumn3(investment.getDepartment());
					dataGrid = investmentService.getEntities(model);
					// 查询：当前页签中的某个二级部门的所有三级处室的信息
					List<MaintainDocumentInvestment> investmentListChildren = dataGrid.getTemplateList();
					investment.setInvestmentChildren(investmentListChildren);
					investmentList.add(investment);
				}
				String investmentKey = generateSheetName(investmentSheetName);
				investmentBeans.put(investmentKey, investmentList);
			}
		}
		// 获取投资类页签【10、11、12、13】的 Map<String, Object> 集合
		if (investmentOtherSheetNames != null && investmentOtherSheetNames.length > 0) {
			for (String investmentOtherSheetName : investmentOtherSheetNames) {
				MaintainDocumentInvestmentOther model = new MaintainDocumentInvestmentOther();
				TemplateDataGrid<MaintainDocumentInvestmentOther> dataGrid = new TemplateDataGrid<MaintainDocumentInvestmentOther>();
				model.setReservedColumn3("");
				model.setContractCode(investmentOtherSheetName);
				dataGrid = investmentOtherService.getEntities(model);
				// 查询：当前页签中的所有二级部门信息
				List<MaintainDocumentInvestmentOther> investmentOtherTemplateList = dataGrid.getTemplateList();
				List<MaintainDocumentInvestmentOther> investmentOtherList = new ArrayList<MaintainDocumentInvestmentOther>();
				for (MaintainDocumentInvestmentOther investmentOther : investmentOtherTemplateList) {
					model.setReservedColumn3(investmentOther.getDepartment());
					dataGrid = investmentOtherService.getEntities(model);
					// 查询：当前页签中的某个二级部门的所有三级处室的信息
					List<MaintainDocumentInvestmentOther> investmentOtherListChildren = dataGrid.getTemplateList();
					investmentOther.setInvestmentOtherChildren(investmentOtherListChildren);
					investmentOtherList.add(investmentOther);
				}
				String investmentOtherKey = generateSheetName(investmentOtherSheetName);
				investmentOtherBeans.put(investmentOtherKey, investmentOtherList);
			}
		}

		if (costBeans != null && !costBeans.isEmpty()) {
			maintainDocument.setCostBeans(costBeans);
		}
		if (costOtherBeans != null && !costOtherBeans.isEmpty()) {
			maintainDocument.setCostOtherBeans(costOtherBeans);
		}
		if (investmentBeans != null && !investmentBeans.isEmpty()) {
			maintainDocument.setInvestmentBeans(investmentBeans);
		}
		if (investmentOtherBeans != null && !investmentOtherBeans.isEmpty()) {
			maintainDocument.setInvestmentOtherBeans(investmentOtherBeans);
		}
		return maintainDocument;
	}

	/**
	 * 
	 * @Title: filterSpecialContractCode
	 * @Description: 成本类页签【25、27、30、31、37】区分专业类型，每一个专业类型的后续审批环节均不相同，故添加此方法进行相应的数据处理
	 * @param contractCode
	 *          页签所代表的合同类型，默认和页签名称是相同的
	 * @return 可用于作为查询条件的（MaintainDocumentCost）对象
	 * @return: MaintainDocumentCost
	 */
	private MaintainDocumentCost filterSpecialContractCode(String contractCode) {
		MaintainDocumentCost model = new MaintainDocumentCost();
		model.setReservedColumn3("");
		if (!universalUtil.stringIsEmpty(contractCode) && contractCode.equals("25_1")) {
			model.setContractCode("25");
			model.setProfessionType("大修理");
		} else if (!universalUtil.stringIsEmpty(contractCode) && contractCode.equals("25_2")) {
			model.setContractCode("25");
			model.setProfessionType("单次日常修理审批");
		} else if (!universalUtil.stringIsEmpty(contractCode) && contractCode.equals("27_1")) {
			model.setContractCode("27");
			model.setProfessionType("维护耗材、低价值仪器仪表、客户接入成本、其他");
		} else if (!universalUtil.stringIsEmpty(contractCode) && contractCode.equals("27_2")) {
			model.setContractCode("27");
			model.setProfessionType("规费（检测费）");
		} else if (!universalUtil.stringIsEmpty(contractCode) && contractCode.equals("30_1")) {
			model.setContractCode("30");
			model.setProfessionType("展览费、产品宣传费（活动执行）、产品宣传费（其他）");
		} else if (!universalUtil.stringIsEmpty(contractCode) && contractCode.equals("30_2")) {
			model.setContractCode("30");
			model.setProfessionType("企业宣传费");
		} else if (!universalUtil.stringIsEmpty(contractCode) && contractCode.equals("31_1")) {
			model.setContractCode("31");
			model.setProfessionType("产品广告费");
		} else if (!universalUtil.stringIsEmpty(contractCode) && contractCode.equals("31_2")) {
			model.setContractCode("31");
			model.setProfessionType("企业形象广告费");
		} else if (!universalUtil.stringIsEmpty(contractCode) && contractCode.equals("37_1")) {
			model.setContractCode("37");
			model.setProfessionType("信息支撑系统日常维修、信息支撑系统大修理、信息支撑系统维保、仓储租赁费、其他");
		} else if (!universalUtil.stringIsEmpty(contractCode) && contractCode.equals("37_2")) {
			model.setContractCode("37");
			model.setProfessionType("警卫消防费");
		} else {
			model.setContractCode(contractCode);
		}
		return model;
	}

	/**
	 * 
	 * @Title: generateSheetName
	 * @Description: TODO
	 * @param sheetName
	 * @return
	 * @return: String
	 */
	private String generateSheetName(String sheetName) {
		StringBuilder stb = new StringBuilder("SHEET_");
		stb.append(sheetName);
		return stb.toString();
	}

	/**
	 * 
	 * @Title: getJudgeAmountBeans
	 * @Description: 查询Excel文档中涉及部门配置信息的所有页签中，包含金额判断的部门信息， 并将每一个页签中存在金额判断的部门名称存储到List集合中，同时将这个List添加到Map集合中
	 * @param sheetNames
	 * @return Map<String,Object>，其中key值：页签名称_部门列的下标值，value值：页签中涉及金额判断的部门名称的List<String>的集合
	 * @throws Exception
	 * @see cn.cgztb.maintaindocument.service.MaintainDocumentServiceI#getJudgeAmountBeans(java.lang.String[])
	 */
	@Override
	public Map<String, Object> getJudgeAmountBeans(String[] sheetNames) throws Exception {
		Map<String, Object> beans = new HashMap<String, Object>();
		// Excel文档中的每一个有关部门信息的页签中的（部门名称列）所在当前页签的列的下标均为3（列的下标从0开始，0代表第一列），页签【36】的部门列下标为4
		int departmentNameCellIndex = 3;
		/*
		 * 数组【sheetNames】的每一项元素都是由合同类型加页签名称组成的，例如：成本类、Excel文档页签【22】的元素定义是：（cost_22）
		 */
		if (sheetNames != null && sheetNames.length > 0) {
			for (String sheetName : sheetNames) {
				if (universalUtil.stringIsEmpty(sheetName) || sheetName.indexOf("_") == -1) {
					continue;
				}
				// 解析数组中的每一项元素，params[0]是合同类型，params[1]是页签名称
				String params[] = sheetName.split("_");
				// 合同类型
				String contractType = params[0];
				// Excel文档中的页签名称
				String contractCode = params[1];
				// 用于存储当前页签中的涉及金额判断的部门名称的 List<String> 集合
				List<String> departmentNames = new ArrayList<String>();
				if (contractCode.equals("36")) {
					departmentNameCellIndex = 4;
				}
				// "cost" 类型代表当前处理数据为：成本类页签【22、23、24、25、26、27、29、30、31、32、33、34、35、37、38、39】中的数据
				if (contractType.equals("cost")) {
					MaintainDocumentCost model = new MaintainDocumentCost();
					TemplateDataGrid<MaintainDocumentCost> dataGrid = new TemplateDataGrid<MaintainDocumentCost>();
					model.setReservedColumn2("JUDGE_AMOUNT");
					model.setContractCode(contractCode);
					dataGrid = costService.getEntities(model);
					List<MaintainDocumentCost> costTemplateList = dataGrid.getTemplateList();
					if (costTemplateList != null && !costTemplateList.isEmpty()) {
						for (MaintainDocumentCost cost : costTemplateList) {
							if (cost == null) {
								continue;
							}
							String departmentName = cost.getDepartment();
							departmentNames.add(departmentName);
						}
					}
					// "costOther" 类型代表当前处理数据为：成本类页签【36】中的数据，此页签仅包含上海市财务、审计、法律三个部门
				} else if (contractType.equals("costOther")) {
					MaintainDocumentCostOther model = new MaintainDocumentCostOther();
					TemplateDataGrid<MaintainDocumentCostOther> dataGrid = new TemplateDataGrid<MaintainDocumentCostOther>();
					model.setReservedColumn2("JUDGE_AMOUNT");
					model.setContractCode(contractCode);
					dataGrid = costOtherService.getEntities(model);
					List<MaintainDocumentCostOther> costOtherTemplateList = dataGrid.getTemplateList();
					if (costOtherTemplateList != null && !costOtherTemplateList.isEmpty()) {
						for (MaintainDocumentCostOther costOther : costOtherTemplateList) {
							if (costOther == null) {
								continue;
							}
							String departmentName = costOther.getDepartment();
							departmentNames.add(departmentName);
						}
					}
					// "investment" 类型代表当前处理数据为：投资类页签【1、2、3、4、5、6、7、8、9、14、15、16、17、18、19、20、21】中的数据
				} else if (contractType.equals("investment")) {
					MaintainDocumentInvestment model = new MaintainDocumentInvestment();
					TemplateDataGrid<MaintainDocumentInvestment> dataGrid = new TemplateDataGrid<MaintainDocumentInvestment>();
					model.setReservedColumn2("JUDGE_AMOUNT");
					model.setContractCode(contractCode);
					dataGrid = investmentService.getEntities(model);
					List<MaintainDocumentInvestment> investmentTemplateList = dataGrid.getTemplateList();
					if (investmentTemplateList != null && !investmentTemplateList.isEmpty()) {
						for (MaintainDocumentInvestment investment : investmentTemplateList) {
							if (investment == null) {
								continue;
							}
							String departmentName = investment.getDepartment();
							departmentNames.add(departmentName);
						}
					}
					// "investmentOther" 类型代表当前处理数据为：投资类页签【10、11、12、13】中的数据，这四个页签中的（专业负责人）没有金额判断，（专业线总经理）不分金额区间判断
				} else if (contractType.equals("investmentOther")) {
					MaintainDocumentInvestmentOther model = new MaintainDocumentInvestmentOther();
					TemplateDataGrid<MaintainDocumentInvestmentOther> dataGrid = new TemplateDataGrid<MaintainDocumentInvestmentOther>();
					model.setReservedColumn2("JUDGE_AMOUNT");
					model.setContractCode(contractCode);
					dataGrid = investmentOtherService.getEntities(model);
					List<MaintainDocumentInvestmentOther> investmentOtherTemplateList = dataGrid.getTemplateList();
					if (investmentOtherTemplateList != null && !investmentOtherTemplateList.isEmpty()) {
						for (MaintainDocumentInvestmentOther investmentOther : investmentOtherTemplateList) {
							if (investmentOther == null) {
								continue;
							}
							String departmentName = investmentOther.getDepartment();
							departmentNames.add(departmentName);
						}
					}
				}
				beans.put(contractCode + "_" + departmentNameCellIndex, departmentNames);
			}// 遍历数组【sheetNames】的 for 循环结束
		}
		return beans;
	}

	@Override
	public Serializable editMaintainDocument(Map<String, Object> params) throws Exception {
		// TODO
		return null;
	}
}
