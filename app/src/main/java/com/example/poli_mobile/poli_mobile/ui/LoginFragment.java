package com.example.poli_mobile.poli_mobile.ui;

import com.example.poli_mobile.R.id;
import com.example.poli_mobile.R.layout;
import com.example.poli_mobile.poli_mobile.listeners.LoginListenerTwo;
import com.example.poli_mobile.poli_mobile.listeners.loginListener;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile_ws.WsLogin;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment implements loginListener {

	private AutoCompleteTextView tvIdentificacion;
	private TextView tvPassword;
	private Button btnSignIn;
	private LoginListenerTwo lListener;
	private Bundle mBundle;
	private int position;
	private ProgressDialog pdLogin;

	public LoginFragment newInstance(String text, LoginListenerTwo lListener,
			int position) {
		LoginFragment mFragment = new LoginFragment();
		mBundle = new Bundle();
		mFragment.setArguments(mBundle);
		ApplicationSession.position = position;
		ApplicationSession.lListener = lListener;
		if(ApplicationSession.rlayout != null)
		{
			ApplicationSession.rlayout.setBackgroundColor(Color.parseColor("#0277bd"));
		}
		return mFragment;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = new View(getActivity());
		v = inflater.inflate(layout.activity_login, container, false);
		pdLogin = new ProgressDialog(getActivity());
		tvIdentificacion = (AutoCompleteTextView) v.findViewById(id.email);
		tvPassword = (TextView) v.findViewById(id.password);
		btnSignIn = (Button) v.findViewById(id.email_sign_in_button);
		btnSignIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!tvIdentificacion.getText().toString().equals("")
						&& !tvPassword.getText().toString().equals("")) {

					callWs();
					pdLogin.setTitle("Iniciando Sesión");
					pdLogin.setMessage("No te desesperes, ya estás a punto de ingresar");
					pdLogin.show();

				} else {
					Toast.makeText(getActivity(),
							"Debe llenar todos los campos", Toast.LENGTH_SHORT)
							.show();
				}
			}

		});
		
//		if(ApplicationSession.SessionStudent!=null)
//		{
//			Toast.makeText(getActivity(), "Ya has iniciado sesión", Toast.LENGTH_SHORT).show();
//			position = ApplicationSession.position;
//			lListener = ApplicationSession.lListener;
//			lListener.onLoginSuccessfull(position);
//		}

		return v;

	}

	@Override
	public void onSessionAuthenticated(String Session) {
		pdLogin.cancel();		
		ApplicationSession.SessionStudent = Session;
		position = ApplicationSession.position;
		lListener = ApplicationSession.lListener;
		lListener.onLoginSuccessfull(position);
		
	}

	private void callWs() {
		WsLogin x = new WsLogin(tvIdentificacion.getText()
				.toString(), tvPassword.getText().toString());
		x.setLoginListener(this);
		x.execute();

		// Toast.makeText(getActivity(),
		// "Has iniciado sesión satisfactoriamente", Toast.LENGTH_SHORT).show();
		// ApplicationSession.Session = Session;

		// WsHorario x = new WsHorario(";D1LMBBKT");
		// x.execute();

		// WsListadoClases x = new WsListadoClases("4434");
		// x.execute();
	}

	@Override
	public void onSessionFailed() {
		pdLogin.cancel();
		Toast.makeText(getActivity(), "Credenciales incorrectas",
				Toast.LENGTH_SHORT).show();

	}

}