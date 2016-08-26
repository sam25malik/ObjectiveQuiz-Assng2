package in.ac.iiitd.psingh.mc16.objectivequiz;

/**
 * Created by Sameer on 22/08/16.
 */


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class HintActivity extends AppCompatActivity {
    private Button show_hint;

    int answer;
    int flag_new;
    int flag_hint;
    int new_hintans;
    TextView mTextView;

    private static final String TAG = "HintActivity";


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hint_activity);

        new_hintans=0;
        answer= getIntent().getExtras().getInt("ansnew");
        flag_new= getIntent().getExtras().getInt("flag_new");
        flag_hint= getIntent().getExtras().getInt("flag_hint");


        show_hint = (Button) findViewById(R.id.show_hint);
        mTextView = (TextView) findViewById(R.id.textHint);

        show_hint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View correct) {

                mTextView.setText("A prime number is divisible only by itself and by 1. For example, 7 can be divided only by 7 and by 1.");
                if (savedInstanceState != null) {
                    //Restore the fragment's instance
                    savedInstanceState.putString("Text", mTextView.getText().toString());
                    new_hintans = savedInstanceState.getInt("hint_ans",new_hintans);


                }
                new_hintans=1;

                Intent returnData = new Intent();
                returnData.putExtra("hint_ans", new_hintans);
                setResult(Activity.RESULT_OK, returnData);

            }
        });




    }

    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Text", mTextView.getText().toString());
        savedInstanceState.putInt("hint_ans",new_hintans);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        mTextView.setText(savedInstanceState.getString("Text"));
        new_hintans = savedInstanceState.getInt("hint_ans");

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
       if(new_hintans==0) {
            Toast.makeText(getApplicationContext(), "You didnt see the Hint!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            return;

        }

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
