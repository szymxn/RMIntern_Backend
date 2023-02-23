package at.spengergasse.rmbackend.service;

import at.spengergasse.rmbackend.foundation.NanoIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final NanoIdFactory nanoIdFactory;
    public final int DEFAULT_NANO_ID_LENGTH = 8;

    public String createNanoId(){
        return createNanoId(DEFAULT_NANO_ID_LENGTH);
    }

    public String createNanoId(int size){
        return nanoIdFactory.randomNanoId(size);
    }
}
