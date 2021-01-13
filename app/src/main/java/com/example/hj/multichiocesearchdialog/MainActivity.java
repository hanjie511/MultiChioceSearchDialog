package com.example.hj.multichiocesearchdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hanjie.multichiocedialog.MultiChioceDialog;
import com.hanjie.multichiocedialog.PinyinUtils;
import com.hanjie.multichiocedialog.SortModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView showText;
    private MultiChioceDialog multiChioceDialog;
    private boolean[] checkItem1;
    private boolean isFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showText=findViewById(R.id.showText);
    }
    public void multi(View v){
        multiChioceDialog=new MultiChioceDialog(MainActivity.this);
        ArrayList<SortModel> list=new PinyinUtils().getSortedListByAlpha(getList());
        if(isFirst){
            checkItem1=new boolean[list.size()];
            for(int i=0;i<checkItem1.length;i++){
                checkItem1[i]=false;
            }
            isFirst=false;
        }
        multiChioceDialog.setTitleStr("多选测试");
        multiChioceDialog.setDataList(list);
        multiChioceDialog.setCheckedItem(checkItem1);
        multiChioceDialog.setMultiChioce(true);
        multiChioceDialog.setOnPositiveClickListener(new MultiChioceDialog.OnPositiveClickListener() {
            @Override
            public void click(String name, String code, boolean[] checkItem) {
                showText.setText(name);
                showText.setTag(code);
                checkItem1=checkItem;
            }
        });
        multiChioceDialog.show();
    }
    public void single(View v){
        multiChioceDialog=new MultiChioceDialog(MainActivity.this);
        ArrayList<SortModel> list=new PinyinUtils().getSortedListByAlpha(getList());
        multiChioceDialog.setTitleStr("单选测试");
        multiChioceDialog.setDataList(list);
        multiChioceDialog.setMultiChioce(false);
        multiChioceDialog.setOnPositiveClickListener(new MultiChioceDialog.OnPositiveClickListener() {
            @Override
            public void click(String name, String code, boolean[] checkItem) {
                showText.setText(name);
                showText.setTag(code);
            }
        });
        multiChioceDialog.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private ArrayList<SortModel> getList(){
        SortModel sortModel=null;
        ArrayList<SortModel> list=new ArrayList<SortModel>();
        sortModel=new SortModel();
        sortModel.setName("h韩杰");
        sortModel.setCode("h韩杰");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("z赵高");
        sortModel.setCode("z赵高");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("w王五");
        sortModel.setCode("w王五");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("l里斯");
        sortModel.setCode("l里斯");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("l刘子");
        sortModel.setCode("l刘子");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("e尔雅");
        sortModel.setCode("e尔雅");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("l刘三");
        sortModel.setCode("l刘三");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("l刘强");
        sortModel.setCode("l刘强");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("h郝梦龄");
        sortModel.setCode("h郝梦龄");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("h汉三军");
        sortModel.setCode("h汉三军");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("唐国强");
        sortModel.setCode("唐国强");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("唐三藏");
        sortModel.setCode("唐三藏");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("w王军");
        sortModel.setCode("w王军");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("w网上三");
        sortModel.setCode("w网上三");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("j简化军");
        sortModel.setCode("j简化军");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("c成龙");
        sortModel.setCode("c成龙");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("c陈三强");
        sortModel.setCode("c陈三强");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("d丁龙");
        sortModel.setCode("d丁龙");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("z赵子龙1");
        sortModel.setCode("z赵子龙1");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("z赵子龙2");
        sortModel.setCode("z赵子龙2");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("z赵子龙3");
        sortModel.setCode("z赵子龙3");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("z赵子龙4");
        sortModel.setCode("z赵子龙4");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("z赵子龙5");
        sortModel.setCode("z赵子龙5");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("z赵子龙6");
        sortModel.setCode("z赵子龙6");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("z赵子龙7");
        sortModel.setCode("z赵子龙7");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("z赵子龙8");
        sortModel.setCode("z赵子龙8");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("z赵子龙9");
        sortModel.setCode("z赵子龙9");
        list.add(sortModel);
        return  list;
    }
}
