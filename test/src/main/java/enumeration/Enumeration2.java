package enumeration;

public enum Enumeration2 {
    GIRL("girl"),
    BOY("boy"),
    OTHER("other");

    String value;

    Enumeration2(String value){
        this.value = value;
    }

    String getValue(){
        return value;
    }
}
