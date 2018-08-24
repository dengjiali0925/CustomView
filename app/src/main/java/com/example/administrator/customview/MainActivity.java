package com.example.administrator.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.customview.widget.CustomViewGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button customViewBtn;
    private Button customViewGroupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView()
    {
        customViewBtn = (Button) findViewById(R.id.custom_view_btn);
        customViewGroupBtn = (Button) findViewById(R.id.custom_view_group_btn);
    }

    private void setListener()
    {
        customViewBtn.setOnClickListener(this);
        customViewGroupBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.custom_view_btn:
            {
                startActivity(new Intent(this, CustomViewActivity.class));
                break;
            }

            case R.id.custom_view_group_btn:
            {
                startActivity(new Intent(this, CustomViewGroupActivity.class));
                break;
            }
            default:
                break;
        }
    }
}
