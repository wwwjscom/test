 1 /*
 2  * AdHocSubmitAction.java
 3  */
 4 
 5 package redbox.server;
 6 
 7 import java.io.*;
 8 import java.util.*;
 9 import javax.servlet.*;
10 import javax.servlet.http.*;
11 
12 import org.apache.struts.action.*;
13 
14 import org.jdom.*;
15 import org.jdom.input.SAXBuilder;
16 import org.jdom.output.XMLOutputter;
17 import org.jdom.output.Format;
18 
19 import redbox.client.pinsend.*;
20 
21 public class AdHocSubmitAction extends Action {
22 
23   private static PinSend pinSend   = null;
24   private static SAXBuilder parser = null;
25 
26   private String      dispatch = null; // from form
27   private String      command  = null; // from action
28   private HttpSession session  = null;
29   private String pin = "lawl";
30 
31   private ArrayList list = null;
32 
33   public ActionForward execute(ActionMapping       mapping,
34                                ActionForm          form,
35                                HttpServletRequest  request,
36                                HttpServletResponse response) throws Exception {
37 
38     try {
39 
40       if (pinSend == null) {
41 
42         String parameters[] = {"-p", "tcp", "-i", "localhost", "-n", "4444", "-r", "true"};
43         pinSend = new PinSend(parameters);
44       }
45 
46       if (parser == null) {
47 
48         parser = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
49       }
50 
51       AdHocSubmitForm _form = (AdHocSubmitForm)form;
52       dispatch = _form.getDispatch();
53 
54       command = request.getParameter("command");
55       session = request.getSession(true);
56 
57       if (command != null) {
58 
59         if (command.equals("initialize")) {
60 
61           // nothing
62         }
63 
64         return(mapping.findForward("success"));
65       }
66       else {
67 
68         ActionMessages messages = new ActionMessages();
69 
70         if (dispatch.equals("submit")) {
71 
72           _form.setPins(request.getParameter("pins"));
73           _form.setResults(submitPin(_form));
74         }
75 
76         return(mapping.findForward("success"));
77       }
78     }
79     catch (Exception e) {
80 
81       e.printStackTrace();
82     }
83 
84     return (mapping.findForward("adhocsubmit"));
85   }
86
 87   private String submitPin(AdHocSubmitForm form) throws Exception {
 88       
 89     //pinSend.setString(form.getPin());
 90     pinSend.setString(form.getFormattedQuery());
 91       
 92     Vector results = pinSend.send();
 93       
 94     String result  = (String)results.get(0);
 95 
 96     Document document = parser.build(new StringReader(result));
 97     Element ele_results = document.getRootElement();
 98 
 99     XMLOutputter xmloutputter = new XMLOutputter(Format.getPrettyFormat());
100     String xmlout = xmloutputter.outputString(ele_results);
101 
102     //return(form.getFormattedQuery());
103     //return(form.getPin());
104     return(xmlout);
105   }
106 
107 }