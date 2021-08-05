package br.com.zupacademy.gabrielamartins.ecommerce.config;

public interface Mailer {

    void enviar(String body, String subject, String nameFrom, String from, String to);
}
