package in.ac.iiitd.psingh.mc16.objectivequiz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNext;
    private Button mHintButton;
    private Button mCheatButton;


    TextView Number;

    int ans;
    int output;
    int flag_new;
    int flag_hint;
    int new_hint;

    private int REQUEST_CODE=1;

    private final String FLAG_VALUE = "someNewValue";

    private final String HINT_VALUE = "someHintValue";
    private final String NEW_VALUE = "newHintValue";


    private static final String TAG = "QuizActivity";

    public int prime(int ans)
    {
        int i,flag=0;
        for(i=2;i<ans;i++)
        {
            if(ans%i==0)
            {
                flag=1;
                break;
            }
            else
            {
                flag=0;
            }
        }

        return flag;





    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        if (savedInstanceState != null) {
            //Restore the fragments instance
            flag_new = savedInstanceState.getInt(FLAG_VALUE);
            flag_hint = savedInstanceState.getInt(HINT_VALUE);
            new_hint = savedInstanceState.getInt(NEW_VALUE);


        }


        Number = (TextView) findViewById(R.id.textViewer);
        mNext = (Button) findViewById(R.id.next_button);
        mNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int Min = 1, Max = 1000;

                Random num = new Random();
                ans = num.nextInt(Max - Min + 1) + Min;


                Number.setText(String.valueOf(ans));
                flag_new=0;
                flag_hint=0;
                new_hint=0;

            }
        });




        mTrueButton = (Button) findViewById(R.id.TrueButton);

        mTrueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View correct) {
                    output = prime(ans);
                    if (savedInstanceState != null) {
                        //Restore the fragment's instance
                        savedInstanceState.putInt("ans", ans);
                        savedInstanceState.putInt("output", output);
                        savedInstanceState.putString("Text", Number.getText().toString());


                    }

                if ((prime(ans) == 0) && (flag_new == 0)) {
                    if(flag_hint==0) {
                        Toast.makeText(getApplicationContext(), "Correct Ans!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "You have seen the Hint!", Toast.LENGTH_SHORT).show();

                    }

                    }

                else if ((prime(ans) == 0) && (flag_new == 1))
                {
                    if(flag_hint==0) {
                        Toast.makeText(getApplicationContext(), "You have cheated!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "You have cheated and seen the hint!", Toast.LENGTH_SHORT).show();

                    }
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Incorrect Please Try Again!", Toast.LENGTH_SHORT).show();

                }
            }

        });

        mFalseButton = (Button) findViewById(R.id.FalseButton);

        mFalseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View correct) {

                    output=prime(ans);
                    if (savedInstanceState != null) {
                        //Restore the fragment's instance
                        savedInstanceState.putInt("ans", ans);
                        savedInstanceState.putInt("output", output);
                        savedInstanceState.putString("Text", Number.getText().toString());

                    }

                    if ((prime(ans) == 1) && (flag_new==0)) {
                        if (flag_hint == 0) {
                            Toast.makeText(getApplicationContext(), "Correct Ans!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "You have seen the Hint!", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else if((prime(ans) == 1) && (flag_new==1)) {
                        if (flag_hint == 0) {
                            Toast.makeText(getApplicationContext(), "You have cheated!", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "You have cheated and seen the Hint!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    else  {
                        Toast.makeText(getApplicationContext(), "Incorrect Please Try Again!", Toast.LENGTH_SHORT).show();

                    }
                }

        });


        mHintButton = (Button) findViewById(R.id.hint_button);

        mHintButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View correct) {

                Intent intent = new Intent(QuizActivity.this, HintActivity.class);
                intent.putExtra("hint", flag_hint);
                startActivityForResult(intent, 1);

                /*Intent myIntent2 = new Intent(QuizActivity.this, HintActivity.class);
                myIntent2.putExtra("ansnew",output);
                myIntent2.putExtra("number",ans);

                myIntent2.putExtra("flag_hint",flag_hint);

                startActivity(myIntent2);*/

                if (savedInstanceState != null) {
                    //Restore the fragment's instance
                    savedInstanceState.putInt("ans",ans);
                    flag_hint = savedInstanceState.getInt(HINT_VALUE);
                    savedInstanceState.putString("Text", Number.getText().toString());


                }
            }
        });


        mCheatButton = (Button) findViewById(R.id.cheat_button);

        mCheatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View correct) {

                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                intent.putExtra("hint2", flag_new);
                startActivityForResult(intent, 2);
                /*
                Intent myIntent2 = new Intent(QuizActivity.this, CheatActivity.class);
                myIntent2.putExtra("ansnew",output);
                myIntent2.putExtra("number",ans);

                myIntent2.putExtra("flag_new",flag_new);

                startActivity(myIntent2);*/

                if (savedInstanceState != null) {
                    //Restore the fragment's instance
                    savedInstanceState.putInt("ans",ans);
                    flag_new = savedInstanceState.getInt(FLAG_VALUE);
                    savedInstanceState.putString("Text", Number.getText().toString());


                }
          }
        });




    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    flag_hint = data.getIntExtra("hint_ans", 0);
                    if(flag_hint==1) {
                        Toast.makeText(getApplicationContext(), "You took the Hint!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "You didnt see the Hint!", Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
            case 2:
                if (resultCode == Activity.RESULT_OK) {
                    flag_new = data.getIntExtra("hint_cheat", 0);
                    if(flag_new==1) {
                        Toast.makeText(getApplicationContext(), "You took the Cheat!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "You didnt see the Cheat!", Toast.LENGTH_SHORT).show();

                    }
                    break;
                }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("ans",ans);
        savedInstanceState.putInt("output",output);
        savedInstanceState.putInt(FLAG_VALUE,flag_new);
        savedInstanceState.putInt(HINT_VALUE,flag_hint);
        savedInstanceState.putInt(NEW_VALUE, new_hint);

        savedInstanceState.putString("Text", Number.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        ans=savedInstanceState.getInt("ans");
        output=savedInstanceState.getInt("output",output);
        flag_new = savedInstanceState.getInt(FLAG_VALUE);
        flag_hint = savedInstanceState.getInt(HINT_VALUE);
        new_hint=savedInstanceState.getInt(NEW_VALUE,new_hint);
        Number.setText(savedInstanceState.getString("Text"));
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Toast.makeText(this,"landscape",Toast.LENGTH_SHORT).show();

        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            Toast.makeText(this,"portrait",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Inside OnPause");

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"Inside OnREsume");


    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside OnSTop");

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }
}

