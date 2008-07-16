 1 /*
 2  * AdHocSubmitForm.java
 3  */
 4 
 5 package redbox.server;
 6 
 7 import org.apache.struts.action.*;
 8 
 9 public class AdHocSubmitForm extends ActionForm {
10 
11   private String dispatch = null;
12   private String pin      = null;
13   private String pins     = null;//DEBUG
14   private String results  = null;
15 
16   public void setDispatch(String dispatch) {
17 
18     this.dispatch = dispatch;
19   }
20 
21   public String getDispatch() {
22 
23     return(dispatch);
24   }
25 
26   public void setPin(String pin) {
27      28     this.pin = pin;
29   }
30 
31   public String getPin() {
32 
33     return(pin);
34   }
35 
36   public void setResults(String results) {
37 
38     this.results = results;
39   }
40 
41   public String getResults() {
42 
43     return(results);
44   }
46   public ActionErrors validate(ActionMapping mapping, javax.servlet.http.HttpServletRequest request) {
47 
48     ActionErrors errors = new ActionErrors();
49 
50     String command = request.getParameter("command");
51 
52     if ((command != null) && (command.equals("initialize"))) {
53 
54       reset();
55 
56       return(errors);
57     }
58     else {
59 
60       // check for null pin value          
61     }
62 
63     return(errors);
64   }
65 
66   public void reset() {
67 
68     setDispatch(null);
69     setPin(null);
70     setResults(null);
71   }
72 
73   /* <Added by soo@IIT> */
74   public String getFormattedQuery()
75   {
76           String q = null;
77           if(pins.equals("redbox"))
78           {
79 
80                   q = "<PIN>"
81                             + "<HDT>"
82                                 + "<PINname>REDBOX_Administration</PINname>"
83                                     + "<PINtype>redboxStatus</PINtype>"
84                               + "</HDT>"
85 + getPin()
86                       + "</PIN>";
87 
88           } else if (pins.equals("listrules")) {
89
     89 
     90                 q = "listrules";
     91 
     92           } else if (pins.equals("sql")) {
     93 
     94                 q = getPin();
     95 
     96           } else if (pins.equals("xmlql")) {
     97 
     98                 q = "<PIN>"
     99                 + "<HDT Version=\"1.0\">"    
    100                 + "<PINname>Query</PINname>"
    101                 + "<PINtype>XMLQL</PINtype>"
    102                 + "</HDT>"
    103                 + "<QDT>"
    104                         + "<Query description='TV Transmitter Call Signs as XML'"
    105                         + "explain='false'"
    106                         + "gcausage='false'"
    107                         + "language='xmlql'"
    108                         + "joinType='1'"
    109                         + "numcols='1'"
    110                         + "tlevel='4'"
    111                         + "mimetype='text/xml'"
    112                         + "preservewhitespace='true'"
    113                         + "cdata='false' >"
    114                 + "<![CDATA[ "
    115                 + getPin()
    116                 + "]]>"
    117                 + "</Query>"
    118                 + "</QDT></PIN>";
    119 
    120           } else if (pins.equals("xpath")) {
    121 
    122                 q = "<PIN>  <HDT Version=\"1.0\">    <PINname>Query</PINname>    <PINtype>XMLQL</PINtype>  </HDT>  <QDT>    <Query language=\"xpath\" gcausage=\"false\"         >      <![CDATA[ " + getPin() + "      ]]>    </Query>  </QDT></PIN>";
    123           }
    124 
    125           return q;
    126   }
    127 
    128   public void setPins(String pins)
    129   {
    130           this.pins = pins;
    131   }
    132 
    133   public String getPins()
    134   {
    135           return pins;
    136   }
    137   /* </Added by soo@IIT> */
    138 }