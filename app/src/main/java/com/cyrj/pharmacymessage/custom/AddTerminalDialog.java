package com.cyrj.pharmacymessage.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.help.UIUtil;


/**
 * 自定义的dialog
 */

public class AddTerminalDialog extends AlertDialog {
    private Button yes;
    private Button no;
    private TextView titleTv;
    private TextView messageTv;
    private String titleStr;
    private String messageStr;
    private String yesStr;

    /*  -------------------------------- 接口监听 -------------------------------------  */

    private YesOnclickListener yesOnclickListener;
    private EditText et_input_matou;
    private int inputType;

    public interface YesOnclickListener {
        void onYesClick(String inputCount);
    }

    public interface NoOnclickListener {
        void onNoClick();
    }

    public void setYesOnclickListener(String str, YesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    /*  ---------------------------------- 构造方法 -------------------------------------  */

    public AddTerminalDialog(Context context) {
        super(context, R.style.MyDialog);//风格主题
    }


    /*  ---------------------------------- onCreate-------------------------------------  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminal_dialog_layout);//自定义布局
        //按空白处不能取消动画
//        setCanceledOnTouchOutside(false);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                   String content= et_input_matou.getText().toString().trim();
                    if (!TextUtils.isEmpty(content)){
                        yesOnclickListener.onYesClick(content);
                        dismiss();
                    }else{
                        UIUtil.showToast("内容不能为空");
                    }
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dismiss();
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (titleStr != null) {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(titleStr);
        }
            if (!TextUtils.isEmpty(messageStr)) {
                messageTv.setText(messageStr);
        }
        no.setText("取消");
        //如果设置按钮的文字
        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (inputType!=0) {
            et_input_matou.setInputType(inputType);
        }
    }
    /**
     * 设置输入类型
     */
    public void setInputType(int type){
        inputType = type;

    }
    /**
     * 初始化界面控件
     */
    private void initView() {
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        titleTv = (TextView) findViewById(R.id.title);
        messageTv = (TextView) findViewById(R.id.message);
        et_input_matou = findViewById(R.id.et_input_matou);
    }

    /*  ---------------------------------- set方法 传值-------------------------------------  */
//为外界设置一些public 公开的方法，来向自定义的dialog传递值
    public void setTitle(String title) {
        titleStr = title;
    }

    public void setMessage(String name) {
        messageStr = name;

    }
}
