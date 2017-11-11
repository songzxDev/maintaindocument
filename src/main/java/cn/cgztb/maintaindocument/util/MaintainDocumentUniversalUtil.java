package cn.cgztb.maintaindocument.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MaintainDocumentUniversalUtil {

	private MaintainDocumentUniversalUtil() {
	}

	private static MaintainDocumentUniversalUtil maintainDocumentUniversalUtil = null;

	public synchronized static MaintainDocumentUniversalUtil getInstance() {
		if (maintainDocumentUniversalUtil == null) {
			maintainDocumentUniversalUtil = new MaintainDocumentUniversalUtil();
		}
		return maintainDocumentUniversalUtil;
	}

	public String appendTimestamp(String source, String reserved) {
		StringBuilder stb = new StringBuilder();
		stb.append(source);
		stb.append("_");
		stb.append(getTimestamp());
		stb.append(reserved);
		return stb.toString();
	}

	private String getTimestamp() {
		String value = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			value = sdf.format(timestamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public boolean stringIsEmpty(String target) {
		return target == null || target.isEmpty();

	}
}
