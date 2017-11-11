package cn.cgztb.maintaindocument.service;

import cn.cgztb.maintaindocument.domain.TemplateDataGrid;

public interface TemplateServiceI<T> {
	public T save(T model) throws Exception;

	public T edit(T model) throws Exception;

	public void remove(T model) throws Exception;

	public T getEntity(T model) throws Exception;

	public TemplateDataGrid<T> getEntities(T model) throws Exception;
}
