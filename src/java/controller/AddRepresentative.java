package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

@WebServlet(name = "AddRepresentative", urlPatterns = {"/AddRepresentative"})
public class AddRepresentative extends HttpServlet {

    private DB_Access db = DB_Access.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedCountry = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();

        db.addRepresentative(selectedCountry);
        
        List<Representative> reps = db.getRepresentativesOfCountry(selectedCountry);
        
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        String jsonString = gson.toJson(reps);
        
        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
        
        out.write(jsonString);
        
        out.flush();
        
        
    }


}
