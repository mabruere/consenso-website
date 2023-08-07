let loginForm = document.getElementById("loginForm");
loginForm.addEventListener("submit", async (event) => {
  event.preventDefault();

  let email = document.getElementById("inputEmail").value;
  let senha = document.getElementById("inputSenha").value;

  try {
    let response = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email,
        senha: senha
      }),
    });
    console.log(response);
    const content = await response.json();
    if (response.ok) {
      localStorage.setItem("idUsuario", content.idUsuario);
      let idusuario = localStorage.getItem("idUsuario")
      console.log(idusuario)
      if(content.tipousuario.idTipoUsuario === 2){
        window.location.href = "./cliente/meus-agendamentos.html"
      }
      else if(content.tipousuario.idTipoUsuario === 1){
        window.location.href = "./prestador/meus-servicos.html";
      }
    } else {
      alert("Usuário ou senha inválidos");
    }
  } catch (error) {
    console.error(error);
  }
});