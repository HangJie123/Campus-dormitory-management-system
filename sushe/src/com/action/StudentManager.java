package com.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class StudentManager extends ActionSupport {
	
	private int pageSize = 10;//ÿҳ��ʾ��������¼
	private int currentPage;//��ǰҳ
	private int pageCount;//��ҳ��
	private int recordCount;//�ܼ�¼��
	private List<StudentBean> recordlist;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public List<StudentBean> getRecordlist() {
		return recordlist;
	}
	public void setRecordlist(List<StudentBean> recordlist) {
		this.recordlist = recordlist;
	}
	//������Action�����ڷ�װ�û��������������
	private List<StudentBean> list;
	public List<StudentBean> getList() {
		return list;
	}
	public void setList(List<StudentBean> list) {
		this.list = list;
	}
	private String SearchRow;
	private String SearchKey;
	private String State;
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getSearchRow() {
		return SearchRow;
	}
	public void setSearchRow(String searchRow) {
		SearchRow = searchRow;
	}
	public String getSearchKey() {
		return SearchKey;
	}
	public void setSearchKey(String searchKey) {
		SearchKey = searchKey;
	}
	//�����û������execute����
	public String execute() throws Exception {
		
		//������룬����ҳ�����
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//����session����
		HttpSession session = ServletActionContext.getRequest().getSession();
		//��֤�Ƿ�������¼
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('�����µ�¼��');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		
		//��ѯ����
		String strWhere="1=1";
		if(!(isInvalid(SearchKey)))
		{
			strWhere+=" and "+SearchRow+" like'"+"%"+SearchKey+"%"+"'";
		}
		if(!(isInvalid(State)))
		{
			
			strWhere+=" and Student_State='"+State+"'";
		}
		else
		{
			strWhere+=" and Student_State='��ס'";
		}
		
		if (currentPage == 0) {
			currentPage =1;
		}
		//��ѯ����
		list=new StudentDao().GetAllList(strWhere,"Student_Username");
		recordCount = list.size();
	    int tp = recordCount/pageSize;
		pageCount = recordCount % pageSize ==0 ? tp : tp+1;
		recordlist = new StudentDao().getList3(strWhere,"Student_Username",currentPage,pageSize);
		return SUCCESS;
		
	}
	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
	public static void main(String[] args) {
		System.out.println();
	}
	
}
