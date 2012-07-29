package com.cafetux.chifoumi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.cafetux.chifoumi.resolver.Memory;

public class WelcomeActivity extends Activity {

	private Button play_ai;
	private Button play_pvp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initAiPlayButton();
		initPvPPlayButton();
		Memory.reset();
	}

	private void initPvPPlayButton() {
		play_pvp = (Button) findViewById(R.id.pvp);
		play_pvp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(WelcomeActivity.this,
						getResources().getText(R.string.soon),
						Toast.LENGTH_LONG).show();
			}
		});
	}

	private void initAiPlayButton() {
		play_ai = (Button) findViewById(R.id.playAi);
		play_ai.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WelcomeActivity.this,
						PlayerChoiceActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_welcome, menu);
		return true;
	}
}
