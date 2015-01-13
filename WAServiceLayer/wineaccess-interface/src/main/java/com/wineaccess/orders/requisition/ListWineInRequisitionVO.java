package com.wineaccess.orders.requisition;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "listWinesOfRequistion")
public class ListWineInRequisitionVO {

    private int count;

    private int offSet;

    private int limit;

    private int totalRecordCount;
    
    private String requistionId;
 
    private List<WineInRequistionResultModel> wineInReqDetail;
    
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getOffSet() {
        return offSet;
    }
    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
   
    public int getTotalRecordCount() {
        return totalRecordCount;
    }
    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }
    public List<WineInRequistionResultModel> getWineInReqDetail() {
        return wineInReqDetail;
    }
    public void setWineInReqDetail(List<WineInRequistionResultModel> wineInReqDetail) {
        this.wineInReqDetail = wineInReqDetail;
    }
    public String getRequistionId() {
        return requistionId;
    }
    public void setRequistionId(String requistionId) {
        this.requistionId = requistionId;
    }




}
