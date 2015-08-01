package progamaro.maisquestoes_v2.dto;

/**
 * Created by andremiranda on 31/07/15.
 */
public class JsonBaseDTO<T> {
    public String m;
    public String s;
    public T o;

    public JsonBaseDTO() {
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String isS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public T getO() {
        return o;
    }

    public void setO(T o) {
        this.o = o;
    }
}
