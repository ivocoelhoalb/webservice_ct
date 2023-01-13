package br.com.graphvs.ntrack.secutity;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("br.com.graphvs.ntrack");
      //  register(AuthenticationFilter.class);
    }
}