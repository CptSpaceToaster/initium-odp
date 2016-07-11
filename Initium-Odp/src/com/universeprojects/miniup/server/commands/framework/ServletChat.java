package com.universeprojects.miniup.server.commands.framework;

import com.universeprojects.cacheddatastore.CachedEntity;
import com.universeprojects.miniup.server.ODPDBAccess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletChat extends HttpServlet
{
    private ODPDBAccess db;

    public void init() throws ServletException
    {
        db = getDB();
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
                       throws ServletException
    {
        String username = request.getParameter("user_name");
        String message = request.getParameter("text");

        CachedEntity character = db.getCharacterByName(username);

        if (character == null)
        {
            // TODO: SendGlobalChat(null, "/me " + username + " " + message);
            // or something
        }
        else
        {
            // TODO: SendGlobalChat(character, message);
            // or something
        }
        response.setStatus(response.SC_OK);
    }

    public void destroy()
    {
        // do nothing.
    }
}
