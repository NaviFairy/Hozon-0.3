package com.alg.hozon_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;

    private CategoriasViewModel categoriasViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavHostFragment host = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        // Set up Action Bar
        NavController navController = host.getNavController();

        drawerLayout = findViewById(R.id.drawer_layout);

        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.wellcome_dest);
        topLevelDestinations.add(R.id.graficos_dest);
        topLevelDestinations.add(R.id.cuentas_dest);
        topLevelDestinations.add(R.id.categorias_dest);
        topLevelDestinations.add(R.id.preferencias_dest);

        if (null != drawerLayout) {
            appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations)
                    .setOpenableLayout(drawerLayout)
                    .build();
        } else {
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        }
        setupActionBar(navController, appBarConfiguration);
        setupNavigationMenu(navController);

        setupCategoriasViewModel();

        forceDatabaseCreation();

    }

    private void setupCategoriasViewModel() {
        categoriasViewModel = new ViewModelProvider(this).get(CategoriasViewModel.class);
    }

    private void forceDatabaseCreation() {
        categoriasViewModel.forceDBCreation();
    }


    private void setupNavigationMenu(NavController navController) {
        //TODO STEP 10 - Use NavigationUI to set up a Navigation View
        // In split screen mode, you can drag this view out from the left
        // This does NOT modify the actionbar
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            NavigationUI.setupWithNavController(navigationView, navController);
        }
    }
    private void setupActionBar(NavController navController,
                                AppBarConfiguration appBarConfig) {
        //TODO STEP 12 - Have NavigationUI handle what your ActionBar displays
        // This allows NavigationUI to decide what label to show in the action bar
        // By using appBarConfig, it will also determine whether to
        // show the up arrow or drawer menu icon
        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
        //END STEP 12
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        //TODO STEP 8 - Have Navigation UI Handle the item selection -
        // make sure to comment or delete the old return statement above
        // Have the NavigationUI look for an action or destination matching the menu
        // item id and navigate there if found.
        // Otherwise, bubble up to the parent.
        if (NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.nav_host_fragment)))
            return true;
        else {
            return super.onOptionsItemSelected(item);
        }
        //END STEP 8
    }

    //TODO STEP 12  - Have NavigationUI handle up behavior in the ActionBar
    @Override
    public boolean onSupportNavigateUp() {
        // Allows NavigationUI to support proper up navigation or the drawer layout
        // drawer menu, depending on the situation
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), appBarConfiguration);
    }
}
