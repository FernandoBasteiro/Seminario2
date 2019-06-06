
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>FAR - Registro</title>
  <link rel="shortcut icon" href="assets/img/FavIcon.png">

 <!-- Custom fonts for this template-->
<link href="assets/fontawesome/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="assets/css/far.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-12">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">¡Create una cuenta!</h1>
              </div>
              <form class="user">
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <input type="text" class="form-control form-control-user" id="inNombre" name="nombre" placeholder="Nombre">
                  </div>
                  <div class="col-sm-6">
                    <input type="text" class="form-control form-control-user" id="inApellido" name="apellido" placeholder="Apellido">
                  </div>
                </div>
                <div class="form-group" id="usrDiv">
                  <input type="text" class="form-control form-control-user" id="inUsuario" name="usuario" placeholder="Nombre de Usuario">
                </div>
                <div class="form-group ml-2" id="ver">
                	<span class="spinner-border text-primary spinner-border-sm mb-0 my-0 p-0 desaparecer" role="status" aria-hidden="true" id="loading"></span>
                	<h6 class="errores mb-0 desaparecer" id="existe" style="margin-top:.3rem">El nombre de usuario ya existe</h6>
                	<h6 class="confirmacion mb-0 desaparecer" id="noexiste" style="margin-top:.3rem">El nombre de usuario esta disponible</h6>
                </div>
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <input type="password" class="form-control form-control-user" id="inPassword" name="password" placeholder="Contraseña">
                  </div>
                  <div class="col-sm-6">
                    <input type="password" class="form-control form-control-user" id="inPassword2" name="password2" placeholder="Repetí tu contraseña">
                  </div>
                </div>
                <a onClick="submitirFormulario()" class="btn btn-primary btn-user btn-block">
                  Crear cuenta
                </a>
              </form>
              <hr>
              <div class="text-center">
                <a class="small" href="login.jsp">¿Ya tenés una cuenta?</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
<button onclick="loading">Loading</button>
<button onclick="existe()">Existe</button>
<button onclick="noExiste()">No Existe</button>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="assets/jquery/jquery.min.js"></script>
  <script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="assets/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="assets/js/far.min.js"></script>
  <script src="assets/js/registro.js"></script>

</body>

</html>
