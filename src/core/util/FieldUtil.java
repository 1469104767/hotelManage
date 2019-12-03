package core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段转换工具类
 */
public class FieldUtil {
	private static Pattern linePattern = Pattern.compile("_(\\w)");
	private static Pattern humpPattern = Pattern.compile("[A-Z]");

	/** 下划线转驼峰 */
	public static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/** 驼峰转下划线  */
	public static String humpToLine(String str) {
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	/** 数据库字段名称转为set方法 */
	public static String colNameToSetMethod(String colName) {
		String str = colName.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		sb.replace(0, 1, sb.substring(0,1).toUpperCase());
		sb.insert(0, "set");
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(colNameToSetMethod("user_name"));
	}
}