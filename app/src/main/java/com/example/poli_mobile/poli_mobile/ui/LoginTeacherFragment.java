package com.example.poli_mobile.poli_mobile.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.liveo.utils.Constant;

import com.example.poli_mobile.R;
import com.example.poli_mobile.listeners.LoginListener;
import com.example.poli_mobile.listeners.loginListener;
import com.example.poli_mobile.listeners.loginTeacherListener;
import com.example.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile_ws.WsLogin;
import com.example.poli_mobile_ws.WsTeacherLogin;

public class LoginTeacherFragment extends Fragment implements loginTeacherListener {

	private AutoCompleteTextView tvIdentificacion;

	private EditText tvYear, tvPassword;
	private Button btnSignIn;
	private LoginListener lListener;
	private Bundle mBundle;
	private int position;
	private Spinner spType;
	private ProgressDialog pdLogin;

	public LoginTeacherFragment newInstance(String text,
			LoginListener lListener, int position) {
		LoginTeacherFragment mFragment = new LoginTeacherFragment();
		mBundle = new Bundle();
		mBundle.putString(Constant.TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		ApplicationSession.position = position;
		ApplicationSession.lListener = lListener;
		if (ApplicationSession.rlayout != null) {
			ApplicationSession.rlayout.setBackgroundColor(Color
					.parseColor("#0277bd"));
		}
		return mFragment;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = new View(getActivity());
		v = inflater.inflate(R.layout.fragment_teacher_login, container, false);
		pdLogin = new ProgressDialog(getActivity());
		tvIdentificacion = (AutoCompleteTextView) v.findViewById(R.id.email);
		tvPassword = (EditText) v.findViewById(R.id.password);
		tvYear = (EditText) v.findViewById(R.id.tvYear);
		spType = (Spinner) v.findViewById(R.id.spType);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.types_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spType.setAdapter(adapter);
		btnSignIn = (Button) v.findViewById(R.id.email_sign_in_button);
		btnSignIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!tvIdentificacion.getText().toString().equals("")
						&& !tvPassword.getText().toString().equals("") &&!tvYear.getText().toString().equals("")) {

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

		return v;

	}

	@Override
	public void onSessionAuthenticated(String Session) {
		pdLogin.cancel();
		ApplicationSession.SessionTeacher = Session;
		position = ApplicationSession.position;
		lListener = ApplicationSession.lListener;
		lListener.onLoginSuccessfull(position);

	}

	private void callWs() {
		
		WsTeacherLogin x = new WsTeacherLogin(tvIdentificacion.getText().toString(),
				tvPassword.getText().toString(),tvYear.getText().toString(),String.valueOf(spType.getSelectedItemPosition()));
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