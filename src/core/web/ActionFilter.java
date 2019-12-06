package core.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import core.factory.BeanFactory;
import core.util.FileScanner;
import core.util.JsonUtils;

public class ActionFilter implements Filter {
	
	private Map<String,ActionMethod> urlMethodMap = new HashMap<>();
	private static final String METHOD_ACTION = "Action";
	private static final String URL_ACTION = ".action";
	private ActionRequest actionRequest=  ActionRequest.getInstance();
	private ActionResponse actionResponse=  ActionResponse.getInstance();
	// 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = ((HttpServletRequest)request);
		HttpServletResponse resp = ((HttpServletResponse)response);
		req.setCharacterEncoding("UTF-8");
		//把请求和响应放进actionRequest actionResponse
		actionRequest.put(req);
		actionResponse.put(resp);
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String relativeURI = uri.substring(contextPath.length());
		if(uri.toString().endsWith(URL_ACTION)){
		    ActionMethod method = urlMethodMap.get(relativeURI);
		    Map<String, Object> paramterMap = buildParamterMap(req);
			try {
				Object result = method.invoke(paramterMap);
				//返回页面和json
				if(result instanceof Page){
					req.getRequestDispatcher(((Page)result).getPath()).forward(request, response);
				}else{
					resp.setContentType("text/json;charset=UTF-8");
					PrintWriter writer = resp.getWriter();
					writer.append(JsonUtils.convertObjectToJSON(result));
					writer.close();
				}
			}catch (Exception e) {
				if(method==null){
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
				else{
					resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					resp.setContentType("text/json;charset=UTF-8");
					PrintWriter writer = resp.getWriter();
					Message message = Message.error();
					message.setData(e.getMessage());
					writer.append(JsonUtils.convertObjectToJSON(message));
					e.printStackTrace();
				}
			}finally {
				actionRequest.clear();
				actionResponse.clear();
			}
		}
	}
	
	/** 处理参数  */
	private Map<String, Object> buildParamterMap(HttpServletRequest req) {
		Map<String, Object> parameters = new LinkedHashMap<>();
		Map<String, String[]> parameterMap = req.getParameterMap();
		//将参数变成String再返回
		for (Entry<String, String[]> parameter : parameterMap.entrySet()) {
			if(parameter.getValue()!=null){
				parameters.put(parameter.getKey(), parameter.getValue()[0]);
			}
		}
		//处理文件上传
		if(ServletFileUpload.isMultipartContent(req)){
			   // 配置上传参数
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		    factory.setSizeThreshold(MEMORY_THRESHOLD);
		    // 设置临时存储目录
		    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    // 设置最大文件上传值
		    upload.setFileSizeMax(MAX_FILE_SIZE);
		    // 设置最大请求值 (包含文件和表单数据)
		    upload.setSizeMax(MAX_REQUEST_SIZE);
		    // 中文处理
		    upload.setHeaderEncoding("UTF-8"); 
		    try {
				Map<String, List<FileItem>> FileItemMap = upload.parseParameterMap(req);
				for (Entry<String, List<FileItem>> parameter : FileItemMap.entrySet()) {
					if(parameter.getValue()!=null&&parameter.getValue().size()!=0){
						parameters.put(parameter.getKey(), parameter.getValue().get(0));
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		return parameters;
	}

	/**
	 * 初始化加载url 
	 * 在此处初始化所有bean
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		FileScanner scanner = new FileScanner();
		Set<Class<?>> actions = scanner.search("", METHOD_ACTION);
		for (Class<?> clazz : actions) {
			Object bean = null;
			try {
				bean = clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			String path = "/" + clazz.getName().replace(".","/").replace(METHOD_ACTION, "").replace("/"+METHOD_ACTION.toLowerCase(), "");
			//把entity第一个首字母小写
			char[] pathCharArr = path.toCharArray();
			int firstCharIndex = path.lastIndexOf("/")+1;
			pathCharArr[firstCharIndex] = Character.toLowerCase(pathCharArr[firstCharIndex]);
			path = new String(pathCharArr);
			Method[] methods = clazz.getMethods();
			//把链接对应方法存在map
			for (Method method : methods) {
				//跳过Object的方法
				if(method.getDeclaringClass()==Object.class)
					continue;
				StringBuilder url = new StringBuilder(path);
				urlMethodMap.put(url.append("/").append(method.getName()).append(URL_ACTION).toString(),
						new ActionMethod(method, bean));
			}
		}
	}

}
