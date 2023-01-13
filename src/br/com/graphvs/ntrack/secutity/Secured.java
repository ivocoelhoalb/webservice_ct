package br.com.graphvs.ntrack.secutity;


import java.lang.annotation.*;
import javax.ws.rs.NameBinding;

@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured { }