package es.uniovi.asw.presentation;

import es.uniovi.asw.bussiness.Factories;
import es.uniovi.asw.model.Referendum;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Ignacio Fernandez on 11/04/2016.
 */
@Component("beanConfigElection")
@Scope("application")

public class BeanConfigElection implements Serializable {


    private String selectedValue;
    private String[] values;


    private String electionName;
    private String initialDate;
    private String expireDate;
    private String instructions;
    private String question;
    private static boolean excelUploaded = false;
    private int numChoices;


    @PostConstruct
    void init() {

        System.out.println("BeanControllerElecion creado");
        //selectedValue="abierta";

    }


    public void println() {

        excelUploaded = false;


    }

    public boolean listasAbiertas() {

        return "abierta".equals(selectedValue);
    }

    public boolean referendum() {

        return "referendum".equals(selectedValue);
    }

    public boolean listasCerradas() {

        return "cerrada".equals(selectedValue);
    }


    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List<String> getValues() {
        return Arrays.asList("abierta", "cerrada", "referendum");
    }

    public void setValues(String[] values) {
        this.values = values;
    }


    public String creaCerradas() {


        System.out.println("al menos priqui entro");
        System.out.println("name " + electionName);
        System.out.println("date init" + initialDate);
        System.out.println("date expire" + expireDate);
        System.out.println("instructions " + instructions);
        System.out.println("numChoices" + numChoices);


        return "exito";
    }


    public String creaAbiertas() {


        System.out.println("al menos priqui entro");
        System.out.println("name " + electionName);
        System.out.println("date init" + initialDate);
        System.out.println("date expire" + expireDate);
        System.out.println("instructions " + instructions);


        return "exito";
    }


    public String creaReferendum() {

        System.out.println("I try");
        // 04/15/2016 11:25 AM
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        try {

            Date initialDated = formatter.parse(initialDate);
            Date expireDated = formatter.parse(expireDate);

            if (initialDated.after(expireDated)) {


                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                        "La fecha de inicio ha de ser antes que la de fin"));
                return "fallo";

            }

            Referendum referendum = new Referendum();
            referendum.setStartDate(initialDated);
            referendum.setExpiryDate(expireDated);
            referendum.setInstructions(instructions);
            referendum.setName(electionName);
            referendum.setQuestion(question);
            referendum.setNumChoices(1);


            boolean created = Factories.services.createElectionFactory().createReferendum(referendum);

            if (created) {

                System.out.println("creoo");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                        "Elecciones creadas con exito"));
                return "exito";
            }


        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                    "Revise el formato de sus datos"));
            return "fallo";
            //MENSAJE DE LOG
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                "No se ha podido crear el referendum, existen elecciones en ese plazo"));
        return "fallo";
    }


    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public static boolean isExcelUploaded() {
        return excelUploaded;
    }

    public static void setExcelUploaded(boolean excelUploaded) {
        BeanConfigElection.excelUploaded = excelUploaded;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNumChoices() {
        return numChoices;
    }

    public void setNumChoices(int numChoices) {
        this.numChoices = numChoices;
    }
}
