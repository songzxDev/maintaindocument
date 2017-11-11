package cn.cgztb.maintaindocument.action;

import cn.cgztb.maintaindocument.util.MaintainDocumentJXLSUtil;
import cn.cgztb.maintaindocument.util.MaintainDocumentPOIUtil;
import cn.cgztb.maintaindocument.util.MaintainDocumentUniversalUtil;

public class TemplateAction {

	public static final String COST_SHEETNAMES[] = { "22", "23", "24", "25_1", "25_2", "26", "27_1", "27_2", "29", "30_1", "30_2", "31_1", "31_2", "32", "33", "34", "35", "37_1", "37_2", "38", "39" };

	public static final String COST_OTHER_SHEETNAMES[] = { "36" };

	public static final String INVESTMENT_SHEETNAMES[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "14", "15", "16", "17", "18", "19", "20", "21" };

	public static final String INVESTMENT_OTHER_SHEETNAMES[] = { "10", "11", "12", "13" };

	public static final String JUDGE_AMOUNT_SHEETNAMES[] = { "cost_22", "cost_23", "cost_25", "cost_27", "cost_29", "cost_30", "cost_31", "investment_1", "investment_2", "investment_5" };

	public static final String TEMPLATE_FILE_NAME = "E:\\MyHandoverDocumentSH_TEMP\\TEMPLATE_FILE\\交接文档（合同-专业-人员）_template.xlsx";

	public static final String DEST_FILE_NAME = "E:\\MyHandoverDocumentSH_TEMP\\DEST_FILE\\交接文档（合同-专业-人员）_dest";

	public static MaintainDocumentJXLSUtil JXLS_UTIL = MaintainDocumentJXLSUtil.getInstance();

	public static MaintainDocumentPOIUtil POI_UTIL = MaintainDocumentPOIUtil.getInstance();

	public static MaintainDocumentUniversalUtil UNIVERSAL_UTIL = MaintainDocumentUniversalUtil.getInstance();

}
