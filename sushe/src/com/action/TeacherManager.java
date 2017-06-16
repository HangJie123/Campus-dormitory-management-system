package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class TeacherManager extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int pageSize = 10;//ÿҳ��ʾ��������¼
	private int currentPage;//��ǰҳ
	private int pageCount;//��ҳ��
	private int recordCount;//�ܼ�¼��
	
	private List<TeacherBean> recordlist;
	
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
	public List<TeacherBean> getRecordlist() {
		return recordlist;
	}
	public void setRecordlist(List<TeacherBean> recordlist) {
		this.recordlist = recordlist;
	}
	private List<TeacherBean> list;
	public List<TeacherBean> getList() {
		return list;
	}
	public void setList(List<TeacherBean> list) {
		this.list = list;
	}
	private String SearchRow;
	private String SearchKey;
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
		session.setAttribute("type", "1");
		//��ѯ����
		String strWhere="1=1";
		if(!(isInvalid(SearchKey)))
		{
			strWhere+=" and "+SearchRow+" like'"+"%"+SearchKey+"%"+"'";
		}
		if (currentPage == 0) {
			currentPage =1;
		}
		//��ѯ����
		list=new TeacherDao().GetList(strWhere,"Teacher_Name");
		recordCount = list.size();
	    int tp = recordCount/pageSize;
		pageCount = recordCount % pageSize ==0 ? tp : tp+1;
		recordlist = new TeacherDao().GetList5(strWhere,"Teacher_Name",currentPage,pageSize);
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
