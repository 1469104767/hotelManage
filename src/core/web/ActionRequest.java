package core.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.catalina.connector.Request;
import org.apache.coyote.http11.upgrade.UpgradeInbound;
import org.apache.coyote.http11.upgrade.servlet31.HttpUpgradeHandler;

/**
 * 封装Request,使得每个线程都能拿到自己的请求
 */
public class ActionRequest implements HttpServletRequest{
	/** 存放每个线程的请求 */
	ThreadLocal<HttpServletRequest> localRequest = new ThreadLocal<>();
	private static ActionRequest instance = new ActionRequest();
	private ActionRequest(){
	}
	/** 获取唯一实例 */
	public static ActionRequest getInstance(){
		return instance;
	}
	/** 设置当前线程请求 */
	public void put(HttpServletRequest req){
		localRequest.set(req);
	}
	/** 清除当前线程请求 */
	public void clear(){
		localRequest.remove();
	}
	
	/** 获取真实的请求  */
	public HttpServletRequest getRealRequest(){
		return localRequest.get();
	}
	
	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		
		return localRequest.get().authenticate(response);
	}

	@Override
	public AsyncContext getAsyncContext() {
		
		return localRequest.get().getAsyncContext();
	}

	@Override
	public Object getAttribute(String name) {
		
		return localRequest.get().getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		
		return localRequest.get().getAttributeNames();
	}

	@Override
	public String getAuthType() {
		
		return localRequest.get().getAuthType();
	}

	@Override
	public String getCharacterEncoding() {
		
		return localRequest.get().getCharacterEncoding();
	}

	@Override
	public int getContentLength() {
		
		return localRequest.get().getContentLength();
	}

	@Override
	public String getContentType() {
		
		return localRequest.get().getContentType();
	}

	@Override
	public String getContextPath() {
		
		return localRequest.get().getContextPath();
	}

	@Override
	public Cookie[] getCookies() {
		
		return localRequest.get().getCookies();
	}

	@Override
	public long getDateHeader(String name) {
		
		return localRequest.get().getDateHeader(name);
	}

	@Override
	public DispatcherType getDispatcherType() {
		
		return localRequest.get().getDispatcherType();
	}

	@Override
	public String getHeader(String name) {
		
		return localRequest.get().getHeader(name);
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		
		return localRequest.get().getHeaderNames();
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		
		return localRequest.get().getHeaders(name);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		
		return localRequest.get().getInputStream();
	}

	@Override
	public int getIntHeader(String name) {
		
		return localRequest.get().getIntHeader(name);
	}

	@Override
	public String getLocalAddr() {
		
		return localRequest.get().getLocalAddr();
	}

	@Override
	public String getLocalName() {
		
		return localRequest.get().getLocalName();
	}

	@Override
	public int getLocalPort() {
		
		return localRequest.get().getLocalPort();
	}

	@Override
	public Locale getLocale() {
		
		return localRequest.get().getLocale();
	}

	@Override
	public Enumeration<Locale> getLocales() {
		
		return localRequest.get().getLocales();
	}

	@Override
	public String getMethod() {
		
		return localRequest.get().getMethod();
	}

	@Override
	public String getParameter(String name) {
		
		return localRequest.get().getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		
		return localRequest.get().getParameterMap();
	}

	@Override
	public Enumeration<String> getParameterNames() {
		
		return localRequest.get().getParameterNames();
	}

	@Override
	public String[] getParameterValues(String name) {
		
		return localRequest.get().getParameterValues(name);
	}

	@Override
	public Part getPart(String name) throws IllegalStateException, IOException, ServletException {
		
		return localRequest.get().getPart(name);
	}

	@Override
	public Collection<Part> getParts() throws IllegalStateException, IOException, ServletException {
		
		return localRequest.get().getParts();
	}

	@Override
	public String getPathInfo() {
		
		return localRequest.get().getPathInfo();
	}

	@Override
	public String getPathTranslated() {
		
		return localRequest.get().getPathTranslated();
	}

	@Override
	public String getProtocol() {
		
		return localRequest.get().getProtocol();
	}

	@Override
	public String getQueryString() {
		
		return localRequest.get().getQueryString();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		
		return localRequest.get().getReader();
	}

	@Override
	public String getRealPath(String path) {
		
		return localRequest.get().getRealPath(path);
	}

	@Override
	public String getRemoteAddr() {
		
		return localRequest.get().getRemoteAddr();
	}

	@Override
	public String getRemoteHost() {
		
		return localRequest.get().getRemoteHost();
	}

	@Override
	public int getRemotePort() {
		
		return localRequest.get().getRemotePort();
	}

	@Override
	public String getRemoteUser() {
		
		return localRequest.get().getRemoteUser();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		
		return localRequest.get().getRequestDispatcher(path);
	}

	@Override
	public String getRequestURI() {
		
		return localRequest.get().getRequestURI();
	}

	@Override
	public StringBuffer getRequestURL() {
		
		return localRequest.get().getRequestURL();
	}

	@Override
	public String getRequestedSessionId() {
		
		return localRequest.get().getRequestedSessionId();
	}

	@Override
	public String getScheme() {
		
		return localRequest.get().getScheme();
	}

	@Override
	public String getServerName() {
		
		return localRequest.get().getServerName();
	}

	@Override
	public int getServerPort() {
		
		return localRequest.get().getServerPort();
	}

	@Override
	public ServletContext getServletContext() {
		
		return localRequest.get().getServletContext();
	}

	@Override
	public String getServletPath() {
		
		return localRequest.get().getServletPath();
	}

	@Override
	public HttpSession getSession() {
		
		return localRequest.get().getSession();
	}

	@Override
	public HttpSession getSession(boolean create) {
		
		return localRequest.get().getSession(create);
	}

	@Override
	public Principal getUserPrincipal() {
		
		return localRequest.get().getUserPrincipal();
	}

	@Override
	public boolean isAsyncStarted() {
		
		return localRequest.get().isAsyncStarted();
	}

	@Override
	public boolean isAsyncSupported() {
		
		return localRequest.get().isAsyncSupported();
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		
		return localRequest.get().isRequestedSessionIdFromCookie();
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		
		return localRequest.get().isRequestedSessionIdFromURL();
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		
		return localRequest.get().isRequestedSessionIdFromUrl();
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		
		return localRequest.get().isRequestedSessionIdValid();
	}

	@Override
	public boolean isSecure() {
		
		return localRequest.get().isSecure();
	}

	@Override
	public boolean isUserInRole(String role) {
		
		return localRequest.get().isUserInRole(role);
	}

	@Override
	public void login(String username, String password) throws ServletException {
		
		localRequest.get().login(username, password);
	}

	@Override
	public void logout() throws ServletException {
		
		localRequest.get().logout();
	}

	@Override
	public void removeAttribute(String name) {
		
		localRequest.get().removeAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object o) {
		
		localRequest.get().setAttribute(name, o);
	}

	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		
		localRequest.get().setCharacterEncoding(env);
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		
		return localRequest.get().startAsync();
	}

	@Override
	public AsyncContext startAsync(ServletRequest request, ServletResponse response) throws IllegalStateException {
		
		return localRequest.get().startAsync(request, response);
	}

	
}
