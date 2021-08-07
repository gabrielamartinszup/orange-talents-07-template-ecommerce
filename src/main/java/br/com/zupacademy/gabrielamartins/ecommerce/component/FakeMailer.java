package br.com.zupacademy.gabrielamartins.ecommerce.component;

import br.com.zupacademy.gabrielamartins.ecommerce.component.Mailer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeMailer implements Mailer {
    @Override
    public void enviar(String body, String subject, String nameFrom, String from, String to) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(nameFrom);
        System.out.println(from);
        System.out.println(to);

    }
}
