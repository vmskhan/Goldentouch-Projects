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
    <title>Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
   
    <%
    for(int i=0;i<3;i++)
    {
     out.println("<c:url value=\""+request.getAttribute("imgName"+(i+1))+" \" var=\"photo\"/>");
    }
     %>
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
                <a class="nav-link fw-bolder bg-info" aria-current="page" href="/homepage/display">Home</a>
              </li>
            </ul>
            <a class="nav-link active fw-bolder bg-info" href="/cart/show">Cart</a>
          </div>
        </div>
      </nav>
            <!-- <img src=\"...\" class=\"card-img-top\" alt=\"...\"> -->
      <br>
      <div class="container rounded border border-3 border-dark bg-light">
            <h1 class="display-1 bg-light text-decoration-underline text-center">Cart</h1> 
          </div>
          <br>
    <div class="container bg-light rounded border border-dark border-3">
      <h2 class="display-1 bg-light text-decoration-underline text-center">Items</h2> 
        <div class="row">
    
        <%
        int i;
        if((int)request.getAttribute("noOfProducts")!=0)
        {
          out.println("<ol class=\"list-group list-group-numbered\">");
        for(i=0;i<(int)request.getAttribute("noOfProducts");i++)
        {
        out.println("<li class=\"list-group-item d-flex justify-content-between align-items-start row\" id=\""+request.getAttribute("pid"+(i+1))+"\">\r\n"
            + "          <div class=\"ms-2 me-auto col-6\">\r\n"
            + "            <div class=\"fw-bold\">"+ request.getAttribute("pname"+(i+1))+"</div>\r\n"
            + "            <p>"+request.getAttribute("pdesp"+(i+1)) +"</p>\r\n"
            + "            <br>\r\n"
            + "            <p name=\"totalPrice\">Price:"+((int)request.getAttribute("price"+(i+1))*(int)request.getAttribute("qty"+(i+1)))+"Rs</p>\r\n"
            + "            <br>\r\n"
            + "            <p>Qty:"+request.getAttribute("qty"+(i+1))+" units</p>\r\n"
            + "            <br>\r\n"
            + "            <button class=\"btn btn-primary\" onclick=\"removeItem("+request.getAttribute("pid"+(i+1))+");\">remove</button>  \r\n"
            + "        </div>\r\n"
            + "          \r\n"
            + "          <!-- <span class=\"badge bg-primary rounded-pill\">14</span> -->\r\n"
            + "           <div class=\"col-3\">\r\n"
            + "          <img src=\"" +request.getAttribute("imgName"+(i+1)) +"\" class=\"img-fluid\">\r\n"
            + "           </div>\r\n"
            + "        </li>");
        }
        out.println("</ol>");
      }
      else
      {
        out.println("<div class=\"list-group-item\">\r\n"
          + "      <h1 class=\"display-4\">Cart is empty</h1>\r\n"
          + "      <p class=\"lead fw-bolder\">Please add some products</p>\r\n"
          + "    </div>\r\n");
      }
        %>
        
      
    </div>      
</div>      
<br>
<div class=" container rounded border border-dark border-3 jumbotron jumbotron-fluid bg-light">
    <div class="container">
      <h1 class="display-4">Total Cost</h1>
      <p class="lead fw-bolder" id="tc"></p>
      <br>
      <form action="pay" method="post">
        <div class="form-group">
          <label for="name">Name</label>
          <input type="text" class="form-control" name="name" placeholder="your name">
        </div>
        <div class="form-group">
          <label for="phoneno">Phone No</label>
          <input type="text" class="form-control" name="phoneno" placeholder="123456891">
        </div>
        <div class="form-group">
          <label for="emailid">Email address</label>
          <input type="text" class="form-control" name="emailid" placeholder="name@example.com">
        </div>
        <br>
      <button type="submit" class="btn btn-primary" id="paybtn">Pay</button>
      </form>
      <br>
    </div>
  </div>
  <br>
    <script src="../../cart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>