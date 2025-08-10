package webservice_alegra.entities.vo.alegra;

import java.util.ArrayList;
import webservice_alegra.util.Utils;

/**
 *
 * @author Yohan Romero
 */
public class Alegra_JournalVO {

    private int id;
    private String date;
    private String reference;
    private String observations;
    private ArrayList<Alegra_JournalEntryVO> entries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public ArrayList<Alegra_JournalEntryVO> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Alegra_JournalEntryVO> entries) {
        this.entries = entries;
    }

    public String toJson() {
        return Utils.convertObjectToJSON(this);
    }

}
