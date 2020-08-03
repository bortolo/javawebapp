package org.mycompany.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Date;

import org.mycompany.jcg.ManagePostgreSql;
import org.mycompany.models.Actor;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/InsertActor")
public class InsertActor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InsertActor() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        System.out.println("Trying connection to PostgreSQL server.");
        ManagePostgreSql myObj = new ManagePostgreSql();
        //myObj.setVariables();
        Actor actor = new Actor("John","Doe");
        try {

        long id = myObj.insertActor(actor);
        out.print(String.format("%s,%s actor has been inserted with id %d",actor.getFirstName(), actor.getLastName(), id));

        }  catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            out.print(e.getMessage());
        }
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
