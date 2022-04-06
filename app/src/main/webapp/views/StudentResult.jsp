<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Onview</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="stylesheet" href="/styles/style.css">
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-dark text-l bg-success  px-5">
    <div class="container-fluid">
      <a class="navbar-brand " href="#">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-workspace" viewBox="0 0 16 16">
          <path d="M4 16s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H4Zm4-5.95a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5Z"/>
          <path d="M2 1a2 2 0 0 0-2 2v9.5A1.5 1.5 0 0 0 1.5 14h.653a5.373 5.373 0 0 1 1.066-2H1V3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v9h-2.219c.554.654.89 1.373 1.066 2h.653a1.5 1.5 0 0 0 1.5-1.5V3a2 2 0 0 0-2-2H2Z"/>
        </svg> Onview
    </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <span class="px-4"></span>
        <div class="me-auto"></div>
        <a href="/logout">
            <button class="btn btn-outline-light btn-sm" type="submit">Logout</button>
        </a>
      </div>
    </div>
</nav>
    <div class="container-fluid">
      <div class="px-5 mt-4">
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item text-primary"><a href="/user/dashboard" class="decor-none"> Dashboard</a></li>
            <li class="breadcrumb-item active" aria-current="page"><a href="/user/history" class="decor-none">Back</a></li>
          </ol>
        </nav>
      </div>

      <div class="row mt-3 px-5">
          <div class="col-12">
              <div class="h5 text-b">Submission</div>
              <small class="text-secondary">Submissions of your Interview Question-wise</small>
          </div>
      </div>

      <div class="row mt-5 px-5">
          <div class="col-12">
              <div class="d-flex justify-content-between">
                  <div class="h5 text-b">Interview : <span class="text-primary">${tname}</span></div>
                  <div> <a href="/user/sendMail/${tid}/${uid}" class="btn bg-success text-white btn-sm"><i class="fa fa-share-alt"></i> Share Result</a>
                    <a href="/user/exportpdf/${tid}/${uid}" class="btn bg-success text-white btn-sm"><i class="fas fa-download"></i> Download Result</a></div>
                  
              </div>
          </div>
      </div>
      
      <div class="row px-5">
          <div class="col-12">
              <table class="table  table-striped">
                  <thead>
                    <tr>
                      <th scope="col">Question</th>
                      <th scope="col">Correct Answer</th>
                      <th scope="col">Your Choice</th>
                      <th scope="col">Status</th>
                      <th scope="col">Mark</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${reports}" var="reports" varStatus="loop">
                      <tr>
                        <td scope="row">${reports.question.questionText}</td>
                        <td>${reports.submission.rightanswer}</td>
                        <td>${reports.submission.choice}</td>
                        <td>${reports.submission.state}</td>
                        <td>${reports.submission.mark}</td>
                      </tr>
                    </c:forEach>
                  </tbody>
              </table>
          </div>
      </div> 
    </div>
<jsp:include page="Footer.jsp" />