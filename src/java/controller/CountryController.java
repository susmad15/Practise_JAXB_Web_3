package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Country;
import data.Representative;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.DB_Access;

@WebServlet(name = "CountryController", urlPatterns = {"/CountryController"})
public class CountryController extends HttpServlet {

    private DB_Access db;

    @Override
    public void init() throws ServletException {
        super.init();

        db = DB_Access.getInstance();

        db.persistDataHolder();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Country> countries = db.getAllCountries();

        request.setAttribute("countries", countries);

        request.getRequestDispatcher("view.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String bodyText = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        
        List<Representative> reps = db.getRepresentativesOfCountry(bodyText);
        
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        String jsonString = gson.toJson(reps);
        
        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
        
        out.write(jsonString);
        
        out.flush();

    }

}
