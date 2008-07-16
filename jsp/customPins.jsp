<% pageContext.setAttribute("pageTitle", "Ad Hoc Submit"); %>

<%@include file="header.jsp" %>

<table border="0" cellspacing="20">

  <tr>
    <td valign="top">

      <%@include file="menu.jsp" %>

    </td>

    <td bgcolor="#003366"/>
      &nbsp;
    </td>

    <td valign="top">

      <html:errors />

      <logic:messagesPresent message="true">
        <UL>
          <html:messages id="message" message="true">
            <LI><c:out value="${message}" /></LI>
          </html:messages>
        </UL>
      </logic:messagesPresent>

      <table cols='3' border='0' cellspacing='2' cellpadding='2'>
        <tr>
          <th bgcolor='lavender' align='left'>PIN</th>
          <th bgcolor='lavender' align='left'>Templates</th>
          <th bgcolor='lavender' align='left'>Parameters</th>
        </tr>

        <html:form action='customPins.do' name='customPinsForm' type='redbox.server.AdHocSubmitForm' >

          <html:hidden property='dispatch' value='error'/>

          <tr>
            <td><html:textarea property='pin' cols='100' rows='15' readonly='false' /></td>

            <td rowspan='3' valign='top'>
              <table>
                <td><td><select name="pins">
                        <option value="redbox">Redbox</option>
                        <option value="listrules">List Rules</option>
                        <option value="sql">SQL Query</option>
                        <option value="xmlql">xmlql</option>
                        <option value="xpath">XPath</option>
                        </select>
                        </td></tr>
                <tr><td><input type='button' value='REDBOX Status' style="width: 9em;" onclick="document.forms[0].pin.value=RedboxStatusTemplate;"/></td></tr>
                <tr><td><input type='button' value='List Rules'    style="width: 9em;" onclick="document.forms[0].pin.value=ListRulesTemplate;"/></td></tr>
                <tr><td><input type='button' value='SQL Query'     style="width: 9em;" onclick="document.forms[0].pin.value=SqlQueryTemplate;"/></td></tr>
                <tr><td><input type='button' value='XMLQL Query'   style="width: 9em;" onclick="document.forms[0].pin.value=XmlqlQueryTemplate;"/></td></tr>
                <tr><td><input type='button' value='XPATH Query'   style="width: 9em;" onclick="document.forms[0].pin.value=XpathQueryTemplate;"/></td></tr>
              </table>
            </td>

            <td rowspan='3' valign='top'>
              <table>
                <tr><td><b>Protocol</b></td><td>TCP</td></tr>
                <tr><td><b>IP Address</b></td><td>localhost</td></tr>
                <tr><td><b>Port</b></td><td>4444</td></tr>
              </table>
            </td>
          </tr>

          <tr>
            <th bgcolor='lavender' align='left' >Results</th>
          </tr>

          <tr>
            <td><html:textarea property='results' cols='100' rows='15' readonly='true' /></td>
          </tr>

          <tr>
            <script>
              function set(target) {
                document.forms[0].dispatch.value=target;
              }
            </script>
            <td colspan='3'>
              <table>
                <tr>
                  <td><html:submit onclick="set('submit');" style="width: 9em;">SUBMIT</html:submit></td>
		</tr>
	      </table>
	    </td>
	  </tr>

	</html:form>

      </table>

    </td>
  </tr>

</table>

<script>
  var XmlqlQueryTemplate=
    '<PIN>\n' +
    '  <HDT Version="1.0">\n' +
    '    <PINname>Query</PINname>\n' +
    '    <PINtype>XMLQL</PINtype>\n' +
    '  </HDT>\n' +
    '  <QDT>\n' +
    '    <Query language="xmlql" gcausage="false" >\n' +
    '      <![CDATA[\n' +
    '        function query () {\n' +
    '          where <!-- insert where clause here -->\n' +
    '          in <!-- insert collection name here -->\n' +
    '          construct <!-- insert construct clause here -->\n' +
    '        }\n' +
    '      ]]>\n' +
    '    </Query>\n' +
    '  </QDT>\n' +
    '</PIN>\n';

  var XpathQueryTemplate=
    '<PIN>\n' +
    '  <HDT Version="1.0">\n' +
    '    <PINname>Query</PINname>\n' +
    '    <PINtype>XMLQL</PINtype>\n' +
    '  </HDT>\n' +
    '  <QDT>\n' +
    '    <Query language="xpath" gcausage="false">\n' +
    '      <![CDATA[\n' +
    '        <!-- Insert XPATH statement here -->\n' +
    '      ]]>\n' +
    '    </Query>\n' +
    '  </QDT>\n' +
    '</PIN>\n';

  var SqlQueryTemplate=
    '<PIN>\n' +
    '  <HDT Version="1.0">\n' +
    '    <PINname>Query</PINname>\n' +
    '    <PINtype>MySQL</PINtype>\n' +
    '  </HDT>\n' +
    '  <QDT>\n' +
    '    <Query>\n' +
    '      <![CDATA[\n' +
    '        <!-- Insert SQL statement(s) here separated with semicolons \';\' -->\n' +
    '      ]]>\n' +
    '    </Query>\n' +
    '  </QDT>\n' +
    '</PIN>\n';

  var ListRulesTemplate=
    '<PIN>\n' +
    '  <HDT>\n' +
    '    <PINname>REDBOX_Administration</PINname>\n' +
    '    <PINtype>listRules</PINtype>\n' +
    '  </HDT>\n' +
    '</PIN>\n';

  var RedboxStatusTemplate=
    '<PIN>\n' +
    '  <HDT>\n' +
    '    <PINname>REDBOX_Administration</PINname>\n' +
    '    <PINtype>redboxStatus</PINtype>\n' +
    '  </HDT>\n' +
    '</PIN>\n';
</script>


<%@ include file="/jsp/footer.jsp" %>
