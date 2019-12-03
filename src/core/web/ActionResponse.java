package core.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 封装Response,使得每个线程都能拿到自己的响应
 */
public class ActionResponse implements HttpServletResponse{

	ThreadLocal<HttpServletResponse> localResponse = new ThreadLocal<>();
	private static ActionResponse instance = new ActionResponse();
	private ActionResponse(){
	}
	/** 获取唯一实例 */
	public static ActionResponse getInstance(){
		return instance;
	}
	/** 设置当前线程响应 */
	public void put(HttpServletResponse req){
		localResponse.set(req);
	}
	/** 清除当前线程响应 */
	public void clear(){
		localResponse.remove();
	}
	/** 获取真实的响应 */
	public HttpServletResponse getRealResponse(){
		return localResponse.get();
	}
	
	@Override
	public void addCookie(Cookie cookie) {
		
		localResponse.get().addCookie(cookie);
	}

	@Override
	public void addDateHeader(String name, long date) {
		
		localResponse.get().addDateHeader(name, date);
	}

	@Override
	public void addHeader(String name, String value) {
		
		localResponse.get().addHeader(name, value);
	}

	@Override
	public void addIntHeader(String name, int value) {
		
		localResponse.get().addIntHeader(name, value);
	}


	@Override
	public boolean containsHeader(String name) {
		
		return localResponse.get().containsHeader(name);
	}

	@Override
	public String encodeRedirectURL(String url) {
		
		return localResponse.get().encodeRedirectURL(url);
	}

	@Override
	public String encodeRedirectUrl(String url) {
		
		return localResponse.get().encodeRedirectUrl(url);
	}

	@Override
	public String encodeURL(String url) {
		
		return localResponse.get().encodeURL(url);
	}

	@Override
	public String encodeUrl(String url) {
		
		return localResponse.get().encodeUrl(url);
	}

	@Override
	public void flushBuffer() throws IOException {
		
		localResponse.get().flushBuffer();
	}

	@Override
	public int getBufferSize() {
		
		return localResponse.get().getBufferSize();
	}

	@Override
	public String getCharacterEncoding() {
		
		return localResponse.get().getCharacterEncoding();
	}

	@Override
	public String getContentType() {
		
		return localResponse.get().getContentType();
	}

	@Override
	public String getHeader(String name) {
		
		return localResponse.get().getHeader(name);
	}

	@Override
	public Collection<String> getHeaderNames() {
		
		return localResponse.get().getHeaderNames();
	}

	@Override
	public Collection<String> getHeaders(String name) {
		
		return localResponse.get().getHeaders(name);
	}

	@Override
	public Locale getLocale() {
		
		return localResponse.get().getLocale();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		
		return localResponse.get().getOutputStream();
	}

	@Override
	public int getStatus() {
		
		return localResponse.get().getStatus();
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		
		return localResponse.get().getWriter();
	}

	@Override
	public boolean isCommitted() {
		
		return localResponse.get().isCommitted();
	}

	@Override
	public void reset() {
		
		localResponse.get().reset();
	}

	@Override
	public void resetBuffer() {
		
		localResponse.get().resetBuffer();
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		
		localResponse.get().sendError(sc, msg);
	}

	@Override
	public void sendError(int sc) throws IOException {
		
		localResponse.get().sendError(sc);
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		
		localResponse.get().sendRedirect(location);
	}

	@Override
	public void setBufferSize(int size) {
		
		localResponse.get().setBufferSize(size);
	}

	@Override
	public void setCharacterEncoding(String arg0) {
		
		localResponse.get().setCharacterEncoding(arg0);
	}

	@Override
	public void setContentLength(int len) {
		
		localResponse.get().setContentLength(len);
	}

	@Override
	public void setContentType(String type) {
		
		localResponse.get().setContentType(type);
	}

	@Override
	public void setDateHeader(String name, long date) {
		
		localResponse.get().setDateHeader(name, date);
	}

	@Override
	public void setHeader(String name, String value) {
		
		localResponse.get().setHeader(name, value);
	}

	@Override
	public void setIntHeader(String name, int value) {
		
		localResponse.get().setIntHeader(name, value);
	}

	@Override
	public void setLocale(Locale loc) {
		
		localResponse.get().setLocale(loc);
	}

	@Override
	public void setStatus(int sc, String sm) {
		
		localResponse.get().setStatus(sc, sm);
	}

	@Override
	public void setStatus(int sc) {
		
		localResponse.get().setStatus(sc);
	}
	
}
