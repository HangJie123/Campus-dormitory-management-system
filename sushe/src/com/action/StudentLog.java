package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class StudentLog extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int pageSize = 10;//ÿҳ��ʾ��������¼
	private int currentPage;//��ǰҳ
	private int pageCount;//��ҳ��
	private int recordCount;//�ܼ�¼��
	private List<LogBean> recordlist;
	
	
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
	public List<LogBean> getRecordlist() {
		return recordlist;
	}
	public void setRecordlist(List<LogBean> recordlist) {
		this.recordlist = recordlist;
	}

	private List<LogBean> list;
	public List<LogBean> getList() {
		return list;
	}
	public void setList(List<LogBean> list) {
		this.list = list;
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
		if (currentPage == 0) {
			currentPage =1;
		}
		
		//��ѯ����
		list=new LogDao().GetList("Student_ID="+session.getAttribute("id"),"Log_Date desc");
		recordCount = list.size();
	    int tp = recordCount/pageSize;
		pageCount = recordCount % pageSize ==0 ? tp : tp+1;
		recordlist = new LogDao().getList7("Student_ID="+session.getAttribute("id"),"Log_Date desc",currentPage,pageSize);
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
