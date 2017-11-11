/****  修改语句 ****/
/*** 修改某个三级处室的常用环节审批人员信息  ***/
/**【成本类】**/
SELECT
    tab.CONTRACT_CODE,
    tab.DEPARTMENT,
    tab.CHUSHIJINGLI,
    tab.CHUSHIJINGLI_LOGIN_ID,
    tab.QUXIANKUAIJI,
    tab.QUXIANKUAIJI_LOGIN_ID,
    tab.BUMENFUZONGJINGLI,
    tab.BUMENFUZONGJINGLI_LOGIN_ID,
    tab.BUMENZONGJINGLI,
    tab.BUMENZONGJINGLI_LOGIN_ID,
    tab.*
FROM
    maintaindocument.maintain_document_cost AS tab
WHERE
    tab.CONTRACT_CODE !="36"
AND tab.ENABLE_STATUS="1"
AND tab.RESERVED_COLUMN3="上海市浦东新区分公司"
AND tab.DEPARTMENT IN("上海市浦东新区分公司销售支撑部")
-- AND tab.CONTRACT_CODE IN ("22");
/*【仅包含（财务、审计、法律）这三个部门】*/
SELECT
    tab.CONTRACT_CODE,
    tab.DEPARTMENT,
    tab.CHUSHIJINGLI,
    tab.CHUSHIJINGLI_LOGIN_ID,
    tab.QUXIANKUAIJI,
    tab.QUXIANKUAIJI_LOGIN_ID,
    tab.BUMENFUZONGJINGLI,
    tab.BUMENFUZONGJINGLI_LOGIN_ID,
    tab.BUMENZONGJINGLI,
    tab.BUMENZONGJINGLI_LOGIN_ID,
    tab.*
FROM
    maintaindocument.maintain_document_cost_other AS tab
WHERE
    tab.CONTRACT_CODE ="36"
AND tab.ENABLE_STATUS="1"
AND tab.RESERVED_COLUMN3="${二级部门名称}$"
AND tab.DEPARTMENT="${三级处室名称}$"
AND tab.CONTRACT_CODE IN (${合同类型编码}$);
/*
【########################################################################################################################################################################】
*/
/**【投资类】**/
/*【专业负责人、专业线总经理含金额区间判断】*/
-- --【专业负责人】■（$<=10W)
-- --【专业线总经理】■（10W<$<=20W)(刘贤松）、■（欧大春）(20W<$<=30W)
SELECT
    tab.CONTRACT_CODE,
    tab.DEPARTMENT,
    tab.CHUSHIJINGLI,
    tab.CHUSHIJINGLI_LOGIN_ID,
    tab.QUXIANKUAIJI,
    tab.QUXIANKUAIJI_LOGIN_ID,
    tab.BUMENFUZONGJINGLI,
    tab.BUMENFUZONGJINGLI_LOGIN_ID,
    tab.BUMENZONGJINGLI,
    tab.BUMENZONGJINGLI_LOGIN_ID,
    tab.*
FROM
    maintaindocument.maintain_document_investment AS tab
WHERE
    NOT EXISTS
    (
        SELECT
            1
        FROM
            MAINTAINDOCUMENT.MAINTAIN_DOCUMENT_INVESTMENT AS tab1
        WHERE
            tab1.CONTRACT_CODE IN("10",
                                  "11",
                                  "12",
                                  "13"))
AND tab.ENABLE_STATUS="1"
AND tab.RESERVED_COLUMN3="上海市浦东新区分公司"
AND tab.DEPARTMENT="${三级处室名称}$"
AND tab.CONTRACT_CODE IN (${合同类型编码}$);
/*【专业负责人、专业线总经理不含金额区间判断】*/
SELECT
    tab.CONTRACT_CODE,
    tab.DEPARTMENT,
    tab.CHUSHIJINGLI,
    tab.CHUSHIJINGLI_LOGIN_ID,
    tab.QUXIANKUAIJI,
    tab.QUXIANKUAIJI_LOGIN_ID,
    tab.BUMENFUZONGJINGLI,
    tab.BUMENFUZONGJINGLI_LOGIN_ID,
    tab.BUMENZONGJINGLI,
    tab.BUMENZONGJINGLI_LOGIN_ID,
    tab.*
FROM
    maintaindocument.maintain_document_investment_other AS tab
WHERE
    tab.CONTRACT_CODE IN("10",
                         "11",
                         "12",
                         "13")
AND tab.ENABLE_STATUS="1"
AND tab.RESERVED_COLUMN3="${二级部门名称}$"
AND tab.DEPARTMENT="${三级处室名称}$"
AND tab.CONTRACT_CODE IN (${合同类型编码}$);
/*
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
/*
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
/*
--------------------------------------------【数据备份语句】
------------------------------------------------
*/
/*
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
/*
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
/**【成本类】**/
SELECT
    *
FROM
    maintaindocument.maintain_document_cost AS tab
WHERE
    tab.CONTRACT_CODE !="36"
AND tab.ENABLE_STATUS="1"
AND tab.DEPARTMENT="${三级处室名称}$"
AND tab.RESERVED_COLUMN3="${二级部门名称}$"
AND tab.CONTRACT_CODE IN (${合同类型编码}$);
/*【仅包含（财务、审计、法律）这三个部门】*/
SELECT
    *
FROM
    maintaindocument.maintain_document_cost_other AS tab
WHERE
    tab.CONTRACT_CODE ="36"
AND tab.ENABLE_STATUS="1"
AND tab.DEPARTMENT="${三级处室名称}$"
AND tab.RESERVED_COLUMN3="${二级部门名称}$"
AND tab.CONTRACT_CODE IN (${合同类型编码}$);
/*
【########################################################################################################################################################################】
*/
/**【投资类】**/
/*【专业负责人、专业线总经理含金额区间判断】*/
-- --【专业负责人】■（$<=10W)
-- --【专业线总经理】■（10W<$<=20W)(刘贤松）、■（欧大春）(20W<$<=30W)
SELECT
    *
FROM
    maintaindocument.maintain_document_investment AS tab
WHERE
    NOT EXISTS
    (
        SELECT
            1
        FROM
            MAINTAINDOCUMENT.MAINTAIN_DOCUMENT_INVESTMENT AS tab1
        WHERE
            tab1.CONTRACT_CODE IN("10",
                                  "11",
                                  "12",
                                  "13"))
AND tab.ENABLE_STATUS="1"
AND tab.DEPARTMENT="${三级处室名称}$"
AND tab.RESERVED_COLUMN3="${二级部门名称}$"
AND tab.CONTRACT_CODE IN (${合同类型编码}$);
/*【专业负责人、专业线总经理不含金额区间判断】*/
SELECT
    *
FROM
    maintaindocument.maintain_document_investment_other AS tab
WHERE
    tab.CONTRACT_CODE IN("10",
                         "11",
                         "12",
                         "13")
AND tab.ENABLE_STATUS="1"
AND tab.DEPARTMENT="${三级处室名称}$"
AND tab.RESERVED_COLUMN3="${二级部门名称}$"
AND tab.CONTRACT_CODE IN (${合同类型编码}$);
/* 修改上海市省分总经理的审批领导 */
-- --成本类1
SELECT
    tab.SHENGFENZONGJINGLI,
    tab.SHENGFENZONGJINGLI_LOGIN_ID,
    tab.*
FROM
    maintaindocument.maintain_document_cost AS tab
WHERE
    tab.SHENGFENZONGJINGLI_LOGIN_ID='caiquangen';
-- --成本类2
SELECT
    tab.SHENGFENZONGJINGLI,
    tab.SHENGFENZONGJINGLI_LOGIN_ID,
    tab.*
FROM
    maintaindocument.maintain_document_cost_other AS tab
WHERE
    tab.SHENGFENZONGJINGLI_LOGIN_ID='caiquangen';
-- --支出类1
SELECT
    tab.SHENGFENZONGJINGLI,
    tab.SHENGFENZONGJINGLI_LOGIN_ID,
    tab.*
FROM
    maintaindocument.maintain_document_investment AS tab
WHERE
    tab.SHENGFENZONGJINGLI_LOGIN_ID='caiquangen';
-- --支出类2
SELECT
    tab.SHENGFENZONGJINGLI,
    tab.SHENGFENZONGJINGLI_LOGIN_ID,
    tab.*
FROM
    maintaindocument.maintain_document_investment_other AS tab
WHERE
    tab.SHENGFENZONGJINGLI_LOGIN_ID='caiquangen';
    
    
    