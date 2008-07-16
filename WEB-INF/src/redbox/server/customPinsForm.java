/*
 * customPinsForm.java
 */

package redbox.server;

import org.apache.struts.action.*;

public class customPinsForm extends ActionForm {

  private String dispatch = null;
  private String pin      = null;
  private String pins     = null;//DEBUG
  private String results  = null;

  public void setDispatch(String dispatch) {

    this.dispatch = dispatch;
  }

  public String getDispatch() {

    return(dispatch);
  }

  public void setPin(String pin) {
     this.pin = pin;
  }

  public String getPin() {

    return(pin);
  }

  public void setResults(String results) {

    this.results = results;
  }

  public String getResults() {

    return(results);
  }
  public ActionErrors validate(ActionMapping mapping, javax.servlet.http.HttpServletRequest request) {

    ActionErrors errors = new ActionErrors();

    String command = request.getParameter("command");

    if ((command != null) && (command.equals("initialize"))) {

      reset();

      return(errors);
    }
    else {

      // check for null pin value
    }

    return(errors);
  }

  public void reset() {

    setDispatch(null);
    setPin(null);
    setResults(null);
  }

  /* <Added by soo@IIT> */
  public String getFormattedQuery()
  {
          String q = null;
          if(pins.equals("redbox"))
          {

                  q = "<PIN>"
                            + "<HDT>"
                                + "<PINname>REDBOX_Administration</PINname>"
                                    + "<PINtype>redboxStatus</PINtype>"
                              + "</HDT>"
+ getPin()
                      + "</PIN>";

          } else if (pins.equals("listrules")) {

                q = "listrules";

          } else if (pins.equals("sql")) {

                q = getPin();

          } else if (pins.equals("xmlql")) {

                q = "<PIN>"
                + "<HDT Version=\"1.0\">"
		+ "<PINname>Query</PINname>"
                + "<PINtype>XMLQL</PINtype>"
                + "</HDT>"
                + "<QDT>"
                        + "<Query description='TV Transmitter Call Signs as XML'"
                        + "explain='false'"
                        + "gcausage='false'"
                        + "language='xmlql'"
                        + "joinType='1'"
                        + "numcols='1'"
                        + "tlevel='4'"
                        + "mimetype='text/xml'"
                        + "preservewhitespace='true'"
                        + "cdata='false' >"
                + "<![CDATA[ "
                + getPin()
                + "]]>"
                + "</Query>"
                + "</QDT></PIN>";

          } else if (pins.equals("xpath")) {

                q = "<PIN>  <HDT Version=\"1.0\">    <PINname>Query</PINname>    <PINtype>XMLQL</PINtype>  </HDT>  <QDT>    <Query language=\"xpath\" gcausage=\"false\"         >      <![CDATA[ " + getPin() + "      ]]>    </Query>  </QDT></PIN>";
		q += "XPATH";
          }

          return q;
  }

  public void setPins(String pins)
  {
          this.pins = pins;
  }

  public String getPins()
  {
          return pins;
  }
  /* </Added by soo@IIT> */
}
