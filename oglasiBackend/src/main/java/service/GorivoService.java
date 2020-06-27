package service;

import lombok.AllArgsConstructor;
import modeli.Gorivo;
import org.springframework.stereotype.Service;
import repozitorijumi.GorivoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GorivoService {
    private final GorivoRepository gorivoRepository;

    public List<Gorivo> vratiGoriva() {
        List<Gorivo> goriva = gorivoRepository.findAll();
        return goriva;
    }
}
