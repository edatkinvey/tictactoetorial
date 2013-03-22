package com.kinvey.sample.tictac.fragments;

import java.util.Arrays;
import java.util.List;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;
import com.kinvey.java.query.AbstractQuery.SortOrder;
import com.kinvey.sample.tictac.GameEntity;
import com.kinvey.sample.tictac.R;
import com.kinvey.sample.tictac.TicTac;

public class MenuFragment extends SherlockFragment implements OnClickListener {

	private TableLayout leaderBoard;
	private Button newGame;



	private List<GameEntity> leaders;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onResume() {
		super.onResume();
		populateViews();
		if (leaders != null && leaders.size() > 0) {
			updateBoard();
		}
	}

	private void populateViews() {

		Query top = new Query();
		top.addSort("totalWins", SortOrder.DESC);
		top.setLimit(5);

		TicTac.getClient(getSherlockActivity())
				.appData(TicTac.Collection, GameEntity.class)
				.get(top, new KinveyListCallback<GameEntity>() {

					@Override
					public void onSuccess(GameEntity[] arg0) {
						leaders = Arrays.asList(arg0);
						updateBoard();

					}

					@Override
					public void onFailure(Throwable arg0) {
						Toast.makeText(getSherlockActivity(),
								"something went wrong -> " + arg0.getMessage(),
								Toast.LENGTH_SHORT).show();

					}
				});

	}

	private void updateBoard() {

		leaderBoard.removeAllViews();
		TableRow titleRow = new TableRow(getSherlockActivity());
		titleRow.setLayoutParams(new TableRow.LayoutParams(
				TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.WRAP_CONTENT));

		TextView nameTitle = new TextView(getSherlockActivity());
		nameTitle.setText("Name");
		nameTitle.setTypeface(null, Typeface.BOLD);
		TextView winsTitle = new TextView(getSherlockActivity());
		winsTitle.setText("Wins");
		winsTitle.setTypeface(null, Typeface.BOLD);
		TextView loseTitle = new TextView(getSherlockActivity());
		loseTitle.setText("Loses");
		loseTitle.setTypeface(null, Typeface.BOLD);

		titleRow.addView(nameTitle);
		titleRow.addView(winsTitle);
		titleRow.addView(loseTitle);
		leaderBoard.addView(titleRow);

		for (int i = 0; i < leaders.size(); i++) {
			TableRow row = new TableRow(getSherlockActivity());
			row.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.MATCH_PARENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			TextView name = new TextView(getSherlockActivity());
			name.setText(leaders.get(i).getAcl().getCreator());
			TextView wins = new TextView(getSherlockActivity());
			wins.setText(String.valueOf(leaders.get(i).getTotalWins()));
			TextView loses = new TextView(getSherlockActivity());
			loses.setText(String.valueOf(leaders.get(i).getTotalLoses()));

			row.addView(name);
			row.addView(wins);
			row.addView(loses);
			leaderBoard.addView(row);
		}


	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group,
			Bundle saved) {
		View v = inflater.inflate(R.layout.fragment_menu, group, false);
		bindViews(v);
		return v;
	}

	private void bindViews(View v) {
		leaderBoard = (TableLayout) v.findViewById(R.id.menu_leaderboard);
		newGame = (Button) v.findViewById(R.id.menu_newgame);
		newGame.setOnClickListener(this);
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		if (v == newGame) {
			FragmentTransaction ft = getSherlockActivity()
					.getSupportFragmentManager().beginTransaction();
			ft.replace(android.R.id.content, new GameFragment());
			ft.addToBackStack("Game");
			ft.commit();
		}
	}

}
