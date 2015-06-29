package com.example.ya_progressbaralltest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	Context context = MainActivity.this;
	Button btn1, btn2, btn3, btn4;
	ProgressDialog myDialog = null;
	ProgressBar progressbar1, porgressbar2;
	String which_progress = null;
	TextView text_percent, text_progressbar1, text_progressbar2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buildViews();
	}
	
	private void buildViews(){
		
		
		btn1 = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		btn3 = (Button)findViewById(R.id.button3);
		btn4 = (Button)findViewById(R.id.button4);
		
		final CharSequence str_Dialog_Title = getResources().getString(R.string.str_dialog_title);
		final CharSequence str_Dialog_Contents = getResources().getString(R.string.str_dialog_body); 
		
		
		btn1.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				which_progress = "progress_dialog";
				myDialog =
					ProgressDialog.show(context, str_Dialog_Title, str_Dialog_Contents, true);
				fun_thread();
				
			}
		});
	}
	
	public void fun_thread(){
		new Thread(){
			public void run(){
				try{
					
				}catch(Exception e){
					e.printStackTrace();
				}
				finally{
					//myDialog.dismiss();
				}
			}
		}.start();
	}
	
}//end class MainActivity.
