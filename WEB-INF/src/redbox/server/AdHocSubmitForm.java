/*
 * AdHocSubmitForm.java
 */

package redbox.server;

import org.apache.struts.action.*;

public class AdHocSubmitForm extends ActionForm {

  private String dispatch = null;
  private String pin      = null;
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
}
