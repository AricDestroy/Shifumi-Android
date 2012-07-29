package com.cafetux.chifoumi;

import java.io.Serializable;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cafetux.chifoumi.model.AttackTypes;
import com.cafetux.chifoumi.resolver.Memory;
import com.cafetux.chifoumi.resolver.ShifumiMatchResolver;

public class ResultActivity extends Activity {
	private AttackTypes playerChoice;
	private TextView resultText;
	private ImageButton homeButton;
	private ImageButton retryButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		ProgressDialog dialog = ProgressDialog.show(ResultActivity.this, "",
				"En attente de l'adversaire", true);

		Serializable attackExtra = getIntent().getSerializableExtra("attack");
		resultText = (TextView) findViewById(R.id.resultString);
		homeButton = (ImageButton) findViewById(R.id.imageButtonHome);
		retryButton = (ImageButton) findViewById(R.id.imageButtonRetry);
		retryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				resetGame();
			}
		});

		if (attackExtra instanceof AttackTypes) {
			playerChoice = (AttackTypes) attackExtra;

			resolveMatch();
		} else {
			Toast.makeText(ResultActivity.this,
					"Nous avons perdu votre attaque en route..désolé",
					Toast.LENGTH_SHORT).show();
		}
		dialog.hide();
	}

	private void resolveMatch() {

		ShifumiMatchResolver resolver = new ShifumiMatchResolver(
				getApplicationContext(), playerChoice);
		Boolean result = resolver.isPlayerWin();
		Memory.addResult(playerChoice, result);
		resultText.setText(resolver.toString());
		// Toast.makeText(ResultActivity.this, resolver.toString(),
		// Toast.LENGTH_SHORT).show();

	}

	private void resetGame() {
		Intent intent = new Intent(ResultActivity.this,
				PlayerChoiceActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
}
