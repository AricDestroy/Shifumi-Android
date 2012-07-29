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
		dialog.show();
		Serializable attackExtra = getIntent().getSerializableExtra("attack");
		resultText = (TextView) findViewById(R.id.resultString);
		homeButton = (ImageButton) findViewById(R.id.imageButtonHome);

		initPlayAgainButton();
		initHomeButton();
		retrieveAttackAndResolveMatch(attackExtra);

		TextView nbDrawLabelValue = (TextView) findViewById(R.id.textNbDrawValue);
		nbDrawLabelValue.setText(String.valueOf(Memory.getNbDraw()));

		TextView nbWinLabelValue = (TextView) findViewById(R.id.textNbWinValue);
		nbWinLabelValue.setText(String.valueOf(Memory.getNbWin()));

		TextView nbGamesLabelValue = (TextView) findViewById(R.id.textNbGamesValue);
		nbGamesLabelValue.setText(String.valueOf(Memory.getNbGames()));

		TextView nbConsecutiveGameLabelValue = (TextView) findViewById(R.id.textNbConsecutiveWinValue);
		nbConsecutiveGameLabelValue.setText(String.valueOf(Memory
				.getNbConsecutiveWin()));
		dialog.hide();
	}

	private void retrieveAttackAndResolveMatch(Serializable attackExtra) {
		if (attackExtra instanceof AttackTypes) {
			playerChoice = (AttackTypes) attackExtra;
			resolveMatch();
		} else {
			Toast.makeText(ResultActivity.this,
					"Nous avons perdu votre attaque en route..désolé",
					Toast.LENGTH_SHORT).show();
		}
	}

	private void initHomeButton() {
		homeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this,
						WelcomeActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initPlayAgainButton() {
		retryButton = (ImageButton) findViewById(R.id.imageButtonRetry);
		retryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				resetGame();
			}
		});
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
