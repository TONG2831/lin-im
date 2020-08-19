package com.xyz.im.base.handler.returnn;

public class JsonResult {

    private Object data;

    private int status;

    private String desc;

    private String cost;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public static JsonResult builder(int status, String desc) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.status = status;
        jsonResult.desc = desc;
        return jsonResult;
    }
}
