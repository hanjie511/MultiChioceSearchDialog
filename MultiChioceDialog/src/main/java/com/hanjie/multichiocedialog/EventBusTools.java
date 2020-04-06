package com.hanjie.multichiocedialog;

public class EventBusTools {
    private String name;
    private boolean[] checked_item;
    private String code;

    public String getMsg() {
        return msg;
    }

    private String msg;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean[] getChecked_item() {
        return checked_item;
    }
    public EventBusTools(String msg,String name,String code){
        this.name=name;
        this.code=code;
        this.msg=msg;
    }
    public EventBusTools(String msg,String name,String code,boolean [] checked_item){
        this.name=name;
        this.code=code;
        this.msg=msg;
        this.checked_item=checked_item;
    }
}
