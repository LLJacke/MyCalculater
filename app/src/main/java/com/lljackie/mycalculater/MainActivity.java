package com.lljackie.mycalculater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_1;
    Button bt_2;
    Button bt_3;
    Button bt_4;
    Button bt_5;
    Button bt_6;
    Button bt_7;
    Button bt_8;
    Button bt_9;
    Button bt_0;
    Button bt_plus;
    Button bt_minus;
    Button bt_multiply;
    Button bt_divide;
    Button bt_ce;
    Button bt_c;
    Button bt_del;
    Button bt_point;
    Button bt_negate;
    Button bt_equal;
    Button bt_sin;
    Button bt_cos;
    Button bt_squ;
    Button bt_cube;

    TextView tv_input;
    TextView tv_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化所有控件
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        bt_6 = (Button) findViewById(R.id.bt_6);
        bt_7 = (Button) findViewById(R.id.bt_7);
        bt_8 = (Button) findViewById(R.id.bt_8);
        bt_9 = (Button) findViewById(R.id.bt_9);
        bt_0 = (Button) findViewById(R.id.bt_0);
        bt_plus = (Button) findViewById(R.id.bt_plus);
        bt_minus = (Button) findViewById(R.id.bt_minus);
        bt_multiply = (Button) findViewById(R.id.bt_multiply);
        bt_divide = (Button) findViewById(R.id.bt_divide);
        bt_ce = (Button) findViewById(R.id.bt_ce);
        bt_c = (Button) findViewById(R.id.bt_c);
        bt_del = (Button) findViewById(R.id.bt_del);
        bt_point = (Button) findViewById(R.id.bt_point);
        bt_negate = (Button) findViewById(R.id.bt_negate);
        bt_equal = (Button) findViewById(R.id.bt_equal);
        bt_sin = (Button) findViewById(R.id.bt_sin);
        bt_cos = (Button) findViewById(R.id.bt_cos);
        bt_squ = (Button) findViewById(R.id.bt_squ);
        bt_cube = (Button) findViewById(R.id.bt_cube);

        tv_input = (TextView) findViewById(R.id.tv_input);
        tv_output = (TextView) findViewById(R.id.tv_output);

        //为按钮设置监听器
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_0.setOnClickListener(this);
        bt_plus.setOnClickListener(this);
        bt_minus.setOnClickListener(this);
        bt_multiply.setOnClickListener(this);
        bt_divide.setOnClickListener(this);
        bt_ce.setOnClickListener(this);
        bt_c.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_point.setOnClickListener(this);
        bt_negate.setOnClickListener(this);
        bt_equal.setOnClickListener(this);
        bt_sin.setOnClickListener(this);
        bt_cos.setOnClickListener(this);
        bt_squ.setOnClickListener(this);
        bt_cube.setOnClickListener(this);

    }

    int flag = 0;           //是否已做过计算的标识，计算完毕置1，重新开始计算时置0

    @Override
    public void onClick(View v) {

        tv_input = (TextView) findViewById(R.id.tv_input);
        tv_output = (TextView) findViewById(R.id.tv_output);
        String input = tv_input.getText().toString();
        String output = tv_output.getText().toString();
        double x = 0;
        int tip = 0;

        switch (v.getId()) {
            case R.id.bt_1:
            case R.id.bt_2:
            case R.id.bt_3:
            case R.id.bt_4:
            case R.id.bt_5:
            case R.id.bt_6:
            case R.id.bt_7:
            case R.id.bt_8:
            case R.id.bt_9:
            case R.id.bt_0:           //在最后输入数字，若输入框内为0或刚进行完一次计算，则清空输入框再输入数字
                String num = ((Button) v).getText().toString();
                if ("0".equals(input) || flag == 1) {
                    tv_input.setText(num);
                    flag = 0;
                } else
                    tv_input.setText(input + num);
                break;
            case R.id.bt_plus:
            case R.id.bt_minus:
            case R.id.bt_multiply:
            case R.id.bt_divide:        //选择运算符
                if ("error".equals(input))      //判断是否为error
                    break;
                input = predigest(input);       //将输入框内的字符串多余的后缀去掉

                if ("".equals(output)) {
                    tv_output.setText(input + " " + ((Button) v).getText().toString());
                    tv_input.setText("0");
                } else {
                    String result = getResult();
                    tv_output.setText(result + " " + ((Button) v).getText().toString());
                    tv_input.setText("0");
                }
                break;
            case R.id.bt_point:         //小数点，先遍历一遍输入框，避免重复
                tip = 0;
                if (flag == 1){
                    tv_input.setText("0.");
                    flag = 0;
                }
                else {
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) == '.') {
                            tip = 1;
                            break;
                        }
                    }
                    if (tip == 0)
                        tv_input.setText(input + ".");
                }
                break;
            case R.id.bt_ce:            //清空输入框
                tv_input.setText("0");
                break;
            case R.id.bt_c:             //清空输入框与输出框
                tv_input.setText("0");
                tv_output.setText("");
                break;
            case R.id.bt_del:           //回退键
                int length = input.length();
                if (flag == 1 || length == 1) {
                    tv_input.setText("0");
                } else {
                    tv_input.setText(input.substring(0, length - 1));
                }
                break;
            case R.id.bt_negate:        //切换输入框正负号
                if ("0".equals(input))
                    break;
                if (!(input.charAt(0) == '-'))
                    input = "-" + input;
                else
                    input = input.substring(1, input.length());
                tv_input.setText(input);
                break;
            case R.id.bt_equal:         //等号，引用getresult()方法计算，结果在输入框输出，输出框清空
                if (!"".equals(output)){
                    String result = getResult();
                    tv_input.setText(result);
                    tv_output.setText("");
                    flag = 1;
                }
                break;
            case R.id.bt_sin:
                x = Double.parseDouble(input);
                x = Math.sin(x);
                tv_input.setText(predigest(x + ""));
                break;
            case R.id.bt_cos:
                x = Double.parseDouble(input);
                x = Math.cos(x);
                tv_input.setText(predigest(x + ""));
                break;
            case R.id.bt_squ:
                x = Double.parseDouble(input);
                x = x * x;
                tv_input.setText(predigest(x + ""));
                break;
            case R.id.bt_cube:
                x = Double.parseDouble(input);
                x = x * x * x;
                tv_input.setText(predigest(x + ""));
                break;
            default:

        }
    }

    //计算结果，获取两个框内的字符串，分解为两个数和一个运算符，再进行运算
    public String getResult() {
        tv_input = (TextView) findViewById(R.id.tv_input);
        tv_output = (TextView) findViewById(R.id.tv_output);
        String input = tv_input.getText().toString();
        String output = tv_output.getText().toString();

        double firstNumber = Double.parseDouble(output.substring(0, output.length() - 2));
        double secondNumber = Double.parseDouble(input);
        double result = 0;
        int isnull = 0;
        String resultString = "";

        switch (output.charAt(output.length() - 1)) {
            case '+':
                result = firstNumber + secondNumber;
                break;
            case '-':
                result = firstNumber - secondNumber;
                break;
            case '×':
                result = firstNumber * secondNumber;
                break;
            case '÷':
                if (secondNumber != 0)
                    result = firstNumber / secondNumber;
                else
                    isnull = 1;
                break;
            default:
        }
        if (isnull == 1)
            return "error";

        resultString = result + "";
        resultString = predigest(resultString);

        if ("-0".equals(resultString))
            resultString = "0";

        return resultString;
    }

    //简化字符串方法
    public String predigest(String s) {
        int tip = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                tip = 1;
                break;
            }
        }
        if (tip == 1) {
            while (s.charAt(s.length() - 1) == '0') {
                s = s.substring(0, s.length() - 1);
            }
            if (s.charAt(s.length() - 1) == '.')
                s = s.substring(0, s.length() - 1);
        }
        return s;
    }
}

































