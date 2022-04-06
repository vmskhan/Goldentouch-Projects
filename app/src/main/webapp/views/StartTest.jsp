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
            <div class="me-auto"></div>
            <a href="/logout">
                <button class="btn btn-outline-light btn-sm" type="submit">Logout</button>
            </a>
          </div>
        </div>
    </nav>
    <div class="container-fluid px-5">
        <div class="mt-5 border-bottom pb-4 text-center h4 text-d">
            Read the instructions carefully.
        </div>
        <div class="row mt-5">
            <div class="col-7">
                <div class="text-b h5">Test Details</div>
                <div class="row mt-3">
                    <div class="col-3">Name</div>
                    <div class="col-3">Date</div>
                    <div class="col-3">Duration</div>
                    <div class="col-3">Total Marks</div>
                </div>
                <div class="row mt-3 text-b">
                    <div class="col-3">${test.tname}</div>
                    <div class="col-3">${test.date}</div>
                    <div class="col-3">${test.duration}</div>
                    <div class="col-3">${test.totalMarks}</div>
                </div>
                <div class="text-b h5 mt-5">Test Specific Instructions</div>
                <div class="mt-3">
                    <p>* You cannot change your answer once submitted</p>
                    <p>* You can move to next question if and only if you completed current question</p>
                    <p>* If you lost network connectivity, you can resume the test once you reconnected</p>
                </div>
            </div>
            <div class="col-5">
                <div class="px-2 py-4 shadow text-center rounded">
                    You can start the test now. Wish you luck ;-)
                    <a href="/user/test/${tid}/start">
                        <div class="mt-3 d-grid">
                            <button class="btn btn-block bg-r text-white"><i class="fas fa-play px-2"></i> Start Test</button>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</html>

   