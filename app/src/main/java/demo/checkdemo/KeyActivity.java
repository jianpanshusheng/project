package demo.checkdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.checkdemo.view.BankCardTextWatcher;
import demo.checkdemo.view.PassWordDialog;

public class KeyActivity extends AppCompatActivity {


    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.bt_key1)
    Button btKey1;
    @BindView(R.id.bt_key2)
    Button btKey2;
    @BindView(R.id.bt_key3)
    Button btKey3;
    @BindView(R.id.tableRow1)
    TableRow tableRow1;
    @BindView(R.id.bt_key4)
    Button btKey4;
    @BindView(R.id.bt_key5)
    Button btKey5;
    @BindView(R.id.bt_key6)
    Button btKey6;
    @BindView(R.id.tableRow2)
    TableRow tableRow2;
    @BindView(R.id.bt_key7)
    Button btKey7;
    @BindView(R.id.bt_key8)
    Button btKey8;
    @BindView(R.id.bt_key9)
    Button btKey9;
    @BindView(R.id.tableRow3)
    TableRow tableRow3;
    @BindView(R.id.bt_key0)
    Button btKey0;
    @BindView(R.id.bt_key_clear)
    Button btKeyClear;
    @BindView(R.id.tableRow4)
    TableRow tableRow4;
    @BindView(R.id.button16)
    Button button16;
    @BindView(R.id.bt_key_sure)
    Button btKeySure;
    private PassWordDialog passWordDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);
        ButterKnife.bind(this);


//        etContent.setInputType(InputType.TYPE_NULL);//et始终不获取软键盘
//        showPassWordDialog();

        //禁止软键盘弹出
//        if (android.os.Build.VERSION.SDK_INT > 10) {//4.0以上 danielinbiti
//            try {
//                Class<EditText> cls = EditText.class;
//                Method setShowSoftInputOnFocus;
//                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
//                        boolean.class);
//                setShowSoftInputOnFocus.setAccessible(true);
//                setShowSoftInputOnFocus.invoke(etContent, false);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        //监听editTextView获取焦点
//        etContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus){
//                    //隐藏系统软键盘
//                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(etContent.getWindowToken(),0);
//                }
//            }
//        });

        //EditText设置hint的字体大小
//        String hintStr = "请输入12位取票码";
//        SpannableString ss =  new SpannableString(hintStr);
//        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(16, true);
////        etContent.setHintTextColor(R.color.colorPrimary);
//        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        etContent.setHint(new SpannedString(ss));


        //下面的这段 即显示光标又不弹出软键盘
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            etContent.setInputType(InputType.TYPE_NULL);
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setSoftInputShownOnFocus;
                setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(etContent, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //下面的这段 每四个一个空格
//        BankCardTextWatcher.bind(etContent);

//
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Log.e("TAG", "beforeTextChanged: ");
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                space = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        space++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("TAG", "onTextChanged: ");
                int length = s.length();
                buffer.append(s.toString());
                if (length == beforeTextLength || length <= 3
                        || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("TAG", "afterTextChanged: ");
                if (isChanged) {
                    int selectionIndex = etContent.getSelectionEnd();
                    //total char length
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }
                    //total space count
                    index = 0;
                    int totalSpace = 0;
                    while (index < buffer.length()) {
                        if ((index == 4 || index == 9 || index == 14 || index == 19 || index == 24)) {
                            buffer.insert(index, ' ');
                            totalSpace++;
                        }
                        index++;
                    }
                    //selection index
                    if (totalSpace > space) {
                        selectionIndex += (totalSpace - space);
                    }
                    char[] tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (selectionIndex > str.length()) {
                        selectionIndex = str.length();
                    } else if (selectionIndex < 0) {
                        selectionIndex = 0;
                    }
                    etContent.setText(str);
                    Editable text = etContent.getText();
                    //set selection
                    Selection.setSelection(text, selectionIndex < maxLength ? selectionIndex : maxLength);
                    isChanged = false;
                }
            }
        });

    }

    //default max length = 21 + 5 space
    private static final int DEFAULT_MAX_LENGTH = 21 + 5;
    //max input length
    private int maxLength = DEFAULT_MAX_LENGTH;
    private int beforeTextLength = 0;
    private boolean isChanged = false;
    //space count
    private int space = 0;

    private StringBuffer buffer = new StringBuffer();


    @OnClick({R.id.bt_key1, R.id.bt_key2, R.id.bt_key3, R.id.bt_key4, R.id.bt_key5, R.id.bt_key6, R.id.bt_key7, R.id.bt_key8, R.id.bt_key9, R.id.bt_key0, R.id.bt_key_clear, R.id.bt_key_sure, R.id.ll_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_key1:
                etContentChange("1");
                break;
            case R.id.bt_key2:
                etContentChange("2");
                break;
            case R.id.bt_key3:
                etContentChange("3");
                break;
            case R.id.bt_key4:
                etContentChange("4");
                break;
            case R.id.bt_key5:
                etContentChange("5");
                break;
            case R.id.bt_key6:
                etContentChange("6");
                break;
            case R.id.bt_key7:
                etContentChange("7");
                break;
            case R.id.bt_key8:
                etContentChange("8");
                break;
            case R.id.bt_key9:
                etContentChange("9");
                break;
            case R.id.bt_key0:
                etContentChange("0");
                break;
            case R.id.ll_delete:
                etContent.dispatchKeyEvent(new KeyEvent(
                        KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.bt_key_clear:
                etContent.setText("");
                break;
            case R.id.bt_key_sure:
                String trim = etContent.getText().toString().replaceAll(" ", "");
                Toast.makeText(this, trim, Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onViewClicked: " + trim);

                break;
        }
    }

    private void etContentChange(String content) {

        int index = etContent.getSelectionStart();//获取到光标所在的位置
        Editable editableText = etContent.getEditableText();

        int length = etContent.getText().toString().replaceAll(" ", "").length();
        if (etContent != null && length >= 12) {
            Toast.makeText(this, "取票码为12位", Toast.LENGTH_SHORT).show();
        } else {
            etContent.getText().insert(etContent.getSelectionStart(),content);
//            etContent.append(content);
//            if (length == 4 || length == 8) {
//                etContent.append(" " + content);
//            } else {
//                etContent.append(content);
//                if (index < 0 || index >= etContent.length()) {
//                    editableText.append(content);
//                } else {
//                    editableText.insert(index, content);//光标所在位置插入文字
//
////
//                }
//            }

        }
    }


    /**
     * 系统管理员密码键盘
     */
    private void showPassWordDialog() {
        passWordDialog = PassWordDialog.getInstance(KeyActivity.this);
        passWordDialog.setCancelable(true);
        passWordDialog.setOkListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                passWordDialog.dismiss();
            }
        });
        passWordDialog.setCloseClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                passWordDialog.dismiss();

            }
        });
        passWordDialog.show();
    }


}
