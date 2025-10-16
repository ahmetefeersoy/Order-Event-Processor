package com.example.ordereventprocessor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SupabaseConfig {

    @Value("${supabase.url:}")
    private String supabaseUrl;

    @Value("${supabase.api.key:}")
    private String supabaseApiKey;

    @Value("${supabase.service.key:}")
    private String supabaseServiceKey;

    

    public String getSupabaseUrl() {
        return supabaseUrl;
    }

    public String getSupabaseApiKey() {
        return supabaseApiKey;
    }

    public String getSupabaseServiceKey() {
        return supabaseServiceKey;
    }
}
