<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
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
<body class="auth-bg-left-img w-100">
        <div class="d-flex align-items-center justify-content-around">
            <div class="shadow rounded bg-white px-5 py-3 w-50 mt-3">      
                <div class="h3 mt-4 text-success text-center"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-workspace" viewBox="0 0 16 16">
                    <path d="M4 16s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H4Zm4-5.95a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5Z"/>
                    <path d="M2 1a2 2 0 0 0-2 2v9.5A1.5 1.5 0 0 0 1.5 14h.653a5.373 5.373 0 0 1 1.066-2H1V3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v9h-2.219c.554.654.89 1.373 1.066 2h.653a1.5 1.5 0 0 0 1.5-1.5V3a2 2 0 0 0-2-2H2Z"/>
                  </svg> Onview</div>
                <div class="text-center"> 
                    <small class="text-secondary">A better way to Assess </small>             
                </div>
                <div class="h5 mt-4 text-success text-center">S i g n  U p</div>              
                <spring:form class="mt-3" method="POST" action="/signup" modelAttribute="user">
                    <div class="form-group">
                        <label for="email" class="mb-2 text-secondary">Email id</label>
                        <spring:input type="email" path="emailid" placeholder="Email" class="form-control" />
                    </div>
                    <div class="form-group mt-4">
                        <label for="name" class="mb-2 text-secondary">Name</label>
                        <spring:input type="text" path="name" placeholder="Your Name" class="form-control"/>
                    </div>
                    <div class="form-group mt-4">
                        <label for="password" class="mb-2 text-secondary">Password</label>
                        <spring:input type="password" path="password" placeholder="Password" class="form-control" />
                    </div>
                    <div class="text-center mt-4">
                        <input class="btn bg-success text-white btn-sm px-5" type="submit" value="Sign Up" />
                    </div>
                    <div class="my-2 text-center text-danger">
                        <%= request.getAttribute("msg") %>
                    </div>
                    <div class="mt-3 text-center">
                        <div class="text-secondary mb-2"> OR</div>
                        Already have an account ? <a href="/login" >Login</a><br>
                    </div>
                </spring:form>
            </div>
        </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>