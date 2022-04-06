<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="AdminHeader.jsp" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <div class="container-fluid">      
        <div class="px-5 mt-4">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item "><a href="/admin/dashboard" class="decor-none">Dashboard</a></li>
              <li class="breadcrumb-item active" aria-current="page">Interview</li>
            </ol>
          </nav>
        </div>
        <div class="row mt-2 px-5">
            <div class="col-12">
                <div class="d-flex justify-content-between">
                  <div>
                    <div class="h5 text-b">Interview : <span class="text-primary">${test.tname}</span> </div>
                    <small class="text-secondary">Evaluate answers of the Interviewee</small>
                  </div>
                  <div>
                    <div class="h5 text-b">Interviewee : <span class="text-primary">${participant_name}</span></div>
                    <button class="btn bg-info fw-bolder"><a href="/admin/test/${test.tid}/finishEvaluation">Finish Evaluation</a></button>
                  </div>
                    
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row mt-5 px-5">
              <div class="col-12">
                
                  
                  
                 <c:forEach var="question" items="${Questions}" varStatus="loopvar"> 
                  <div class="card w-100 px-2 py-3 border-dark border-5 d-flex"><!-- style="height:100vh;">-->
                  <div class="row">
                    
                    <div class="col-6 border-end border-5 border-success">
                      <div class="">
                        <div class="d-flex justify-content-between">
                          <div class="h5 text-b">Question ${Integer.toString(question.idx)}</div>
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

                        <div>
                          <div class="h5 text-b">Answer</div>
                          <div class="mt-2">
                            <c:if test="${question.questionFormat.equals('obj-b')}">
                              <p>Correct Options are: ${question.answerText}</p>
                            </c:if>
                            <c:if test="${!question.questionFormat.equals('obj-b')}">
                              <p>${question.answerText}</p>
                            </c:if>
                          </div>
                          <div class="mt-2">
                            <c:if test="${question.answerImage!=null}">
                            <img class="w-50" src="/img/as${question.qid}.jpg">
                          </c:if>
                          </div>
                        </div>

                      </div>
                    </div>
                    <div class="col-6 border-end border-dark" >
                      <div class="">
                        <div class="d-flex justify-content-between">
                          <div class="h5 text-b">Answer By Participant</div>
                        </div>
                        <c:if test="${question.questionFormat.equals('obj-a')}">
                          <!-- <form class="mt-2" method="POST" action="/user/test/${tid}/qn" modelAttribute="opt"> -->
                            <c:forEach items="${question.options}" var="option" varStatus="loop">
                              <div class="card px-2 py-2 mb-2 border-danger">
                                <div class="form-check">
                                  <c:if test="${submission.get(loopvar.index).choice.equals(option.answerText)}">
                                    <input class="form-check-input" type="radio"  id="${loop.index+1}" value="${option.answerText}" name="answerText" disabled checked/>
                                  </c:if>
                                  <c:if test="${!submission.get(loopvar.index).choice.equals(option.answerText)}">
                                    <input class="form-check-input" type="radio"  id="${loop.index+1}" value="${option.answerText}" name="answerText" disabled/>
                                  </c:if>
                                  <label class="form-check-label mb-2" for="${loop.index+1}">${option.answerText}</label>
                                  <div class="w-100"></div>
                                  <c:if test="${option.answerImage!=null}">
                                  <img class="w-50" src="/img/os${option.oid}.jpg">
                                </c:if>
                                </div>
                              </div>
                            </c:forEach>
                            <!-- <input type="submit" class="btn btn-primary btn-sm mt-5 px-5" label="Next" /> -->
                          <!-- <form> -->
                        </c:if>
    
                        <c:if test="${question.questionFormat.equals('obj-b')}">
                          <!-- <form class="mt-2" method="POST" action="/user/test/${tid}/qn" modelAttribute="opt"> -->
                            <c:forEach items="${question.options}" var="option" varStatus="loop">
                              <div class="card px-2 py-2 mb-2 border-danger">
                                <div class="form-check">
                                  <c:if test="${submission.get(loopvar.index).choice.contains(option.answerText)}">
                                    <input class="form-check-input" type="checkbox"  id="${loop.index+1}" value="${option.answerText}" name="answerText" disabled checked/>
                                  </c:if>
                                  <c:if test="${!submission.get(loopvar.index).choice.contains(option.answerText)}">
                                    <input class="form-check-input" type="checkbox"  id="${loop.index+1}" value="${option.answerText}" name="answerText" disabled/>
                                  </c:if>
                                  <label class="form-check-label mb-2" for="${loop.index+1}">${option.answerText}</label>
                                  <c:if test="${option.answerImage!=null}">
                                    
                                  <img class="w-100" src="/img/os${option.oid}.jpg">
                                </c:if>
                                </div>
                              </div>
                            </c:forEach>
                            <!-- <input type="submit" class="btn btn-primary btn-sm mt-5 px-5" label="Next" /> -->
                          <!-- <form> -->
                        </c:if>
    
                        <c:if test="${question.questionFormat.equals('sub-a')}">
                          <!-- <form class="mt-2" method="POST" action="/user/test/${tid}/qn" modelAttribute="opt" enctype="multipart/form-data"> -->
                            
                              <div class="card px-2 py-2 mb-2 border-danger">
                                <div class="form-check">
                                  <label class="form-check-label mb-2" for="ans">Answer: </label><br>
                                  <textarea class="form-control"  id="ans"  name="answerText" disabled>${submission.get(loopvar.index).choice}</textarea>
                                  <!-- <label class="form-check-label mb-2" for="fileupld">Upload files: </label>
                                  <input class="form-control" type="file" name="answerImage" id="fileupld"> -->
                                  
                                </div>
                              </div>
                            
                            <!-- <input type="submit" class="btn btn-primary btn-sm mt-5 px-5" label="Next" /> -->
                          <!-- <form> -->
                        </c:if>
                        <!-- correct/wrong selection buttons -->
                        <div class="mt-2 w-50 ms-auto">
                          <button class="btn btn-success my-2" onclick="changeAnswerToCorrect(${loopvar.index},${test.tid});">Correct Answer <i class="fas fa-check-circle"></i></button>
                          <button class="btn btn-danger my-2" onclick="changeAnswerToWrong(${loopvar.index},${test.tid});">Wrong Answer <i class="fas fa-times-circle"></i></button>
                        </div>
                      </div>
                    </div>    


                  </div>
                  
                </div>  <!--end card-->
                <!-- <div class="bg-dark mx-0 my-2"><hr></div> -->
                <br><br>
                </c:forEach>
    
                  
                </div>
              </div>
            </div>        
        </div>



       

<!-- template object -->
<div class="mb-1 d-none" id="templateOptionObject">
  <label for="option-x" class="form-label">Option x</label>
  <input type="text" class="form-control form-control-sm m-2" name="options" />
  <div class="d-none">
  <input class="form-control m-2"type="file" name="images">
  <!-- <label  for="image-x">Choose Image</label> -->
  </div>
  <div class="">
    <input type="checkbox" class="form-control-input mx-1" name="toggletext" checked onClick="toggleView(this,'input');">
    <label class="form-control-label" for="toggletext">Text</label>
    <input type="checkbox" class="form-control-input mx-1" name="Toggleimage" onClick="toggleView(this,'div');">
    <label class="form-control-label" for="Toggleimage">Image</label>
  </div>
</div>
<!--option label-->
<div class="d-none" id="templateCheckbox"> 
  <input type="checkbox" class="mx-2" value="0" name="answerText"/>
  <label>x</label>
</div>

<script>
  function changeAnswerToCorrect(index,tid){
    $.ajax({
      type:"GET",
      url:"http://localhost:8080/admin/testevalsubmit/"+tid+"/"+index+"/correct",
      dataType:"text",
      success:function(data){
        alert("changes successful");
      }
    }); 
  }

  function changeAnswerToWrong(index,tid){
    $.ajax({
      type:"GET",
      url:"http://localhost:8080/admin/testevalsubmit/"+tid+"/"+index+"/wrong",
      success:function(){
        alert("changes successful");
      }
    });
}

  var counter=2,counter1=2;
  function resetCounter(){
    counter=2;
  }
  function resetCounter1()
  {
    counter1=2;
  }
  function addOption(ele)
  {
    
    console.log(counter);
    var x=document.getElementById("templateOptionObject");
    var y=x.cloneNode(true);
    y.classList.toggle("d-none");
    y.removeAttribute("id");
    var label=y.querySelectorAll("label");
    label[0].setAttribute("for","option-"+counter);
    // label[1].setAttribute("for","image-"+counter);
    label[0].textContent="option "+counter;
    var inputTag=y.querySelectorAll("input");
    inputTag[0].setAttribute("name","options");//+counter);
    inputTag[1].setAttribute("name","images");//+counter);
    ele.parentElement.appendChild(y);
    var option=document.createElement("option");
    option.setAttribute("label",counter);
    option.setAttribute("value",counter-1);
    
    ele.parentElement.parentElement.getElementsByClassName("answer")[0].appendChild(option);
    console.log("successful");
    counter++;

  }

  function addOption1(ele)
  {
    
    console.log(counter1);
    var x=document.getElementById("templateOptionObject");
    var y=x.cloneNode(true);
    y.classList.toggle("d-none");
    y.removeAttribute("id");
    var label=y.querySelectorAll("label");
    label[0].setAttribute("for","option-"+counter1);
    // label[1].setAttribute("for","image-"+counter);
    label[0].textContent="option "+counter1;
    var inputTag=y.querySelectorAll("input");
    inputTag[0].setAttribute("name","options");
    inputTag[1].setAttribute("name","images");
    ele.parentElement.appendChild(y);
    var option=document.getElementById("templateCheckbox");
    var op=option.cloneNode(true);
    op.classList.toggle("d-none");
    op.classList.toggle("d-inline");
    op.removeAttribute("id");
    op.getElementsByTagName("label")[0].textContent=counter1;
    op.getElementsByTagName("input")[0].setAttribute("value",counter1-1);
    
    ele.parentElement.parentElement.getElementsByClassName("answer")[0].appendChild(op);
    console.log("successful");
    counter1++;

  }

  function toggleView(ele,type){
    var x=ele.parentElement.parentElement.querySelector(type);
    x.classList.toggle("d-none");
  }
</script>
<jsp:include page="Footer.jsp" />