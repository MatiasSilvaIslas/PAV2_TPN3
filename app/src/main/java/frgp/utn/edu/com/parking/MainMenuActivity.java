package frgp.utn.edu.com.parking;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.utn.parkingcontrol.databinding.ActivityMainMenuBinding;
import com.utn.parkingcontrol.ui.myaccount.MyAccountFragment;
import com.utn.parkingcontrol.ui.myaccount.MyAccountViewModel;
import com.utn.parkingcontrol.ui.parking.ParkingFragment;

public class MainMenuActivity extends AppCompatActivity {

    public User user;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainMenu.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_parking, R.id.nav_myaccount)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        TextView tvNameUser = navigationView.getHeaderView(0).findViewById(R.id.tvNameUser);
        TextView tvEmailUser = navigationView.getHeaderView(0).findViewById(R.id.tvEmailUser);

        user = (User)getIntent().getSerializableExtra(PutExtraConst.UserKey);
        tvNameUser.setText(user.getName());
        tvEmailUser.setText(user.getEmail());

        /*
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.nav_myaccount)
                {

                }

                return true;
            }
        });*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}