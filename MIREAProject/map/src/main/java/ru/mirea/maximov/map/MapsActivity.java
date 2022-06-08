package ru.mirea.maximov.map;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ru.mirea.maximov.map.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // добавление кнопки определения местоположения
        mMap.setMyLocationEnabled(true);
        // добавление кнопок изменнеия масштаба
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // отображение слоя загруженности дорог
        mMap.setTrafficEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng strominka = new LatLng(55.793553, 37.700853);
        LatLng vernandka = new LatLng(55.669895, 37.480481);
        LatLng mitht = new LatLng(55.661674, 37.477857);
        LatLng frunza = new LatLng(55.731743, 37.575395);
        mMap.addMarker(new MarkerOptions().position(strominka).title("Корпус на Сромынке, 20, год основания:1696; Координаты: 55°47'36.8\"N 37°42'03.1\"E"));
        mMap.addMarker(new MarkerOptions().position(vernandka).title("Корпус на Вернандского, 78, год основания:1985; Координаты: 55°40'11.1\"N 37°28'49.0\"E"));
        mMap.addMarker(new MarkerOptions().position(mitht).title("Корпус на Вернандского, 86, год основания: 2002; Координаты: 55°39'40.4\"N 37°28'40.6\"E "));
        mMap.addMarker(new MarkerOptions().position(frunza).title("Корпус на Малой Пироговской, 1с5,год основания: 1913; Координаты: 55°43'53.2\"N 37°34'30.7\"E "));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(strominka));
    }
}