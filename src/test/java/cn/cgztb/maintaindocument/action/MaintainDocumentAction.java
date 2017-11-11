package cn.cgztb.maintaindocument.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.cgztb.maintaindocument.domain.MaintainDocument;
import cn.cgztb.maintaindocument.service.MaintainDocumentServiceI;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring.xml", "classpath:/spring-mybatis.xml" })
public class MaintainDocumentAction extends TemplateAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MaintainDocumentAction.class);

	private MaintainDocumentServiceI maintainDocumentService;

	public MaintainDocumentServiceI getMaintainDocumentService() {
		return maintainDocumentService;
	}

	@Autowired
	public void setMaintainDocumentService(MaintainDocumentServiceI maintainDocumentService) {
		this.maintainDocumentService = maintainDocumentService;
	}

	/* EXECUTE METHOD */
	// 解决：java.lang.OutOfMemoryError: Java heap space
	// 选中被运行的类，点击菜单‘Run as ->Open Run Dialog...’， 选择(x)=Argument标签页下的vm arguments框里输入 -Xmx512m, 保存运行就ok了 //
	@Test
	public void generateDestFile() throws Exception {
		long beginTime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("costSheetNames", COST_SHEETNAMES);
		params.put("costOtherSheetNames", COST_OTHER_SHEETNAMES);
		params.put("investmentSheetNames", INVESTMENT_SHEETNAMES);
		params.put("investmentOtherSheetNames", INVESTMENT_OTHER_SHEETNAMES);
		MaintainDocument maintainDocument = maintainDocumentService.getMaintainDocument(params);

		Map<String, Object> beans = new HashMap<String, Object>();

		beans.put("maintainDocument", maintainDocument);

		Map<String, Object> judgeAmountBeans = maintainDocumentService.getJudgeAmountBeans(JUDGE_AMOUNT_SHEETNAMES);
		List<String> editAttributes = new ArrayList<String>();
		editAttributes.add("EDIT_STYLE_ONLY");// editAttributes.get(0);
		editAttributes.add("239");// editAttributes.get(1);
		judgeAmountBeans.put("editAttributes", editAttributes);
		logger.info(JSON.toJSON(judgeAmountBeans));
		String destFileName = UNIVERSAL_UTIL.appendTimestamp(DEST_FILE_NAME, ".xlsx");
		JXLS_UTIL.transformXLS(TEMPLATE_FILE_NAME, beans, destFileName, judgeAmountBeans);
		logger.info("程序已执行完毕！");
		long endTime = System.currentTimeMillis();
		logger.info("当前程序执行了的毫秒数为：【" + (endTime - beginTime) + "】毫秒！");
		long excuteMinutes = ((endTime - beginTime) % (1000 * 60 * 60)) / (1000 * 60);
		logger.info("当前程序执行了的分钟数为：【" + excuteMinutes + "】分钟！");
	}

}
