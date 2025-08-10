package webservice_alegra.entities.vo.alegra;

/**
 *
 * @author Yohan Romero
 */
public class Alegra_StampVO {

    private boolean generateStamp;

    public Alegra_StampVO(boolean generateStamp) {
        this.generateStamp = generateStamp;
    }

    public boolean isGenerateStamp() {
        return generateStamp;
    }

    public void setGenerateStamp(boolean generateStamp) {
        this.generateStamp = generateStamp;
    }
}
