package com.cyrj.pharmacymessage.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class UserBean implements Parcelable{
    /**
     * 会话ID（token）
     */
    private String token;

    /**
     * 当前登陆时间
     */
    private String now;
    private String userId;
    private String userName;
    private String jgid;
    private List<UserQxkzListBean> qxkzList;
    private List<YkTypeBean>yklbList;

    public List<YkTypeBean> getYkTypeBeanList() {
        return yklbList;
    }

    public void setYkTypeBeanList(List<YkTypeBean> yklbList) {
        this.yklbList = yklbList;
    }

    /**
     * 条码前缀对象
     */
    public String prefixs;
    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }



    public List<UserQxkzListBean> getQxkzList() {
        return qxkzList;
    }

    public void setQxkzList(List<UserQxkzListBean> qxkzList) {
        this.qxkzList = qxkzList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.now);
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.jgid);
        dest.writeTypedList(this.qxkzList);
        dest.writeTypedList(this.yklbList);
        dest.writeString(this.prefixs);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.token = in.readString();
        this.now = in.readString();
        this.userId = in.readString();
        this.userName = in.readString();
        this.jgid = in.readString();
        this.qxkzList = in.createTypedArrayList(UserQxkzListBean.CREATOR);
        this.yklbList = in.createTypedArrayList(YkTypeBean.CREATOR);
        this.prefixs = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
