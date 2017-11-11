package cn.cgztb.maintaindocument.domain; 

import java.util.ArrayList;
import java.util.List;

public class TemplateDataGrid<T> {

	private List<T> templateList = new ArrayList<T>();

	public List<T> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<T> templateList) {
		this.templateList = templateList;
	}
	
	
}
