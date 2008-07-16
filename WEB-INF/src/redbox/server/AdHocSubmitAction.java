/*
 * AdHocSubmitAction.java
 */

package redbox.server;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;

import redbox.client.pinsend.*;

public class AdHocSubmitAction extends Action {

  private static PinSend pinSend   = null;
  private static SAXBuilder parser = null;

  private String      dispatch = null; // from form
  private String      command  = null; // from action
  private HttpSession session  = null;
  private String pin = "lawl";

  private ArrayList list = null;

  public ActionForward execute(ActionMapping       mapping,
                               ActionForm          form,
                               HttpServletRequest  request,
                               HttpServletResponse response) throws Exception {

    try {

      if (pinSend == null) {

        String parameters[] = {"-p", "tcp", "-i", "localhost", "-n", "4444", "-r", "true"};
        pinSend = new PinSend(parameters);
      }

      if (parser == null) {

        parser = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
      }

      AdHocSubmitForm _form = (AdHocSubmitForm)form;
      dispatch = _form.getDispatch();

      command = request.getParameter("command");
      session = request.getSession(true);

      if (command != null) {

        if (command.equals("initialize")) {

          // nothing
        }

        return(mapping.findForward("success"));
      }
      else {

        ActionMessages messages = new ActionMessages();

        if (dispatch.equals("submit")) {

          _form.setPins(request.getParameter("pins"));
          _form.setResults(submitPin(_form));
        }

        return(mapping.findForward("success"));
      }
    }
    catch (Exception e) {

      e.printStackTrace();
    }

    return (mapping.findForward("adhocsubmit"));
  }

  private String submitPin(AdHocSubmitForm form) throws Exception {

    //pinSend.setString(form.getPin());
    pinSend.setString(form.getFormattedQuery());

    Vector results = pinSend.send();

    String result  = (String)results.get(0);

    Document document = parser.build(new StringReader(result));
    Element ele_results = document.getRootElement();

    XMLOutputter xmloutputter = new XMLOutputter(Format.getPrettyFormat());
    String xmlout = xmloutputter.outputString(ele_results);

    //return(form.getFormattedQuery());
    //return(form.getPin());
    return(xmlout);
  }

}
