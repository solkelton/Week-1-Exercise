package data;

public enum DataType {
    SYMBOL("symbol"), PRICE("price"), VOLUME("volume"), DATE("date");

    private String dType;
    DataType(String dType) {
        this.dType = dType;
    }

    public String getDType() {
        return dType;
    }
}
