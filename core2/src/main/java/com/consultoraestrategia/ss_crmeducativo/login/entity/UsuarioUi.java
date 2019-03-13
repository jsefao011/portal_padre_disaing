package com.consultoraestrategia.ss_crmeducativo.login.entity;

public class UsuarioUi {
    private  int usuarioId;
    private  int personaId;
    private  String userName;
    private  String passwordEncrypted;
    private String personaImagenUrl;
    private String institucionUrl;


    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    @Override
    public String toString() {
        return "UsuarioUi{" +
                "usuarioId=" + usuarioId +
                ", personaId=" + personaId +
                ", userName='" + userName + '\'' +
                ", passwordEncrypted='" + passwordEncrypted + '\'' +
                '}';
    }

    public void setPersonaImagenUrl(String personaImagenUrl) {
        this.personaImagenUrl = personaImagenUrl;
    }

    public String getPersonaImagenUrl() {
        return personaImagenUrl;
    }

    public void setInstitucionUrl(String institucionUrl) {
        this.institucionUrl = institucionUrl;
    }

    public String getInstitucionUrl() {
        return institucionUrl;
    }
}
