package cn.cgztb.maintaindocument.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaintainDocumentCostOther {
	private Long costId;

	private String enableStatus;

	private String reservedColumn1;

	private String reservedColumn2;

	private String reservedColumn3;

	private Date createDate;

	private Date modifyDate;

	private String contractCode;

	private String contractType;

	private String professionType;

	private String professionDescription;

	private String department;

	private String chushijingli;

	private String chushijingliLoginId;

	private String quxiankuaiji;

	private String quxiankuaijiLoginId;

	private String bumenfuzongjingli;

	private String bumenfuzongjingliLoginId;

	private String bumenzongjingli;

	private String bumenzongjingliLoginId;

	private String zhuanyezhineng;

	private String zhuanyezhinengLoginId;

	private String chengbenguanli;

	private String chengbenguanliLoginId;

	private String zhuanyexianzongjingli;

	private String zhuanyexianzongjingliLoginId;

	private String caiwubushouquan;

	private String caiwubushouquanLoginId;

	private String shengfenfuzongjingli;

	private String shengfenfuzongjingliLoginId;

	private String shengfenzongjingli;

	private String shengfenzongjingliLoginId;

	private List<MaintainDocumentCostOther> costOtherChildren = new ArrayList<MaintainDocumentCostOther>();

	public Long getCostId() {
		return costId;
	}

	public void setCostId(Long costId) {
		this.costId = costId;
	}

	public String getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(String enableStatus) {
		this.enableStatus = enableStatus == null ? null : enableStatus.trim();
	}

	public String getReservedColumn1() {
		return reservedColumn1;
	}

	public void setReservedColumn1(String reservedColumn1) {
		this.reservedColumn1 = reservedColumn1 == null ? null : reservedColumn1.trim();
	}

	public String getReservedColumn2() {
		return reservedColumn2;
	}

	public void setReservedColumn2(String reservedColumn2) {
		this.reservedColumn2 = reservedColumn2 == null ? null : reservedColumn2.trim();
	}

	public String getReservedColumn3() {
		return reservedColumn3;
	}

	public void setReservedColumn3(String reservedColumn3) {
		this.reservedColumn3 = reservedColumn3 == null ? null : reservedColumn3.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode == null ? null : contractCode.trim();
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType == null ? null : contractType.trim();
	}

	public String getProfessionType() {
		return professionType;
	}

	public void setProfessionType(String professionType) {
		this.professionType = professionType == null ? null : professionType.trim();
	}

	public String getProfessionDescription() {
		return professionDescription;
	}

	public void setProfessionDescription(String professionDescription) {
		this.professionDescription = professionDescription == null ? null : professionDescription.trim();
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department == null ? null : department.trim();
	}

	public String getChushijingli() {
		return chushijingli;
	}

	public void setChushijingli(String chushijingli) {
		this.chushijingli = chushijingli == null ? null : chushijingli.trim();
	}

	public String getChushijingliLoginId() {
		return chushijingliLoginId;
	}

	public void setChushijingliLoginId(String chushijingliLoginId) {
		this.chushijingliLoginId = chushijingliLoginId == null ? null : chushijingliLoginId.trim();
	}

	public String getQuxiankuaiji() {
		return quxiankuaiji;
	}

	public void setQuxiankuaiji(String quxiankuaiji) {
		this.quxiankuaiji = quxiankuaiji == null ? null : quxiankuaiji.trim();
	}

	public String getQuxiankuaijiLoginId() {
		return quxiankuaijiLoginId;
	}

	public void setQuxiankuaijiLoginId(String quxiankuaijiLoginId) {
		this.quxiankuaijiLoginId = quxiankuaijiLoginId == null ? null : quxiankuaijiLoginId.trim();
	}

	public String getBumenfuzongjingli() {
		return bumenfuzongjingli;
	}

	public void setBumenfuzongjingli(String bumenfuzongjingli) {
		this.bumenfuzongjingli = bumenfuzongjingli == null ? null : bumenfuzongjingli.trim();
	}

	public String getBumenfuzongjingliLoginId() {
		return bumenfuzongjingliLoginId;
	}

	public void setBumenfuzongjingliLoginId(String bumenfuzongjingliLoginId) {
		this.bumenfuzongjingliLoginId = bumenfuzongjingliLoginId == null ? null : bumenfuzongjingliLoginId.trim();
	}

	public String getBumenzongjingli() {
		return bumenzongjingli;
	}

	public void setBumenzongjingli(String bumenzongjingli) {
		this.bumenzongjingli = bumenzongjingli == null ? null : bumenzongjingli.trim();
	}

	public String getBumenzongjingliLoginId() {
		return bumenzongjingliLoginId;
	}

	public void setBumenzongjingliLoginId(String bumenzongjingliLoginId) {
		this.bumenzongjingliLoginId = bumenzongjingliLoginId == null ? null : bumenzongjingliLoginId.trim();
	}

	public String getZhuanyezhineng() {
		return zhuanyezhineng;
	}

	public void setZhuanyezhineng(String zhuanyezhineng) {
		this.zhuanyezhineng = zhuanyezhineng == null ? null : zhuanyezhineng.trim();
	}

	public String getZhuanyezhinengLoginId() {
		return zhuanyezhinengLoginId;
	}

	public void setZhuanyezhinengLoginId(String zhuanyezhinengLoginId) {
		this.zhuanyezhinengLoginId = zhuanyezhinengLoginId == null ? null : zhuanyezhinengLoginId.trim();
	}

	public String getChengbenguanli() {
		return chengbenguanli;
	}

	public void setChengbenguanli(String chengbenguanli) {
		this.chengbenguanli = chengbenguanli == null ? null : chengbenguanli.trim();
	}

	public String getChengbenguanliLoginId() {
		return chengbenguanliLoginId;
	}

	public void setChengbenguanliLoginId(String chengbenguanliLoginId) {
		this.chengbenguanliLoginId = chengbenguanliLoginId == null ? null : chengbenguanliLoginId.trim();
	}

	public String getZhuanyexianzongjingli() {
		return zhuanyexianzongjingli;
	}

	public void setZhuanyexianzongjingli(String zhuanyexianzongjingli) {
		this.zhuanyexianzongjingli = zhuanyexianzongjingli == null ? null : zhuanyexianzongjingli.trim();
	}

	public String getZhuanyexianzongjingliLoginId() {
		return zhuanyexianzongjingliLoginId;
	}

	public void setZhuanyexianzongjingliLoginId(String zhuanyexianzongjingliLoginId) {
		this.zhuanyexianzongjingliLoginId = zhuanyexianzongjingliLoginId == null ? null : zhuanyexianzongjingliLoginId.trim();
	}

	public String getCaiwubushouquan() {
		return caiwubushouquan;
	}

	public void setCaiwubushouquan(String caiwubushouquan) {
		this.caiwubushouquan = caiwubushouquan == null ? null : caiwubushouquan.trim();
	}

	public String getCaiwubushouquanLoginId() {
		return caiwubushouquanLoginId;
	}

	public void setCaiwubushouquanLoginId(String caiwubushouquanLoginId) {
		this.caiwubushouquanLoginId = caiwubushouquanLoginId == null ? null : caiwubushouquanLoginId.trim();
	}

	public String getShengfenfuzongjingli() {
		return shengfenfuzongjingli;
	}

	public void setShengfenfuzongjingli(String shengfenfuzongjingli) {
		this.shengfenfuzongjingli = shengfenfuzongjingli == null ? null : shengfenfuzongjingli.trim();
	}

	public String getShengfenfuzongjingliLoginId() {
		return shengfenfuzongjingliLoginId;
	}

	public void setShengfenfuzongjingliLoginId(String shengfenfuzongjingliLoginId) {
		this.shengfenfuzongjingliLoginId = shengfenfuzongjingliLoginId == null ? null : shengfenfuzongjingliLoginId.trim();
	}

	public String getShengfenzongjingli() {
		return shengfenzongjingli;
	}

	public void setShengfenzongjingli(String shengfenzongjingli) {
		this.shengfenzongjingli = shengfenzongjingli == null ? null : shengfenzongjingli.trim();
	}

	public String getShengfenzongjingliLoginId() {
		return shengfenzongjingliLoginId;
	}

	public void setShengfenzongjingliLoginId(String shengfenzongjingliLoginId) {
		this.shengfenzongjingliLoginId = shengfenzongjingliLoginId == null ? null : shengfenzongjingliLoginId.trim();
	}

	public List<MaintainDocumentCostOther> getCostOtherChildren() {
		return costOtherChildren;
	}

	public void setCostOtherChildren(List<MaintainDocumentCostOther> costOtherChildren) {
		this.costOtherChildren = costOtherChildren;
	}

}