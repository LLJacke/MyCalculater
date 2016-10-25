package com.lljackie.mycalculater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ConversionActivity extends AppCompatActivity {

    Spinner spn_length;

    EditText et_cvs;

    TextView tv_cvs1;
    TextView tv_cvs2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        et_cvs = (EditText) findViewById(R.id.et_cvs);

        tv_cvs1 = (TextView) findViewById(R.id.tv_cvs1);
        tv_cvs2 = (TextView) findViewById(R.id.tv_cvs2);

        spn_length = (Spinner) findViewById(R.id.spn_length);

        spn_length.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String in = et_cvs.getText().toString();
                if ("".equals(in))
                    return;
                for (int i = 0; i < in.length(); i++) {
                    if (!(in.charAt(i) >= '0' && in.charAt(i) <= '9') && in.charAt(i) != '.')
                        return;
                }
                double input = Double.parseDouble(in);
                switch (position) {
                    case 1: {
                        tv_cvs1.setText(input * 1000 + "米");
                        tv_cvs2.setText(input * 10000 + "分米");
                    }
                    break;
                    case 2: {
                        tv_cvs1.setText(input / 1000 + "千米");
                        tv_cvs2.setText(input * 10 + "分米");
                    }
                    break;
                    case 3: {
                        tv_cvs1.setText(input / 10 + "米");
                        tv_cvs2.setText(input * 10 + "厘米");
                    }
                    break;
                    case 4: {
                        tv_cvs1.setText(input / 10 + "分米");
                        tv_cvs2.setText(input * 10 + "毫米");
                    }
                    break;
                    case 5: {
                        tv_cvs1.setText(input / 100 + "分米");
                        tv_cvs2.setText(input / 10 + "厘米");
                    }
                    break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cvs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.it_back: {
                finish();
            }
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
