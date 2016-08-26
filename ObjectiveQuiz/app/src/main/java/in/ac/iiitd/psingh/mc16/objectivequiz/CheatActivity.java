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

/**
 * Created by Sameer on 22/08/16.
 */
public class CheatActivity extends AppCompatActivity {
    int answer;
    Button show_cheat;
    TextView TextCheat;
    int new_ans;
    int prime_number;
    int flag_new;
    int flag_hint;
    int flag_cheat;

    private static final String TAG = "CheatActivity";

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

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheat_activity);
        flag_cheat=0;
        answer= getIntent().getExtras().getInt("ansnew");
        flag_new= getIntent().getExtras().getInt("flag_new");
        flag_hint=getIntent().getExtras().getInt("flag_hint");
        prime_number=getIntent().getExtras().getInt("number");
        show_cheat = (Button) findViewById(R.id.show_cheat);
        TextCheat = (TextView) findViewById(R.id.textCheat);

        show_cheat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View correct) {

                flag_cheat=1;
                if(prime(prime_number)==0) {
                    TextCheat.setText("Prime Number");

                    new_ans=answer;
                    if (savedInstanceState != null) {
                        //Restore the fragment's instance
                        savedInstanceState.putInt("answer",answer);
                        savedInstanceState.putInt("flag_new",flag_new);
                        savedInstanceState.putString("Text", TextCheat.getText().toString());


                    }
                }
                else
                {
                    TextCheat.setText("Not a Prime Number");

                    new_ans=answer;
                    if (savedInstanceState != null) {
                        //Restore the fragment's instance
                        savedInstanceState.putInt("answer",answer);
                        savedInstanceState.putInt("flag_new",flag_new);
                        savedInstanceState.putString("Text", TextCheat.getText().toString());


                    }
                }

                Intent returnDataCheat = new Intent();
                returnDataCheat.putExtra("hint_cheat",flag_cheat);
                setResult(Activity.RESULT_OK, returnDataCheat);

            }
        });


    }

    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Text", TextCheat.getText().toString());
        savedInstanceState.putInt("hint_cheat", flag_cheat);



    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        TextCheat.setText(savedInstanceState.getString("Text"));
        flag_cheat=savedInstanceState.getInt("hint_cheat", flag_cheat);


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
        if(flag_cheat==0) {
            Toast.makeText(getApplicationContext(),"You didnt see the Cheat!", Toast.LENGTH_SHORT).show();
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
