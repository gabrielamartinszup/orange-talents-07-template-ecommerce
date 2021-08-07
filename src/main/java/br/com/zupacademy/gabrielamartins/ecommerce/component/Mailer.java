package br.com.zupacademy.gabrielamartins.ecommerce.component;

public interface Mailer {

    void enviar(String body, String subject, String nameFrom, String from, String to);
}
