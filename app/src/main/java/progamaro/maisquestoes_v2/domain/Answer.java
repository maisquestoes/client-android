package progamaro.maisquestoes_v2.domain;

/**
 * Created by helio on 25/07/15.
 */
public class Answer {

    private String id;
    private String description;
    private boolean isCorrect;
    private String imagePath;

    public Answer(String id, String description, boolean isCorrect, String pathImage) {
        this.id = id;
        this.description = description;
        this.isCorrect = isCorrect;
        this.imagePath = pathImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
