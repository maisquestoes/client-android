package progamaro.maisquestoes_v2.dto;

/**
 * Created by andremiranda on 31/07/15.
 */
public class JsonBaseDTO<T> {
    public String message;
    public boolean success;
    public T object;

    public JsonBaseDTO() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
