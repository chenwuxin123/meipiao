package com.meipiao.mongdb;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * mongodb实体示例
 * @author user
 * @create 2020-03-20 9:04
 */

@Document(collection = "Spider_SpiderTask")
public class SpiderTask implements Serializable {

    @org.springframework.data.annotation.Id
    public String Id;
    /// <summary>
    /// Hotels/Expdeia
    /// </summary>
    public String Channel;

    public String Name;
    public String Remark;
    //region 周期参数
    /// <summary>
    /// 周期,分钟
    /// 传递到消息队列需转化为毫秒
    /// </summary>
    public int Period;
    /// <summary>
    /// 任务起始时间
    /// 今天向后周期
    /// </summary>
    public int StartDate;
    /// <summary>
    /// 任务结束时间
    /// 今天向后周期
    /// </summary>
    public int EndDate;
    /// <summary>
    /// 任务起始时间,指定绝对日期,用于旺季特殊处理
    /// </summary>
    public String StartDateAbs;

    /// <summary>
    /// 任务结束时间,指定绝对日期,用于旺季特殊处理
    /// </summary>
    public String EndDateAbs;
    //endregion

    /// <summary>
    /// 搜索人数
    /// 分号分组,逗号分隔成人儿童
    /// 例:
    /// 2成人1儿童+2成人+3成人+3成人2儿童
    /// 2,1;2;3;3,2
    /// </summary>
    public String GuestNum;


    //region 发布参数

    /// <summary>
    /// 过期时间
    /// 分钟
    /// </summary>
    public int ReceiveExpiration;
    /// <summary>
    /// 优先级
    /// AMQP协议:0~9
    /// </summary>
    public int ReceivePriority;

    //endregion

    /// <summary>
    /// 标记最后读取时间,用于任务的发布
    /// </summary>
    public Date UpdateTime;

    public boolean IsStop;

    public List<String> HotelCodes = new ArrayList<String>();

    public boolean IsVerified() {
        if (Channel != "Hotels" && Channel != "Expedia")
            return false;
        return true;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getPeriod() {
        return Period;
    }

    public void setPeriod(int period) {
        Period = period;
    }

    public int getStartDate() {
        return StartDate;
    }

    public void setStartDate(int startDate) {
        StartDate = startDate;
    }

    public int getEndDate() {
        return EndDate;
    }

    public void setEndDate(int endDate) {
        EndDate = endDate;
    }

    public String getStartDateAbs() {
        return StartDateAbs;
    }

    public void setStartDateAbs(String startDateAbs) {
        StartDateAbs = startDateAbs;
    }

    public String getEndDateAbs() {
        return EndDateAbs;
    }

    public void setEndDateAbs(String endDateAbs) {
        EndDateAbs = endDateAbs;
    }

    public String getGuestNum() {
        return GuestNum;
    }

    public void setGuestNum(String guestNum) {
        GuestNum = guestNum;
    }

    public int getReceiveExpiration() {
        return ReceiveExpiration;
    }

    public void setReceiveExpiration(int receiveExpiration) {
        ReceiveExpiration = receiveExpiration;
    }

    public int getReceivePriority() {
        return ReceivePriority;
    }

    public void setReceivePriority(int receivePriority) {
        ReceivePriority = receivePriority;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }

    public boolean isStop() {
        return IsStop;
    }

    public void setStop(boolean stop) {
        IsStop = stop;
    }

    public List<String> getHotelCodes() {
        return HotelCodes;
    }

    public void setHotelCodes(List<String> hotelCodes) {
        HotelCodes = hotelCodes;
    }

    @Override
    public String toString() {
        return "SpiderTask{" +
                "Id='" + Id + '\'' +
                ", Channel='" + Channel + '\'' +
                ", Name='" + Name + '\'' +
                ", Remark='" + Remark + '\'' +
                ", Period=" + Period +
                ", StartDate=" + StartDate +
                ", EndDate=" + EndDate +
                ", StartDateAbs=" + StartDateAbs +
                ", EndDateAbs=" + EndDateAbs +
                ", GuestNum='" + GuestNum + '\'' +
                ", ReceiveExpiration=" + ReceiveExpiration +
                ", ReceivePriority=" + ReceivePriority +
                ", UpdateTime=" + UpdateTime +
                ", IsStop=" + IsStop +
                ", HotelCodes=" + HotelCodes +
                '}';
    }
}
