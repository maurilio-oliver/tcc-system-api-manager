package br.unip.tcc.tccapi;

import br.unip.tcc.tccapi.model.Client;
import br.unip.tcc.tccapi.model.Seller;
import br.unip.tcc.tccapi.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class TccApiApplication {

    public static void main(String[] args) {
        Client test = new Client();
        User test2 = new Seller();

        //SpringApplication.run(TccApiApplication.class, args);
    }

}
