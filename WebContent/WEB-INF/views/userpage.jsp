<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <script src="js/index.js"></script> -->
<link rel="stylesheet" type="text/css" href="css/style.css" />

<title>User Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">

	
	$(document).ready(function() {
	    setInterval(doAjax,1000);
	    
	    
	    jQuery("#searchForm").submit(function( event ) {
	    	  event.preventDefault();
	    	});
	    
	    
	});
	
	
	function doRecommendationStart() {
		 $("#recommendationResultsWrapper").html("processing recommendation...");	
		$.ajax({
    	        url: 'gui/startRecommendation',
    	        method: 'GET'  
    	      });
    	 
    }
	
	 function doSearchByUserName() {
		 $("#searchResultsWrapper").html("processing search...");
		 	$.ajax({
	    	        url: 'gui/startSearch',
	    	        method: 'POST',
	    	        data: ({searchWord :$("#searchBox").val() , searchMethod:'searchInUserName'})
	    	      });
	    	 
	    }
	 
	 function doSearchByInterest() {
		 $("#searchResultsWrapper").html("processing search...");	
		 $.ajax({
	    	        url: 'gui/startSearch',
	    	        method: 'POST',
	    	        data: ({searchWord :$("#searchBox").val(), searchMethod:'searchInInterests'})
	    	      });
	    	 
	    }

    function doAjax() {
     	
    	$("#searchResultsWrapper").load("searchResults.html");
    	$("#recommendationResultsWrapper").load("recommendationResults.html");
    	
    }
    
  
    
  </script>

</head>
<body>
	
	<div id="wrapper" class="wrapper">
	<div class="container">
<div id="logId"></div>
<div id="logId8"></div>



<h3 class="userName">Hello: <c:out value="${userName}"></c:out></h3>

<h2>LIKES AND INTERESTS</h2>
<display:table  class="tableStyle" name="${myInterests}" id="interest" requestURI="/userpage.html">
<display:setProperty name="basic.msg.empty_list">You have not inserted interests or likes</display:setProperty> 
<display:column><c:out value="${interest_rowNum}.  "/></display:column>
<display:column><c:out value="${interest}"></c:out> </display:column>
</display:table>


<h2>Friends</h2>
<display:table  class="tableStyle" name="${myFriends}" id="friend" requestURI="/userpage.html">
<display:setProperty name="basic.msg.empty_list">You have no friends connected to you so far.</display:setProperty>
<display:column><c:out value="${friend_rowNum}.  "/></display:column>
<display:column><c:out value="${friend}"></c:out> </display:column>
</display:table>

<form>
<button type= "button" onclick="doRecommendationStart()">Give me Recommendations</button>
</form>
<p>Recommended: <span id="txtHint"></span></p>
<div id="recommendationResultsWrapper"></div>



<br>
<p>You can search for your interest here</p>


<form  id="searchForm"  class="form1" >
<input id="searchBox"  type="text" name="SearchBox" >
<input onclick="doSearchByUserName()" type="submit" value="Search by Username" class="searchButton"/>
<input onclick="doSearchByInterest()" type="submit" value="Search by Interests" class="searchButton"/>
</form> 


	
<div id="searchResultsWrapper"></div>	


<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
	
	
	

	
	
	
	

</div>
</body>
</html>