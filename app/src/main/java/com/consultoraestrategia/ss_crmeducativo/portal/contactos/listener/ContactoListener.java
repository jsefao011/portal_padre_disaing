package com.consultoraestrategia.ss_crmeducativo.portal.contactos.listener;

import com.consultoraestrategia.ss_crmeducativo.portal.contactos.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.PadreMentorUi;

public interface ContactoListener {

    void callPerson(String number);

    void sendMessage(String number);

    void settings(PersonaUi personaUi);
}
