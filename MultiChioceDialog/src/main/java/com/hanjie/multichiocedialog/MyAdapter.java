package com.hanjie.multichiocedialog;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter <SortModel>{
    ArrayList<SortModel> list;
    Context context;
    private int resource;
    VH vh=null;

    public void setCheckedItem(boolean[] checkedItem) {
        this.checkedItem = checkedItem;
        for(int i=0;i<checkedItem.length;i++){
            if(checkedItem[i]==true){
                MyApplication.dataList.get(i).setSelected(true);
            }else{
                MyApplication.dataList.get(i).setSelected(false);
            }
        }
    }

    public boolean[] getCheckedItem() {
        return checkedItem;
    }

    private boolean[] checkedItem;
    public void setMultiChioce(boolean multiChioce) {
        isMultiChioce = multiChioce;
    }

    private boolean isMultiChioce=false;
    private class VH{
        TextView groupText;
        TextView username;
        LinearLayout linear;
        CheckBox checkBox;
    }

    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SortModel> list) {
        super(context, resource, list);
        this.context=context;
        this.resource=resource;
        this.list=list;

    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final SortModel sortModel=list.get(position);
        View view=null;
        if(convertView==null){
            vh=new VH();
            view=LayoutInflater.from(context).inflate(resource,null,false);
            vh.groupText=view.findViewById(R.id.group_text_item);
            vh.username=view.findViewById(R.id.username_item);
            vh.linear=view.findViewById(R.id.username_linear);
            vh.checkBox=view.findViewById(R.id.checkbox_list_item);
            view.setTag(vh);
        }else{
            view=convertView;
            vh=(VH)view.getTag();
        }
        if(sortModel.isTag()){
                vh.linear.setVisibility(View.GONE);
                vh.groupText.setVisibility(View.VISIBLE);
                vh.groupText.setText(sortModel.getName());
                vh.username.setTag(sortModel.getId());

        }else{
            vh.linear.setVisibility(View.VISIBLE);
            vh.groupText.setVisibility(View.GONE);
            vh.username.setText(sortModel.getName());
            vh.username.setTag(sortModel.getId());
            if(isMultiChioce&&checkedItem!=null){
                vh.checkBox.setTag(sortModel.getId());
                vh.checkBox.setVisibility(View.VISIBLE);
                vh.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String tag=buttonView.getTag().toString();
                        if(isChecked){
                            for(int i=0;i<MyApplication.dataList.size();i++) {
                                if(tag.equals(MyApplication.dataList.get(i).getId())) {
                                    MyApplication.checked_item[i]=true;
                                    MyApplication.dataList.get(i).setSelected(true);
                                }
                            }
                        }else{
                            for(int i=0;i<MyApplication.dataList.size();i++) {
                                if(tag.equals(MyApplication.dataList.get(i).getId())) {
                                    MyApplication.checked_item[i]=false;
                                    MyApplication.dataList.get(i).setSelected(false);
                                }
                            }
                        }
                    }
                });

//                if(MyApplication.checked_item[position]==true&&vh.checkBox.getTag().equals(MyApplication.dataList.get(position).getCode())) {
//                    vh.checkBox.setChecked(true);
//                }else{
//                    vh.checkBox.setChecked(false);
//                }
                if(sortModel.isSelected()) {
                    vh.checkBox.setChecked(true);
                }else{
                    vh.checkBox.setChecked(false);
                }
            }
       }

        return view;
}
    @Override
    public int getCount() {
        return list.size();
    }
    @Nullable
    @Override
    public SortModel getItem(int position) {
        return list.get(position);
    }
}
