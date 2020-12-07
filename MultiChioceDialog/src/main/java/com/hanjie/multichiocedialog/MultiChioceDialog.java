package com.hanjie.multichiocedialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

public class MultiChioceDialog extends AlertDialog{
    private Context context;
    private TextView title;
    private EditText search_edit;
    private ListView listView;
    private AlphabetView alphabetView;
    private Button sure_btn;
    private Button back_btn;
    private MyAdapter myAdapter;
    private ArrayList<SortModel> dataList;
    private ArrayList<SortModel> search_dataList=new ArrayList<>();
    private String titleStr;
    private boolean isMultiChioce=false;
    private String titleColor;
    int i=0;
    private LinearLayout btn_linear;
    private boolean[] checkedItem;
    private OnPositiveClickListener onPositiveClickListener;
    private String titleTextColor;
    public void setCheckedItem(boolean[] checkedItem) {
        this.checkedItem = checkedItem;
        MyApplication.checked_item=checkedItem;
    }
    /*
       第三步：设置对话框是多选还是单选
        */
    public void setMultiChioce(boolean multiChioce) {
        isMultiChioce = multiChioce;
    }
    /*
    第一步：设置对话框的标题
     */
    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }
    public void setTitleBgColor(String color){
        this.titleColor=color;
    }
    public void setTitleTextColor(String color){
        this.titleTextColor=color;
    }
    /*
        第二步：设置对话框的数据源
         */
    public void setDataList(ArrayList<SortModel> dataList) {
        this.dataList = dataList;
        MyApplication.dataList=this.dataList;
    }
    public MultiChioceDialog(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hanjie_dialog_layout);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay(); //获取屏幕宽高
        Point point = new Point();
        display.getSize(point);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes(); //获取当前对话框的参数值
        layoutParams.width = (int) (point.x*0.9 ); //宽度设置为屏幕宽度的0.8
        layoutParams.height = (int) (point.y*0.9); //高度设置为屏幕高度的0.9
        window.setAttributes(layoutParams);
        initView();
        initEvent();
    }
    private void initView(){
        title=findViewById(R.id.dialog_title);
        if(titleColor!=null){
            title.setBackgroundColor(Color.parseColor(titleColor));
        }
        if(titleStr!=null){
            title.setText(titleStr);
        }
        if(titleTextColor!=null){
            title.setTextColor(Color.parseColor(titleTextColor));
        }
        search_edit=findViewById(R.id.search_edit);
        listView=findViewById(R.id.listView);
        alphabetView=findViewById(R.id.alphabetView);
        back_btn=findViewById(R.id.back_btn);
        sure_btn=findViewById(R.id.sure_btn);
        btn_linear=findViewById(R.id.btn_linear);
        if(dataList!=null){
            myAdapter=new MyAdapter(context,R.layout.hanjie_list_item,dataList);
            myAdapter.setMultiChioce(isMultiChioce);
            if(checkedItem!=null){
                myAdapter.setCheckedItem(checkedItem);
            }
            listView.setAdapter(myAdapter);
        }
        if(!isMultiChioce){
            btn_linear.setVisibility(View.GONE);
        }
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!"".equals(s.toString())){
                    String content=s.toString();
                    Iterator<SortModel> iterator=dataList.iterator();
                    while(iterator.hasNext()){
                        SortModel sortModel=iterator.next();
                        if(sortModel.getName().contains(content)){
                            search_dataList.add(sortModel);
                        }
                    }
                    myAdapter=new MyAdapter(context,R.layout.hanjie_list_item,search_dataList);
                    myAdapter.setMultiChioce(isMultiChioce);
                    if(checkedItem!=null){
                        myAdapter.setCheckedItem(checkedItem);
                    }
                    listView.setAdapter(myAdapter);
                }else{
                search_dataList.clear();
                myAdapter=new MyAdapter(context,R.layout.hanjie_list_item,dataList);
                myAdapter.setMultiChioce(isMultiChioce);
                if(checkedItem!=null){
                    myAdapter.setCheckedItem(checkedItem);
                }
                listView.setAdapter(myAdapter);
                }
            }
        });
    }
    private void initEvent(){
        alphabetView.setOnClickListener(new AlphabetView.OnClickListener() {
            @Override
            public void onClick(String alpha) {
                Iterator<SortModel> iterator=dataList.iterator();
                SortModel sortModel=null;
                i=0;
                while(iterator.hasNext()){
                    sortModel=iterator.next();
                    if(sortModel.getName().equals(alpha)){
                        break;
                    }
                    i++;
                }
                listView.setSelection(i);
            }
        });
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name="";
                String code="";
                for(int i=0;i<MyApplication.checked_item.length;i++){
                    if(MyApplication.checked_item[i]==true){
                        name=name+dataList.get(i).getName()+",";
                        code=code+dataList.get(i).getCode()+",";

                    }
                }
                onPositiveClickListener.click(name,code,MyApplication.checked_item);
                dismiss();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!isMultiChioce){
                    SortModel sortModel=(SortModel) parent.getAdapter().getItem(position);
                    if(!sortModel.isTag()){
                        onPositiveClickListener.click(sortModel.getName(),sortModel.getCode(),null);
                        dismiss();
                    }
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    public void setOnPositiveClickListener(OnPositiveClickListener l){
        this.onPositiveClickListener=l;
    }
    public interface OnPositiveClickListener{
        void click(String name,String code,boolean [] checkItem);
    }
}
