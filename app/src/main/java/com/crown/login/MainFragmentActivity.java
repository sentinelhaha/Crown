package com.crown.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

/**
 * Created by zjj on 2018/11/26.
 */

public class MainFragmentActivity extends FragmentActivity {
   IndexFragment indexFragment;
    //indexfragment,messgeFragment,mefragment
    Fragment[] fragments=new Fragment[3];

    //indexBtn,messageBtn,MeBtn
    Button[] buttons=new Button[3];

    //现在显示的是第几个fragment
    int currentFragmentIndex=0;

    //单击的按钮是第几个
    int clickBtnIndex=0;
    //参数只有一个
    @Override
    protected void onCreate
            (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);
        //在fragment_container中显示indexFragment

        //开始事务
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        //动作1：加载indexFragment到fragment_container中
        indexFragment=new IndexFragment();
        transaction.add(R.id.fragment_container,indexFragment);
        //动作2：显示indexfragment
        transaction.show(indexFragment);
       //add()和show()有一个失败了，出异常，不执行commit()
        //提交事务
        transaction.commit();

        //创建两个fragment
        MessageFragment messageFragment=new MessageFragment();
MeFragment meFragment=new MeFragment();
        //把三个fragment放到fragments
        fragments[0]=indexFragment;
        fragments[1]=messageFragment;
        fragments[2]=meFragment;

        //找到三个按钮
        buttons[0]= (Button) findViewById(R.id.btn_main_fragment_store);
        buttons[1]= (Button) findViewById(R.id.btn_main_fragment_message);
        buttons[2]= (Button) findViewById(R.id.btn_main_fragment_me);

        //设置事件监听
        MyBtnListener myBtnListener=new MyBtnListener();
        for(Button button:buttons)
        {
            button.setOnClickListener(myBtnListener);
        }

        //设置第一个按钮的状态为选中
        buttons[currentFragmentIndex].setSelected(true);

    }
    //按钮事件监听的类
    class MyBtnListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            //判断单击是第几个按钮 clickBtnIndex=1 fragments[1]
           switch (v.getId())
           {
               case R.id.btn_main_fragment_store:
                   clickBtnIndex=0;
                   break;
               case R.id.btn_main_fragment_message:
                   clickBtnIndex=1;
                   break;
               case R.id.btn_main_fragment_me:
                   clickBtnIndex=2;
                   break;
           }
            //判断单击的是不是当前
            if (currentFragmentIndex!=clickBtnIndex) {
                //开始事务
                FragmentManager manager=getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                //动作1：隐藏以前的fragment
                Fragment hideFragment=fragments[currentFragmentIndex];
                transaction.hide(hideFragment);
                //动作2:添加新的fragment
                Fragment showFragment=fragments[clickBtnIndex];
                if (showFragment.isAdded()==false)
                {
                    transaction.add(R.id.fragment_container,showFragment);
                }
                //动作3:显示新的fragment
                transaction.show(showFragment);
                //提交
                transaction.commit();
                buttons[currentFragmentIndex].setSelected(false);
                buttons[clickBtnIndex].setSelected(true);
                currentFragmentIndex=clickBtnIndex;
            }
        }
    }
}
