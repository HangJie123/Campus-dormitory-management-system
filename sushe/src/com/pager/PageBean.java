package com.pager;

import java.util.List;
/**
 * ��ҳ��һҳ����Ϣ
 * @author Jack
 *
 */
public class PageBean {
	
	private int currentPage;//��ǰҳ
	private int pageSize;//ÿҳ��ʾ������
	
	private int pageCount;//��ҳ��
	private int recordCount;//�ܼ�¼��
	
	private int beginPageIndex;//ҳ���б�Ŀ�ʼ����
	private int endPageIndex;//ҳ���б�Ľ�������	
	
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	
}
