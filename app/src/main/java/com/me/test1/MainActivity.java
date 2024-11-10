package com.me.test1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.me.test1.databinding.ActivityMainBinding;
import com.me.test1.dto.florist.FloristDTO;
import com.me.test1.dto.plant.PlantDTO;
import com.me.test1.dto.plant.PlantListRecordDTO;
import com.me.test1.dto.planttype.PlantTypeListRecordDTO;
import com.me.test1.ui.dashboard.PlantRegistrationFragment;
import com.me.test1.ui.dashboard.PlantTypeCardFragment;
import com.me.test1.ui.dashboard.PlantTypeListFragment;
import com.me.test1.ui.home.EditFloristInfoFragment;
import com.me.test1.ui.home.EditFloristPlantFragment;
import com.me.test1.ui.home.FloristPlantInfoFragment;
import com.me.test1.ui.home.FloristPlantsFragment;
import com.me.test1.ui.notifications.DateNotificationFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            Intent intent = new Intent(this, Authorization.class);
            startActivity(intent);
        }else {
            Info.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            BottomNavigationView navView = findViewById(R.id.nav_view);
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController);
        }
    }

    public void replaceFragment1(PlantTypeListRecordDTO plantType){
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        PlantTypeCardFragment mFrag = new PlantTypeCardFragment(plantType);
        t.replace(R.id.frame_layout, mFrag);
        t.commit();
    }

    public void replaceFragment2(){
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        PlantTypeListFragment mFrag = new PlantTypeListFragment();
        t.replace(R.id.frame_layout, mFrag);
        t.commit();
    }

    public void replaceFragmentHome(Long id){
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        FloristPlantsFragment mFrag = new FloristPlantsFragment(id);
        t.replace(R.id.home_frame_layout, mFrag);
        t.commit();
    }

    public void replaceFragmentRegistration(PlantTypeListRecordDTO plantType){
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        PlantRegistrationFragment mFrag = new PlantRegistrationFragment(plantType);
        t.replace(R.id.frame_layout, mFrag);
        t.commit();
    }

    public void replaceFragmentPlantCard(PlantListRecordDTO plant, Long id){
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        FloristPlantInfoFragment mFrag = new FloristPlantInfoFragment(plant, id);
        t.replace(R.id.home_frame_layout, mFrag);
        t.commit();
    }

    public void replaceFragmentEditFloristInfo(FloristDTO florist) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        EditFloristInfoFragment mFrag = new EditFloristInfoFragment(florist);
        t.replace(R.id.home_frame_layout, mFrag);
        t.commit();
    }

    public void replaceDateNotificationsFragment(Long floristId) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.notification_frame_layout, new DateNotificationFragment(floristId));
        t.commit();
    }

    public void replaceEditFloristPlantFragment(PlantDTO plant) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.home_frame_layout, new EditFloristPlantFragment(plant));
        t.commit();
    }
}