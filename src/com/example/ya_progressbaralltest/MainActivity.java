package com.example.ya_progressbaralltest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	Context context = MainActivity.this;
	Button btn1, btn2, btn3, btn4;
	ProgressDialog myDialog = null;
	ProgressBar progressbar1, progressbar2;
	String which_progress = null;
	TextView text_progressbar1, text_progressbar2, text_percent;

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
		
		progressbar1 = (ProgressBar)findViewById(R.id.progressBar1);
		text_progressbar1 = (TextView)findViewById(R.id.text_progressbar1);
		
		progressbar2 = (ProgressBar)findViewById(R.id.progressBar2);
		text_progressbar2 = (TextView)findViewById(R.id.text_progressbar2);
		
		text_percent = (TextView)findViewById(R.id.text_percent);
		
		//給 ProgressDialog使用。
		final CharSequence str_Dialog_Title = getResources().getString(R.string.str_dialog_title);
		final CharSequence str_Dialog_Contents = getResources().getString(R.string.str_dialog_body); 
		
		//環狀  ProgressDialog
		btn1.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				which_progress = "progress_dialog";
				myDialog =
					ProgressDialog.show(context, str_Dialog_Title, str_Dialog_Contents, true);
				fun_thread();
				
			}
		});
		
		//長條狀  ProgressDialog.
		btn2.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				which_progress = "progress_dialog";
				final CharSequence str_Dialog_Contents = getResources().getString(R.string.str_dialog_body);
				myDialog = new ProgressDialog(context);
				myDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				myDialog.setProgress(0);
				myDialog.setMax(100);
				myDialog.setTitle(str_Dialog_Title);
				myDialog.setMessage(str_Dialog_Contents);
				myDialog.show();
				fun_thread();
			}
		});
		
		//環狀 ProgressBar
		btn3.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				which_progress = "progress_bar1";
				progressbar1.setVisibility(View.VISIBLE);
				progressbar1.setProgress(0);
				progressbar1.setMax(100);
				text_progressbar1.setText(R.string.str_dialog_title);
				fun_thread();
			}
		});
		
		//長條狀 ProgressBar
		btn4.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				which_progress = "progress_bar2";
				progressbar2.setVisibility(View.VISIBLE);
				progressbar2.setProgress(0);
				progressbar2.setMax(100);
				text_progressbar2.setText(R.string.str_dialog_body);
				fun_thread();
			}
		});
	
	}//end method buildViews.
	
	
	
	//透過 Handler傳遞執行緒的狀態。
	Handler handler = new Handler(){
		public void handleMessage(Message msg){
			int p = msg.getData().getInt("PERCENT");
			
			//當事件進度抵達100時，關掉/隱藏 progressDialog/progressBar
			if(p > 100){
				if(which_progress.equals("progress_dialog")){
					myDialog.dismiss();
				
				//Button3 & Button4:ProgressBar.
				}else if(which_progress.equals("progress_bar1")){
					progressbar1.setVisibility(View.GONE);
					text_progressbar1.setText("Button3");
				}else{
					progressbar2.setVisibility(View.GONE);
					text_progressbar2.setText("Button4");
					text_percent.setText("100%");
				}
				
			}else{
				if(which_progress.equals("progress_dialog")){
					myDialog.setProgress(p);
				
				//Button4:ProgressBar Horizontal.
				}else if(which_progress.equals("progress_bar2")){
					progressbar2.setProgress(p);
					text_percent.setText(p + "%");
				}
			}
		}
	};
	
	public void fun_thread(){
		new Thread(){
			public void run(){
				try{
					for(int i = 0; i < 6; i++){  //i < 6???
						sleep(1000);
						int percent = (i + 1) * 20;
						
						//設定進度文字:?%
						Message m = new Message();
						Bundle b = m.getData();
						b.putInt("PERCENT", percent);
						m.setData(b);
						handler.sendMessage(m);
					}
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
