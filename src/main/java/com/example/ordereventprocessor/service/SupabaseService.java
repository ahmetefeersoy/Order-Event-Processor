package com.example.ordereventprocessor.service;

import com.example.ordereventprocessor.config.SupabaseConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class SupabaseService {

    private final RestTemplate supabaseRestTemplate;
    private final SupabaseConfig supabaseConfig;

    public SupabaseService(RestTemplate supabaseRestTemplate, SupabaseConfig supabaseConfig) {
        this.supabaseRestTemplate = supabaseRestTemplate;
        this.supabaseConfig = supabaseConfig;
    }

    /**
     * Example: Insert data into a Supabase table using REST API
     * 
     * @param tableName The name of your Supabase table
     * @param data The data to insert (as JSON string or object)
     * @return Response from Supabase
     */
    public <T> ResponseEntity<String> insertData(String tableName, T data) {
        String url = supabaseConfig.getSupabaseUrl() + "/rest/v1/" + tableName;
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", supabaseConfig.getSupabaseApiKey());
        headers.set("Authorization", "Bearer " + supabaseConfig.getSupabaseApiKey());
        headers.set("Content-Type", "application/json");
        headers.set("Prefer", "return=representation");
        
        HttpEntity<T> request = new HttpEntity<>(data, headers);
        
        return supabaseRestTemplate.postForEntity(url, request, String.class);
    }

    /**
     * Example: Query data from a Supabase table
     * 
     * @param tableName The name of your Supabase table
     * @param query Optional query parameters (e.g., "?id=eq.1")
     * @return Response from Supabase
     */
    public ResponseEntity<String> queryData(String tableName, String query) {
        String url = supabaseConfig.getSupabaseUrl() + "/rest/v1/" + tableName;
        if (query != null && !query.isEmpty()) {
            url += query;
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", supabaseConfig.getSupabaseApiKey());
        headers.set("Authorization", "Bearer " + supabaseConfig.getSupabaseApiKey());
        
        HttpEntity<String> request = new HttpEntity<>(headers);
        
        return supabaseRestTemplate.exchange(url, HttpMethod.GET, request, String.class);
    }

    /**
     * Example: Update data in a Supabase table
     * 
     * @param tableName The name of your Supabase table
     * @param query Query to identify records (e.g., "?id=eq.1")
     * @param data The data to update
     * @return Response from Supabase
     */
    public <T> ResponseEntity<String> updateData(String tableName, String query, T data) {
        String url = supabaseConfig.getSupabaseUrl() + "/rest/v1/" + tableName + query;
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", supabaseConfig.getSupabaseApiKey());
        headers.set("Authorization", "Bearer " + supabaseConfig.getSupabaseApiKey());
        headers.set("Content-Type", "application/json");
        headers.set("Prefer", "return=representation");
        
        HttpEntity<T> request = new HttpEntity<>(data, headers);
        
        return supabaseRestTemplate.exchange(url, HttpMethod.PATCH, request, String.class);
    }

    /**
     * Example: Delete data from a Supabase table
     * 
     * @param tableName The name of your Supabase table
     * @param query Query to identify records to delete (e.g., "?id=eq.1")
     * @return Response from Supabase
     */
    public ResponseEntity<String> deleteData(String tableName, String query) {
        String url = supabaseConfig.getSupabaseUrl() + "/rest/v1/" + tableName + query;
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", supabaseConfig.getSupabaseApiKey());
        headers.set("Authorization", "Bearer " + supabaseConfig.getSupabaseApiKey());
        
        HttpEntity<String> request = new HttpEntity<>(headers);
        
        return supabaseRestTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
    }
}
