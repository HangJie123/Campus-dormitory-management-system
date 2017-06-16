package com.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pager.PageBean;

import com.bean.*;
import com.dao.*;


public class DomitoryManager extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int pageSize = 10;//ÿҳ��ʾ��������¼
	private int currentPage;//��ǰҳ
	
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

	private int pageCount;//��ҳ��
	private int recordCount;//�ܼ�¼��
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	private List<DomitoryBean> list;
	public List<DomitoryBean> getList() {
		return list;
	}
	public void setList(List<DomitoryBean> list) {
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
	private List<BuildingBean> buildinglist;
	public List<BuildingBean> getBuildinglist() {
		return buildinglist;
	}
	public void setBuildinglist(List<BuildingBean> buildinglist) {
		this.buildinglist = buildinglist;
	}
	private String Domitory_BuildingID;
	public String getDomitory_BuildingID() {
		return Domitory_BuildingID;
	}
	public void setDomitory_BuildingID(String domitoryBuildingID) {
		Domitory_BuildingID = domitoryBuildingID;
	}
	private List<DomitoryBean> recordlist;
	public List<DomitoryBean> getRecordlist() {
		return recordlist;
	}
	public void setRecordlist(List<DomitoryBean> recordlist) {
		this.recordlist = recordlist;
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
		if(!(isInvalid(Domitory_BuildingID)))
		{
			strWhere+=" and Domitory_BuildingID='"+Domitory_BuildingID+"'";
		}
		if (currentPage == 0) {
			currentPage =1;
		}
		
		//��ѯ����¥��
		buildinglist=new BuildingDao().GetList("","Building_Name");
		//��ѯ��������
		list=new DomitoryDao().GetList(strWhere,"Domitory_BuildingID");
	    recordCount = list.size();
	    int tp = recordCount/pageSize;
		pageCount = recordCount % pageSize ==0 ? tp : tp+1;
		recordlist = new DomitoryDao().getList1(strWhere,"Domitory_BuildingID",currentPage,pageSize);
		
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
