package demo.checkdemo.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import demo.checkdemo.R;


public class PassWordDialog extends Dialog implements
        android.view.View.OnClickListener {

    private static Context tmpContext;
    private static PassWordDialog instance;
    private static Button btnOne, btnTwo, btnThree, btnOk, btnZero, btnCancle,
            btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    private TextView textPassword;
    private ImageView ivClear;

    public PassWordDialog(Context context) {
        super(context);
        setCustomDialog();
    }

    public String getInputVal() {
        return textPassword.getText().toString();
    }

    //清空输入文本框
    public String cleanText() {
        textPassword.setText("");
        return textPassword.getText().toString();
    }

    public static PassWordDialog getInstance(Context context) {

        if (instance == null || !tmpContext.equals(context)) {
            synchronized (PassWordDialog.class) {
                if (instance == null || !tmpContext.equals(context)) {
                    instance = new PassWordDialog(context);
                }
            }
        }
        tmpContext = context;
        return instance;
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(
                R.layout.dialog_password, null);
        btnOne = (Button) mView.findViewById(R.id.btn_dialog_password_one);
        btnTwo = (Button) mView.findViewById(R.id.btn_dialog_password_two);
        btnThree = (Button) mView.findViewById(R.id.btn_dialog_password_three);
        btnFour = (Button) mView.findViewById(R.id.btn_dialog_password_four);
        btnFive = (Button) mView.findViewById(R.id.btn_dialog_password_five);
        btnSix = (Button) mView.findViewById(R.id.btn_dialog_password_six);
        btnSeven = (Button) mView.findViewById(R.id.btn_dialog_password_seven);
        btnEight = (Button) mView.findViewById(R.id.btn_dialog_password_eight);
        btnNine = (Button) mView.findViewById(R.id.btn_dialog_password_nine);
        btnZero = (Button) mView.findViewById(R.id.btn_dialog_zero);
        btnOk = (Button) mView.findViewById(R.id.btn_dialog_ok);
        btnCancle = (Button) mView.findViewById(R.id.btn_dialog_cancle);
        textPassword = (TextView) mView.findViewById(R.id.text_dialog_password);
        ivClear = (ImageView) mView.findViewById(R.id.iv_dialog_password_delete);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);

        btnZero.setOnClickListener(this);
        ivClear.setOnClickListener(this);
        super.setContentView(mView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_password_one:
                textPassword.append("1");
                break;
            case R.id.btn_dialog_password_two:
                textPassword.append("2");
                break;
            case R.id.btn_dialog_password_three:
                textPassword.append("3");
                break;
            case R.id.btn_dialog_password_four:
                textPassword.append("4");
                break;
            case R.id.btn_dialog_password_five:
                textPassword.append("5");
                break;
            case R.id.btn_dialog_password_six:
                textPassword.append("6");
                break;
            case R.id.btn_dialog_password_seven:
                textPassword.append("7");
                break;
            case R.id.btn_dialog_password_eight:
                textPassword.append("8");
                break;
            case R.id.btn_dialog_password_nine:
                textPassword.append("9");
                break;
            case R.id.btn_dialog_zero:

                textPassword.append("0");
                break;
            case R.id.btn_dialog_cancle:
                textPassword.setText("");
                break;
            case R.id.iv_dialog_password_delete:
                textPassword.setText("");
                break;
            default:
                break;
        }

    }

    public PassWordDialog setCloseClick(android.view.View.OnClickListener lis) {
        btnCancle.setOnClickListener(lis);
        return instance;
    }

    public PassWordDialog setOkListener(android.view.View.OnClickListener listener) {
        btnOk.setOnClickListener(listener);
        return instance;
    }

    public PassWordDialog setHintText(String str) {
        textPassword.setHint(str);
        return instance;
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(true);
    }

    @Override
    public void show() {
        textPassword.setText("");
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}