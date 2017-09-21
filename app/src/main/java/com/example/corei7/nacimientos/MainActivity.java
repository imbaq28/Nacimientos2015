package com.example.corei7.nacimientos;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.corei7.nacimientos.adapters.NacimientosAdapter;
import com.example.corei7.nacimientos.model.DatosResponse;
import com.example.corei7.nacimientos.model.Nacimientos;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements NacimientosAdapter.OnNacimientoSelectedListener {

    private RecyclerView NacimientosRecyclerView;
    private NacimientosAdapter NacimientosAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NacimientosRecyclerView = (RecyclerView) findViewById(R.id.NacimientosRecyclerView);
        NacimientosRecyclerView.setHasFixedSize(true);
        NacimientosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        NacimientosAdapter = new NacimientosAdapter(this, this);

        NacimientosRecyclerView.setAdapter(NacimientosAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarDatos();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        swipeRefreshLayout.setRefreshing(true);
        cargarDatos();
    }

    private void cargarDatos() {
        DatosGobBoService service = ServiceGenerator.createService(DatosGobBoService.class);
        Call<DatosResponse> call = service.Nacimientos("ff2b39ca-c4b5-4d82-b84a-be3e8aacf434", "cbba");

        call.enqueue(new Callback<DatosResponse>() {
            @Override
            public void onResponse(Call<DatosResponse> call, Response<DatosResponse> response) {
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful()) {
                    NacimientosAdapter.setDataset(response.body().getResult().getRecords());
                } else {
                    Log.e("MIAPP ", "No se puede obtener los departamentos");
                }
            }

            @Override
            public void onFailure(Call<DatosResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.e("MIAPP", "Error obteniendo departamentos: " + t.getMessage());
            }
        });
    }



    @Override
    public void onNacimientoSelected(Nacimientos departamento) {

    }
}
