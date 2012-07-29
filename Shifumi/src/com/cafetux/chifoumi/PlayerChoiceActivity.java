package com.cafetux.chifoumi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.cafetux.chifoumi.model.AttackTypes;

public class PlayerChoiceActivity extends Activity {

	ImageButton btnRock;
	ImageButton btnLeaf;
	ImageButton btnScissor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_choice);
		btnRock = (ImageButton) findViewById(R.id.imageButtonRock);
		btnLeaf = (ImageButton) findViewById(R.id.imageButtonLeaf);
		btnScissor = (ImageButton) findViewById(R.id.imageButtonScissor);

		addListenerOnButton(btnRock, AttackTypes.STONE);
		addListenerOnButton(btnLeaf, AttackTypes.PAPER);
		addListenerOnButton(btnScissor, AttackTypes.SCISSOR);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_player_choice, menu);
		return true;
	}

	private void addListenerOnButton(ImageButton btn, final AttackTypes attack) {

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				launchResultResolutionActivity(attack);
			}

		});

	}

	private void launchResultResolutionActivity(AttackTypes attack) {
		Intent intent = new Intent(PlayerChoiceActivity.this,
				ResultActivity.class);
		intent.putExtra("attack", attack);
		startActivity(intent);

	}
}
