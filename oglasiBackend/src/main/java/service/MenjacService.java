package service;

import lombok.AllArgsConstructor;
import modeli.Menjac;
import org.springframework.stereotype.Service;
import repozitorijumi.MenjacRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MenjacService {
    private final MenjacRepository menjacRepository;

    public List<Menjac> vratiMenjace() {
        List<Menjac> menjaci = menjacRepository.findAll();
        return menjaci;
    }
}
