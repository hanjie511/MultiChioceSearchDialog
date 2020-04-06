package com.hanjie.multichiocedialog;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
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
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiChioceDialog extends AlertDialog{
    private Context context;
    private TextView title;
    private EditText search_edit;
    private ImageButton cancel_btn;
    private ImageButton search_btn;
    private ListView listView;
    private AlphabetView alphabetView;
    private Button sure_btn;
    private Button back_btn;
    private MyAdapter myAdapter;
    private ArrayList<SortModel> dataList;
    private ArrayList<SortModel> search_dataList=new ArrayList<>();
    private String titleStr;
    private boolean isMultiChioce=false;
    int i=0;
    private LinearLayout btn_linear;
    private boolean[] checkedItem;

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
        setContentView(R.layout.dialog_layout);
        EventBus.getDefault().register(this);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay(); //获取屏幕宽高
        Point point = new Point();
        display.getSize(point);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes(); //获取当前对话框的参数值
        layoutParams.width = (int) (point.x*0.8 ); //宽度设置为屏幕宽度的0.8
        layoutParams.height = (int) (point.y*0.8); //高度设置为屏幕高度的0.9
        window.setAttributes(layoutParams);
        initView();
        initEvent();
    }
    private void initView(){
        title=findViewById(R.id.dialog_title);
        if(titleStr!=null){
            title.setText(titleStr);
        }
        search_btn=findViewById(R.id.search_btn);
        search_edit=findViewById(R.id.search_edit);
        cancel_btn=findViewById(R.id.cancel_btn);
        listView=findViewById(R.id.listView);
        alphabetView=findViewById(R.id.alphabetView);
        back_btn=findViewById(R.id.back_btn);
        sure_btn=findViewById(R.id.sure_btn);
        btn_linear=findViewById(R.id.btn_linear);
        if(dataList!=null){
            myAdapter=new MyAdapter(context,R.layout.list_item,dataList);
            myAdapter.setMultiChioce(isMultiChioce);
            if(checkedItem!=null){
                myAdapter.setCheckedItem(checkedItem);
            }
            listView.setAdapter(myAdapter);
        }
        if(!isMultiChioce){
            btn_linear.setVisibility(View.GONE);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(EventBusTools event){
    }
    private void initEvent(){
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_dataList.clear();
                if(!"".equals(search_edit.getText())){
                    String content=search_edit.getText().toString();
                    Iterator<SortModel> iterator=dataList.iterator();
                    while(iterator.hasNext()){
                        SortModel sortModel=iterator.next();
                        if(sortModel.getName().contains(content)){
                            search_dataList.add(sortModel);
                        }
                    }
                    myAdapter=new MyAdapter(context,R.layout.list_item,search_dataList);
                    myAdapter.setMultiChioce(isMultiChioce);
                    if(checkedItem!=null){
                        myAdapter.setCheckedItem(checkedItem);
                    }
                    listView.setAdapter(myAdapter);
                }
                }

        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_edit.setText("");
                search_dataList.clear();
                myAdapter=new MyAdapter(context,R.layout.list_item,dataList);
                myAdapter.setMultiChioce(isMultiChioce);
                if(checkedItem!=null){
                    myAdapter.setCheckedItem(checkedItem);
                }
                listView.setAdapter(myAdapter);
            }
        });
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
                EventBus.getDefault().post(new EventBusTools("multi",name,code,MyApplication.checked_item));
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
                    EventBus.getDefault().post(new EventBusTools("single",sortModel.getName(),sortModel.getCode()));
                    dismiss();
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
