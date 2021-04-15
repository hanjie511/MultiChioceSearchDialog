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
        sortModel.setName("韩杰-1234");
        sortModel.setCode("韩杰");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("赵高-1234");
        sortModel.setCode("赵高");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("王五-1234");
        sortModel.setCode("王五");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("里斯-1234");
        sortModel.setCode("里斯");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("刘子-1234");
        sortModel.setCode("刘子");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("尔雅-1234");
        sortModel.setCode("尔雅");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("刘三-1234");
        sortModel.setCode("刘三");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("刘强-1234");
        sortModel.setCode("刘强");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("郝梦龄-1234");
        sortModel.setCode("郝梦龄");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("汉三军-1234");
        sortModel.setCode("汉三军-1234");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("唐国强-1234");
        sortModel.setCode("唐国强");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("唐三藏-1234");
        sortModel.setCode("唐三藏");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("王军-1234");
        sortModel.setCode("王军");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("网上三-1234");
        sortModel.setCode("网上三");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("简化军-1234");
        sortModel.setCode("简化军");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("成龙-1234");
        sortModel.setCode("成龙");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("陈三强-1234");
        sortModel.setCode("陈三强");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("丁龙-1234");
        sortModel.setCode("丁龙");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("赵子龙1-1234");
        sortModel.setCode("赵子龙1");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("赵子龙2-1234");
        sortModel.setCode("赵子龙2");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("赵子龙3-1234");
        sortModel.setCode("赵子龙3");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("赵子龙4-1234");
        sortModel.setCode("赵子龙4");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("赵子龙5-1234");
        sortModel.setCode("赵子龙5");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("赵子龙6-1234");
        sortModel.setCode("赵子龙6");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("赵子龙7-1234");
        sortModel.setCode("赵子龙7");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("赵子龙8-1234");
        sortModel.setCode("赵子龙8");
        list.add(sortModel);
        sortModel=new SortModel();
        sortModel.setName("赵子龙9-1234");
        sortModel.setCode("赵子龙9");
        list.add(sortModel);
        return  list;
    }
}
