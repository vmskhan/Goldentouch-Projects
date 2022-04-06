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
            <!-- <a href="/user/test/${tid}/end">
                <button class="btn btn-outline-light btn-sm" type="submit">End Test</button>
            </a> -->
          </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row mt-5 px-5">
          <div class="col-12">
            <div class="card w-100 px-2 py-3 border-success border-5 overflow-auto" style="height:100vh;">
              <div class="row">
<!-- zoom -->
                <div class="col-6 border-end border-dark" id="meetingSDKElement">

                </div>



                <div class="col-6">
                  <div class="">
                    <div class="d-flex justify-content-between">
                      <div class="h5 text-b">Question ${question.idx}</div>
                      <p>Mark : <strong>${question.mark}</strong></p>
                    </div>
                    <div class="mt-2">
                      <p>${question.questionText}</p>
                    </div>
                    
                    <div class="mt-2">
                      <c:if test="${question.questionImage!=null}">
                      <img class="w-50" src="/img/qs${question.qid}.jpg">
                    </c:if>
                    </div>
                  
                  </div>

                  <div class="">
                    <div class="d-flex justify-content-between">
                      <div class="h5 text-b">Options</div>
                    </div>
                    <c:if test="${question.questionFormat.equals('obj-a')}">
                      <form class="mt-2" method="POST" action="/user/test/${tid}/qn" modelAttribute="opt">
                        <c:forEach items="${options}" var="option" varStatus="loop">
                          <div class="card px-2 py-2 mb-2 border-danger">
                            <div class="form-check">
                              <input class="form-check-input" type="radio"  id="${loop.index+1}" value="${option.answerText}" name="answerText" required />
                              <label class="form-check-label mb-2" for="${loop.index+1}">${option.answerText}</label>
                              <div class="w-100"></div>
                              <c:if test="${option.answerImage!=null}">
                              <img class="w-50" src="/img/os${option.oid}.jpg">
                            </c:if>
                            </div>
                          </div>
                        </c:forEach>
                        <input type="submit" class="btn btn-primary btn-sm mt-5 px-5" label="Next" />
                      <form>
                    </c:if>

                    <c:if test="${question.questionFormat.equals('obj-b')}">
                      <form class="mt-2" method="POST" action="/user/test/${tid}/qn" modelAttribute="opt">
                        <c:forEach items="${options}" var="option" varStatus="loop">
                          <div class="card px-2 py-2 mb-2">
                            <div class="form-check">
                              <input class="form-check-input" type="checkbox"  id="${loop.index+1}" value="${option.answerText}" name="answerText" />
                              <label class="form-check-label mb-2" for="${loop.index+1}">${option.answerText}</label>
                              <c:if test="${option.answerImage!=null}">
                                
                              <img class="w-100" src="/img/os${option.oid}.jpg">
                            </c:if>
                            </div>
                          </div>
                        </c:forEach>
                        <input type="submit" class="btn btn-primary btn-sm mt-5 px-5" label="Next" />
                      <form>
                    </c:if>

                    <c:if test="${question.questionFormat.equals('sub-a')}">
                      <form class="mt-2" method="POST" action="/user/test/${tid}/qn" modelAttribute="opt" enctype="multipart/form-data">
                        
                          <div class="card px-2 py-2 mb-2">
                            <div class="form-check">
                              <label class="form-check-label mb-2" for="ans">Answer: </label><br>
                              <textarea class="form-control"  id="ans"  name="answerText" required></textarea>
                              <label class="form-check-label mb-2" for="fileupld">Upload files: </label>
                              <input class="form-control" type="file" name="answerImage" id="fileupld">
                              
                            </div>
                          </div>
                        
                        <input type="submit" class="btn btn-primary btn-sm mt-5 px-5" label="Next" />
                      <form>
                    </c:if>

                  </div>
                </div>


              </div>
            </div>
          </div>
        </div>        
    </div>
    <button class="btn btn-primary nav-btn px-1 py-4 bg-white text-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" ><i class="fas fa-arrow-left"></i></button>

        <div class="offcanvas offcanvas-end" style="width: 250px;margin-top: 104px;overflow: hidden;"  data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
          
          <div class="offcanvas-body w-100 pt-0 pl-0" style="margin-left: 25px; overflow: hidden;" >
                <div style="min-height: 250px;max-height: 250px;  width: 100%; overflow: scroll; overflow-x: hidden; padding-right: 17px; box-sizing: content-box;" class="border">
                  <div class="bg-b text-white text-center">Navigation</div>
                  <div class="row mt-2 px-3">
                    <c:forEach begin="0" end="${participation.totalQn - 1}" varStatus="loop">
                      <c:if test="${ loop.index < participation.last_attempted}">
                        <div class="col-3 text-white mt-2">
                          <button class="btn btn-primary rounded-circle">${loop.index+1}</button>
                        </div>                        
                      </c:if>
                      <c:if test="${ loop.index == participation.last_attempted}">
                        <div class="col-3 text-white mt-2">
                          <button class="btn bg-r text-white rounded-circle">${loop.index+1}</button>
                        </div>                        
                      </c:if>
                      <c:if test="${ loop.index > participation.last_attempted}">
                        <div class="col-3 text-white mt-2">
                          <button class="btn btn-outline-primary rounded-circle">${loop.index+1}</button>
                        </div>                        
                      </c:if>
                   </c:forEach>
                  </div>              
              </div>

              <div class="border" style="padding-right: 17px;">
                <div class="bg-b text-white text-center">Summary</div>
                <div class="row py-1 px-1">
                  <div class="col-8">
                    <div class="text-secondary">Total questions</div>
                  </div>
                  <div class="col-4 text-end">${participation.totalQn}</div>
                </div> 
                <div class="row py-1 px-1">
                  <div class="col-8">
                    <div class="text-secondary">Answered</div>
                  </div>
                  <div class="col-4 text-end">${participation.last_attempted}</div>
                </div> 
                <div class="row py-1 px-1">
                  <div class="col-8">
                    <div class="text-secondary">Saved</div>
                  </div>
                  <div class="col-4 text-end">${participation.last_attempted}</div>
                </div>    
                <div class="row py-1 px-1">
                  <div class="col-8">
                    <div class="text-secondary">Unanswered</div>
                  </div>
                  <div class="col-4 text-end">${ participation.totalQn - participation.last_attempted}</div>
                </div>    
            </div>
          </div>  
          <button class="btn btn-primary bg-white text-primary px-1 py-4 inner-nav-btn" data-bs-dismiss="offcanvas"><i class="fas fa-arrow-right"></i></button>
        </div>
    </div>
    <script src="https://source.zoom.us/2.3.5/lib/vendor/react.min.js"></script>
    <script src="https://source.zoom.us/2.3.5/lib/vendor/react-dom.min.js"></script>
    <script src="https://source.zoom.us/2.3.5/lib/vendor/redux.min.js"></script>
    <script src="https://source.zoom.us/2.3.5/lib/vendor/redux-thunk.min.js"></script>
    <script src="https://source.zoom.us/2.3.5/lib/vendor/lodash.min.js"></script>
    
    <script src="https://source.zoom.us/2.3.5/zoom-meeting-embedded-2.3.5.min.js"></script>
    <script src="${pageContext.request.contextPath}/joinClient.js"></script>
<jsp:include page="Footer.jsp" />