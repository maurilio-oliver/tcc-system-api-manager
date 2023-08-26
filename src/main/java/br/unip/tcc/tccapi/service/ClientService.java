package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
        private List<Client> clients;

    public ClientService() {
        clients = new ArrayList<>();
    }

    public void cadastrarClient(Client client) {
        clients.add(client);
    }

    public void atualizarClient(int id, Client novoClient) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId() == id) {
                clients.set(i, novoClient);
                break;
            }
        }
    }

    public void removerClient(int id) {
        clients.removeIf(client -> client.getId() == id);
    }

    public Client buscarClientPorId(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    public List<Client> listarClients() {
        return new ArrayList<>(clients);
    }
}
