package com.consultoraestrategia.ss_crmeducativo.portal.main.data.sourse.local;

import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.portal.main.data.sourse.MainDataSource;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.HijoUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.PadreMentorUi;
import com.consultoraestrategia.ss_crmeducativo.portal.main.entities.ProgramaEducativoUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class MainLocaDataSource implements MainDataSource {
    @Override
    public List<ProgramaEducativoUi> onGetProgramaEducativo(int alumnoId) {

        List<ProgramasEducativo> programasEducativos = SQLite.select()
                .from(ProgramasEducativo.class)
                .innerJoin(NivelAcademico.class)
                .on(ProgramasEducativo_Table.nivelAcadId.withTable()
                        .eq(NivelAcademico_Table.nivelAcadId.withTable()))
                .innerJoin(DetalleContratoAcad.class)
                .on(NivelAcademico_Table.nivelAcadId.withTable()
                        .eq(DetalleContratoAcad_Table.idNivelAcademico.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(DetalleContratoAcad_Table.anioAcademicoId.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .innerJoin(Contrato.class)
                .on(Contrato_Table.idContrato.withTable()
                        .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                .where(AnioAcademico_Table.estadoId.withTable().eq(AnioAcademico.ANIO_ACADEMICO_ACTIVO))
                .and(Contrato_Table.personaId.withTable().eq(alumnoId))
                .queryList();
        List<ProgramaEducativoUi> programaEducativoUiList = new ArrayList<>();
        int count = 0;
        for (ProgramasEducativo programasEducativo: programasEducativos){
            ProgramaEducativoUi programaEducativoUi = new ProgramaEducativoUi();
            programaEducativoUi.setId(programasEducativo.getProgramaEduId());
            programaEducativoUi.setNombre(programasEducativo.getNombre());
            programaEducativoUi.setSelected(count==0);
            count ++;
            programaEducativoUiList.add(programaEducativoUi);
        }


        return programaEducativoUiList;
    }

    @Override
    public PadreMentorUi getPadreMentor(int usuarioId) {
        PadreMentorUi padreMentorUi = new PadreMentorUi();
        padreMentorUi.setUsuarioId(usuarioId);
        Persona persona = SQLite.select()
                .from(Persona.class)
                .innerJoin(Usuario.class)
                .on(Persona_Table.personaId.withTable()
                        .eq(Usuario_Table.personaId.withTable()))
                .where(Usuario_Table.usuarioId.withTable().eq(usuarioId))
                .querySingle();
        List<HijoUi> hijoUiList = new ArrayList<>();
        if(persona!=null){
            padreMentorUi.setPersonaId(persona.getPersonaId());
            padreMentorUi.setNombre(persona.getFirstName());
            padreMentorUi.setNombres(persona.getNombres());
            padreMentorUi.setApellidos(persona.getNombres());
            padreMentorUi.setUrl_imagen(persona.getFoto());
            char etiqueta = persona.getNombres() == null || persona.getNombres().length() == 0 ? 'V': persona.getNombres().charAt(0);
            padreMentorUi.setEtiqueta(etiqueta);



            List<Persona> personaList = SQLite.select()
                    .from(Persona.class)
                    .innerJoin(Relaciones.class)
                    .on(Persona_Table.personaId.withTable()
                            .eq(Relaciones_Table.personaPrincipalId.withTable()))
                    .where(Relaciones_Table.personaVinculadaId.withTable().eq(persona.getPersonaId()))
                    .queryList();

            int count = 0;
            for (Persona hijopersona: personaList){
                HijoUi hijoUi = new HijoUi();

                hijoUi.setPersonaId(hijopersona.getPersonaId());
                hijoUi.setNombre(hijopersona.getFirstName());
                hijoUi.setNombres(hijopersona.getNombres());
                hijoUi.setApellidos(hijopersona.getNombres());
                hijoUi.setUrl_imagen(hijopersona.getFoto());
                char etiqueta2 = hijopersona.getNombres() == null || hijopersona.getNombres().length() == 0 ? 'V': hijopersona.getNombres().charAt(0);
                hijoUi.setEtiqueta(etiqueta2);
                hijoUi.setSelected(count == 0);

                Usuario usuarioHijo = SQLite.select()
                        .from(Usuario.class)
                            .where(Usuario_Table.personaId.eq(hijopersona.getPersonaId()))
                        .querySingle();

                if(usuarioHijo!=null)hijoUi.setUsuarioId(usuarioHijo.getUsuarioId());

                hijoUiList.add(hijoUi);
                count++;
            }

            padreMentorUi.setHijoUiList(hijoUiList);

        }




        return padreMentorUi;
    }
}
