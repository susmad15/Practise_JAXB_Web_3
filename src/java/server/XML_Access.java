package server;

import data.Country;
import data.Customer;
import data.DataHolder;
import data.Representative;
import java.io.File;
import javax.xml.bind.JAXB;

public class XML_Access {

    private static XML_Access theInstance;
    
    private String path;

    private XML_Access() {
        
        path = "/home/markus/Dokumente/HTL/5DHIF/POS/MaturaTest/Practise_JAXB_Web/res/countries.xml";
    }

    public static XML_Access getInstance() {
        if (theInstance == null) {
            theInstance = new XML_Access();
        }

        return theInstance;
    }

    public DataHolder loadLocations() {
        
        File file = new File(path);
        
        if(!file.exists()){
            System.out.println("Die Datei existiert nicht!!!!");
        }
        
        DataHolder holder = JAXB.unmarshal(file, DataHolder.class);
        
        for(Country c : holder.getCountries()){
            for(Representative rep : c.getRepresentatives()) {
                rep.setCountry(c);
                
                for(Customer cust : rep.getCustomers()) {
                    cust.setRepresentative(rep);
                }
            }
        }
        
        return holder;
        
    }
    
    public static void main(String[] args) {
        XML_Access xml = XML_Access.getInstance();
        
        xml.loadLocations();
    }

}
