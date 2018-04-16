package com.manuel.canul.productos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Productos> ProductosList;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RelativeLayout coordinatorLayout;
    //Volley Request Queue
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerProductos);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //TODO INICIALIZAMOS NUESTRA LISTA DE TIPS
        ProductosList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        //TODO LLAMAMOS LA METODO QUE REALIZA LA BAJADA DE DATOS Y LOS COLOCA EN EL RECYCLER VIEW
        getData();

        //TODO INICIALIZAMOS  EL ADATADOR
        adapter = new ProductosAdapter(ProductosList, this);

        //TODO AÑADIMOS EL ADATER AL RECYCLER VIEW
        recyclerView.setAdapter(adapter);
    }

    /*
     *TODO PROCESAMOS LA PETICION HTTP Y CAPTURAMOS LOS ELEMENTOS JSON RECIBIDOS
     *
     **/
    private JsonArrayRequest getDataFromServer() {
        //TODO REALIZAMOS LA LECTURA DEL JSONARRAY CON VOLLEY
        String urlGetProductos = Config.DATA_URL + "baja.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlGetProductos,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //TODO LLAMAMOS AL METODO PARSEDATA SI HUBO CAPTURA DE DATOS
                        parseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO SI HAY ERROR OCULTAMOS EL PROGRESS BAR Y EL TEXTO DE CARGA Y MOSTRAMOS EL CONTENEDOR QUE MARCA ERROR
                        //TODO Y EL BOTON PARA RECARGAR
                        lanzarError();
                    }
                });
        //TODO RETORNAMOS LO CAPTURADO SEA ERROR O DATOS
        return jsonArrayRequest;
    }

    //TODO ESTE METODO ES EL QUE HACE QUE BAJEMOS LOS DATOS DE LA RED
    private void getData() {
        //TODO AÑADIENDO AL  queue LLAMANDO AL METODO getDataFromServer
        requestQueue.add(getDataFromServer());
    }

    //TODO ESTE METODO PASA LOS DATOS A SUS GET Y SET CORRESPONDIENTES PARA SU USO
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            //TODO CREAMOS EL OBJETO TIP
            Productos productos = new Productos();
            JSONObject json = null;
            try {
                //TODO OBTENEMOS EL JSON
                json = array.getJSONObject(i);

                //TODO AÑADIMOS DATOS AL OBJETO TIP
                productos.setId_producto(json.getInt(Config.TAG_ID_PRODUCTO));
                productos.setNombre_producto(json.getString(Config.TAG_NOMBRE_PRODUCTO));
                productos.setDescripcion_producto(json.getString(Config.TAG_DESCRIPCION_PRODUCTO));
                productos.setPrecio_producto(json.getString(Config.TAG_PRECIO_PRODUCTO));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //TODO AÑADIMOS EL OBJETO TIP A LA LISTA TIP
            ProductosList.add(productos);
        }

        //TODO NOTIFICAMOS AL ADAPTADOR PARA QUE HAGA CAMBIOS
        adapter.notifyDataSetChanged();
    }
    private void lanzarError(){
        Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
    }
}
