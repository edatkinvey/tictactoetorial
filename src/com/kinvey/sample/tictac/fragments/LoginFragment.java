package com.kinvey.sample.tictac.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.kinvey.android.callback.KinveyUserCallback;
import com.kinvey.java.User;
import com.kinvey.sample.tictac.R;
import com.kinvey.sample.tictac.TicTac;
import com.kinvey.sample.tictac.R.id;
import com.kinvey.sample.tictac.R.layout;

public class LoginFragment extends SherlockFragment implements OnClickListener {

	private EditText username;
	private EditText password;
	private Button login;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group,
			Bundle saved) {
		View v = inflater.inflate(R.layout.fragment_login, group, false);
		bindViews(v);
		return v;
	}

	private void bindViews(View v) {
		username = (EditText) v.findViewById(R.id.login_username);
		password = (EditText) v.findViewById(R.id.login_password);
		login = (Button) v.findViewById(R.id.login_button);
		login.setOnClickListener(this);

		if (TicTac.getClient(getSherlockActivity()).user().getUsername() != null) {
			username.setText(TicTac.getClient(getSherlockActivity()).user()
					.getUsername());
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		populateViews();
	}

	public void populateViews() {

	}

	@Override
	public void onClick(View v) {
		if (v == login) {
			login();

		}

	}

	private void login() {

		String user = username.getText().toString();
		String pass = password.getText().toString();

		TicTac.getClient(getSherlockActivity()).user()
				.login(user, pass, new KinveyUserCallback() {

					@Override
					public void onSuccess(User arg0) {
						Toast.makeText(
								getSherlockActivity(),
								"Logged in as: "
										+ TicTac.getClient(
												getSherlockActivity()).user()
												.getUsername(),
								Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onFailure(Throwable arg0) {
						Toast.makeText(getSherlockActivity(),
								"Error logging in: " + arg0.getMessage(),
								Toast.LENGTH_SHORT).show();

					}
				});

	}

}
