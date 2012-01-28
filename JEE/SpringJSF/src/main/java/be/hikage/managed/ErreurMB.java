package be.hikage.managed;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import javax.faces.event.ActionEvent;

import be.hikage.exeption.DirectException;

/**
 * @Autor : Hikage
 */
@Component
public class ErreurMB {

    public void lanceErreur(ActionEvent e){
        throw new RuntimeException("test");
    }

    public void lanceErreurDirect(ActionEvent e) throws DirectException {
        throw new DirectException();
    }
}
