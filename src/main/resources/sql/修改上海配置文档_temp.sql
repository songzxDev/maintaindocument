-- UPDATE maintain_document_cost
-- SET 
-- DEPARTMENT = '上海市分公司人力资源部员工绩效模块'
-- WHERE
-- DEPARTMENT IN ('上海市分公司人力资源部员工绩效管理模块');
-- 
-- UPDATE maintain_document_cost_other
-- SET 
-- DEPARTMENT = '上海市分公司人力资源部员工绩效模块'
-- WHERE
-- DEPARTMENT IN ('上海市分公司人力资源部员工绩效管理模块');
-- 
-- UPDATE maintain_document_investment_other
-- SET 
-- DEPARTMENT = '上海市分公司人力资源部员工绩效模块'
-- WHERE
-- DEPARTMENT IN ('上海市分公司人力资源部员工绩效管理模块');
-- 
-- UPDATE maintain_document_investment
-- SET 
-- DEPARTMENT = '上海市分公司人力资源部员工绩效模块'
-- WHERE
-- DEPARTMENT IN ('上海市分公司人力资源部员工绩效管理模块');


SELECT * FROM MAINTAIN_DOCUMENT_COST WHERE DEPARTMENT IN ('上海市闵行区分公司一号商企客户中心') ORDER BY DEPARTMENT;

SELECT * FROM MAINTAIN_DOCUMENT_COST_OTHER WHERE DEPARTMENT IN ('上海市闵行区分公司一号商企客户中心') ORDER BY DEPARTMENT;

SELECT * FROM MAINTAIN_DOCUMENT_INVESTMENT WHERE DEPARTMENT IN ('上海市闵行区分公司一号商企客户中心') ORDER BY DEPARTMENT;

SELECT * FROM MAINTAIN_DOCUMENT_INVESTMENT_OTHER WHERE DEPARTMENT IN ('上海市闵行区分公司一号商企客户中心') ORDER BY DEPARTMENT;


