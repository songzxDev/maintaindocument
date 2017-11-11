package cn.cgztb.maintaindocument.service;

import java.io.Serializable;
import java.util.Map;

import cn.cgztb.maintaindocument.domain.MaintainDocument;

public interface MaintainDocumentServiceI {
	public MaintainDocument getMaintainDocument(Map<String, Object> params) throws Exception;

	public Map<String, Object> getJudgeAmountBeans(String[] sheetNames) throws Exception;

	public Serializable editMaintainDocument(Map<String, Object> params) throws Exception;
}
