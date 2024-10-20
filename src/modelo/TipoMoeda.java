package modelo;

public enum TipoMoeda {

    DOLAR("USD"),
    PESO_ARGENTINO("ARS"),
    PESO_COLOMBIANO("COP"),
    REAL_BRASILEIRO("BRL");

    private final String codigo;

    TipoMoeda(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

}
