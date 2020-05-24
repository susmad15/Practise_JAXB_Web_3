package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Customer;
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


@WebServlet(name = "RetrieveCustomers", urlPatterns = {"/RetrieveCustomers"})
public class RetrieveCustomers extends HttpServlet {

    private DB_Access db = DB_Access.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String bodyText = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        
        List<Customer> customers = db.getCustomersOfRepresentative(bodyText);
        
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        String jsonString = gson.toJson(customers);
        
        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
        
        out.write(jsonString);
        
        out.flush();
        
    }


}
