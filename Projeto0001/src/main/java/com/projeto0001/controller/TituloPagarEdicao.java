package com.projeto0001.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

// anotação para produzir um titulo que está editando.
// server para o programa EmissaoTitulo enxergar o CadastroTituloPagarBean. 
// há uma anotacao em cada um dos arquivos.

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
public @interface TituloPagarEdicao {

}
