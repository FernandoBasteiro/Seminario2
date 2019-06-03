<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>FAR - Ingreso</title>

  <!-- Custom fonts for this template-->
  <link href="assets/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="assets/css/far.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">
  <div class="container">
    <!-- Outer Row -->
    <div class="row justify-content-center">
      <div class="col-md-9">
        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row ml-3">
              <div class="col-md-4 d-none d-md-block my-4" style="text-align: center;"><img src="assets/img/login-small.gif"></div>
              <div class="col-md-8">
                <div class="p-4">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Volviste, �Bienvenido!</h1>
                  </div>
                  <form class="user" action="Servlet" method="POST">
                  <input type="hidden" id="action" value="login">
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="usuario" aria-describedby="emailHelp" placeholder="Nombre de usuario">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="password" placeholder="Contrase�a">
                    </div>
                    <input type="submit" class="btn btn-primary btn-user btn-block" value="Ingresar" />
                  </form>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="register.html">�Queres registrarte?</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>

</html>
