package org.me.gcu.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

        TextView Number, solu;
        MaterialButton button_c, button_B1, button_B2;
        MaterialButton button_Div, button_Mult, button_Add, button_sub, button_equals;
        MaterialButton button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
        MaterialButton button_AC, button_dec;

    //this isnt meant to be here
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Number = findViewById(R.id.Number);
        solu = findViewById(R.id.solu);

        //do this for all of the otherr buttons
        assignId(button_c, R.id.button_c);
        assignId(button_B1, R.id.button_B1);
        assignId(button_B2, R.id.button_B2);
        assignId(button_Div, R.id.button_Div);
        assignId(button_Mult, R.id.button_Mult);
        assignId(button_Add, R.id.button_Add);
        assignId(button_sub, R.id.button_sub);
        assignId(button_equals, R.id.button_equals);
        assignId(button_0, R.id.button_0);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);
        assignId(button_AC, R.id.button_AC);
        assignId(button_dec, R.id.button_dec);














    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solu.getText().toString();

        if(buttonText.equals("AC")){
            solu.setText("");
            Number.setText("");
            return;
        }

        if(buttonText.equals("="))
        {
            solu.setText(Number.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);

        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }


        solu.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            Number.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }

            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}