<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table  htmlId="recommendationResults" class="tableStyle" name="${recommendationUserResults}" id="user" >
<display:setProperty name="basic.msg.empty_list">No search results until now...</display:setProperty>
<display:column title="ResultNr."><c:out value="${user_rowNum}.  "/></display:column>
<display:column property="username" title="Name"></display:column>
<display:column property="userId" title="User ID"></display:column>
</display:table>