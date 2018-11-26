package com.example.ramir.studentapp.client;

import com.example.ramir.studentapp.map.Graph;
import com.example.ramir.studentapp.map.MapGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RestClient {

    private static ObjectMapper mapper = new ObjectMapper();

    private static final String REST_URI = "http://192.168.1.12:9080/CarpoolingServer_war/";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(REST_URI)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private CarpoolingService service = retrofit.create(CarpoolingService.class);


    public Graph getGraph() {
        Call<Graph> msg = service.getMap();
        String graph = null;
        //Log.i("Graph", graph.getVertices().toString());

        /**
        try {
            map = mapper.readValue(message, Graph.class);
        } catch (IOException e) {
            e.printStackTrace();
        }**/
        return MapGenerator.generateGraph(30);
    }
}
