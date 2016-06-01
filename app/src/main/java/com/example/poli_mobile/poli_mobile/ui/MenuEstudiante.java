package com.example.poli_mobile.poli_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile.utilidades.PoliPreferences;

import java.util.List;

public class MenuEstudiante extends AppCompatActivity {
    /**
     * Instancia del drawer
     */
    private DrawerLayout drawerLayout;
    private PoliPreferences _axPrefs;

    /**
     * Titulo inicial del drawer
     */
    private String drawerTitle;
    private TextView tvUserName, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.setContext(this);
        _axPrefs = new PoliPreferences(this);
        setContentView(R.layout.activity_main);
        setToolbar(); // Setear Toolbar como action bar

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        tvEmail = (TextView) findViewById(R.id.email);
        tvUserName = (TextView) findViewById(R.id.username);

        //tvEmail.setText(_axPrefs.getUserEmail());
        //tvUserName.setText(_axPrefs.getUserName());

        drawerTitle = "Inicio";
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            selectItem(drawerTitle, bundle);
        }

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_drawer);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);
                        // Crear nuevo fragmento
                        String title = menuItem.getTitle().toString();
                        Bundle bundle = new Bundle();
                        selectItem(title, bundle);
                        return true;
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        if (getVisibleFragment() instanceof Noticias) {
            super.onBackPressed();
        } else {
            selectItem("Inicio", null);
        }
    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = MenuEstudiante.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }

    private void selectItem(String title, Bundle argument) {
        // Enviar título como arguemento del fragmento
        Bundle args = new Bundle();
        Fragment fragment;
        drawerLayout.closeDrawers(); // Cerrar drawer

        switch (title) {
            case "Inicio":
                fragment = Noticias.newInstance(title);
                args.putString(Noticias.ARG_SECTION_TITLE, title);
                fragment.setArguments(args);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Lista de Clase":
                fragment = new ListadoClasesFragment();
                args.putString(Noticias.ARG_SECTION_TITLE, title);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Citas Médicas":
                fragment =  new CitasFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Auto Evaluación":
                fragment =  new AutoEvaluacionFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Facultades":
                fragment =  new FacultadesFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Calendario":
                fragment =  new CalendarioFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Horario de Clase":
                fragment =  new HorarioFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Notas":
                fragment =  new NotasFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Parciales":
                fragment =  new ParcialesFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Asesoría Académica":
                fragment =  new AsesoriaFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Acerca del Poli":
                fragment =  new AcercaDeActivity();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            case "Recibos de Pago":
                fragment =  new ComprobanteFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
            default:
                fragment = Noticias.newInstance(title);
                args.putString(Noticias.ARG_SECTION_TITLE, title);
                fragment.setArguments(args);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                setTitle(title); // Setear título actual
                break;
        }
    }

    public void logout() {
        _axPrefs.storageToken("");
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.menu, menu);
            if(ApplicationSession.getType().equals("3"))
            {

            }
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}