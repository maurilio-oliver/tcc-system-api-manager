package br.unip.tcc.tccapi.service;

import br.unip.tcc.tccapi.model.RequestHIstory;
import br.unip.tcc.tccapi.repository.RequestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestHistoryService {
    @Autowired
    private RequestHistoryRepository requestHistoryRepository;


    public void saveState(final RequestHIstory requestHIstory){
        this.requestHistoryRepository.save(requestHIstory);
    }
}
