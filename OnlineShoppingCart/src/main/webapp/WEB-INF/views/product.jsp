<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
    <c:set var="limit" value="<%=(int)request.getAttribute(\"noOfProducts\")-1 %>"></c:set>
    <c:forEach var="i" begin="0" end="${limit}">
    
     <c:url value="${request.getAttribute(\"imgName\"+(i+1))}"/>
   </c:forEach>
     
</head>
<body class="bg-info">
  
    <nav class="navbar navbar-expand-lg navbar-light bg-primary border-bottom border-dark border-3">
        <div class="container-fluid">
          <a class="navbar-brand" href="/homepage/display">Shopper's Cart</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link bg-info fw-bolder" aria-current="page" href="/homepage/display">Home</a>
              </li>
            </ul>
            <a class="nav-link text-dark bg-info fw-bolder" href="/cart/show">Cart</a>
          </div>
        </div>
      </nav>
      
      <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
        </symbol>
        <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
          <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
        </symbol>
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
          <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
      </svg>
      <div id="liveAlertPlaceholder"></div>

      
            <!-- <img src=\"...\" class=\"card-img-top\" alt=\"...\"> -->
     <div class="container bg-light border-start border-end border-dark border-3">
      <h1 class="display-1 text-decoration-underline text-center">Products</h1>
      <div class="row">
      
          <%
      int i;
      for(i=0;i<(int)request.getAttribute("noOfProducts");i++)
      {
      out.println("<div class=\"col\">"
        + "<div class=\"card m-5 border-primary\" style=\"width: 18rem;\">\n"
        + "        <img src=\""+ request.getAttribute("imgName"+(i+1))+"\" class=\"card-img-top w-50 m-auto\" />\n"
        + "        <div class=\"card-body text-center\">\n"
        + "          <h5 class=\"card-title\">"+request.getAttribute("pname"+(i+1))+"</h5>\n"
        + "          <p class=\"card-text\">"+request.getAttribute("pdesp"+(i+1))+"<br>"+request.getAttribute("price"+(i+1))+"Rs</p>\n"
        + "           <input type=\"number\" name=\"qty\" id=\"q" +request.getAttribute("pid"+(i+1)) +"\" value=\"0\">\n"
        + "          <button onclick=\"getData(this); \" class=\"btn btn-primary my-2\" id=\"" + request.getAttribute("pid"+(i+1)) + "\">Add to Cart</button>\n"
        + "        </div>\n"
        + "      </div>"
        + "      </div>");
      }
        %>
    </div>
    </div>
    <br>
    
    <!-- <c:forEach var="i" begin="0" end="${limit}">
       Item <c:out value="${i}"/>
     </c:forEach>         -->
    <!-- <% out.println(request.getAttribute("myProduct"));%>
    <!-- <img src="<%= request.getAttribute("imgName")%>" class="card-img-top" /> -->
    <!-- <img src="/zafar.jpg" /> -->
    <script src="../../product.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>





