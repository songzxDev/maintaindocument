package cn.cgztb.maintaindocument.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class MaintainDocumentJXLSUtil {

	private static MaintainDocumentPOIUtil poiUtil = MaintainDocumentPOIUtil.getInstance();

	private MaintainDocumentJXLSUtil() {
	}

	private static MaintainDocumentJXLSUtil maintainDocumentJXLSUtil = null;

	public synchronized static MaintainDocumentJXLSUtil getInstance() {
		if (maintainDocumentJXLSUtil == null) {
			maintainDocumentJXLSUtil = new MaintainDocumentJXLSUtil();
		}
		return maintainDocumentJXLSUtil;
	}

	/**
	 * 
	 * @Title: transformXLS
	 * @Description: 使用【net.sf.jxls】框架依据配置好的excel模版生成所需的excel文件
	 * @param templateFileName
	 *          模版所在的绝对路径
	 * @param beans
	 *          写到生成文件的内容
	 * @param destFileName
	 *          生成文件所在的绝对路径
	 * @param reservedParams
	 *          预留的参数
	 * @return
	 * @return: String 操作是否成功，成功：SUCCESS
	 * @author 宋桢熙
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Serializable transformXLS(String templateFileName, Map<String, Object> beans, String destFileName, Map<String, Object> reservedParams) throws Exception {
		StringBuilder status = new StringBuilder();
		try {
			XLSTransformer transformer = new XLSTransformer();
			InputStream is = new BufferedInputStream(new FileInputStream(templateFileName));
			OutputStream os = new BufferedOutputStream(new FileOutputStream(destFileName));
			if (!isParameterEmpty(templateFileName) && !isParameterEmpty(destFileName)) {
				org.apache.poi.ss.usermodel.Workbook workbook = transformer.transformXLS(is, beans);
				if (reservedParams != null && !reservedParams.isEmpty()) {
					List<String> editAttributes = (List<String>) reservedParams.get("editAttributes");
					if (editAttributes != null && !editAttributes.isEmpty()) {
						int fromRowIndex = Integer.parseInt(editAttributes.get(1));
						Map<String, Object> conditions = poiUtil.getLastExcelCellsByConditions(workbook, reservedParams, fromRowIndex);
						if (conditions != null && !conditions.isEmpty()) {
							poiUtil.eidtWorkbook(workbook, conditions, editAttributes.get(0));
						}
					}
				}
				workbook.write(os);
				status.append("SUCCESS");
			} else {
				status.append("FAILURE:");
				status.append("模版文件路径和依据模版生成的文件路径不能为空！");
				status.append(";\r\n");
			}
			is.close();
			os.flush();
			os.close();
		} catch (ParsePropertyException e) {
			status.append("FAILURE:");
			status.append(e.getMessage());
			status.append(";\r\n");
		} catch (InvalidFormatException e) {
			status.append("FAILURE:");
			status.append(e.getMessage());
			status.append(";\r\n");
		} catch (IOException e) {
			status.append("FAILURE:");
			status.append(e.getMessage());
			status.append(";");
		}
		return status.toString();
	}

	/**
	 * 判断字符串是否为空(判断 null、""、"null"、"undefined"四种情况)
	 * 
	 * @param str
	 * @return 为空返回TRUE，不为空返回FALSE
	 * @return 列出方法的返回值列表（为空返回TRUE，不为空返回FALSE）
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	private boolean isParameterEmpty(String str) {
		return str == null || "".equals(str.trim()) || "null".equals(str.trim()) || "undefined".equals(str.trim());
	}

}
