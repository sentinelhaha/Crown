package com.crown.login;

import java.io.Serializable;

/**
 * Created by zjj on 2018/11/25.
 */
//把服务器返回的json转成serverResult
public class ServerResult  implements Serializable{
    private  int status;
    private  String msg;
    private  Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
