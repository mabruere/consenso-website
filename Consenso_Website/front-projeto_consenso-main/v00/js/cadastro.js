//referenciar os elementos html
const form = document.getElementById("form-signin")
const nome = document.getElementById("inputNome")
const email = document.getElementById("inputEmail")
const senha = document.getElementById("inputSenha")
const tipoUsuario = document.getElementById("inputTipoUsuario")

// CADASTRO - VALIDAÇÃO

form.addEventListener("submit", (e) => {
    e.preventDefault()
    validarEntradas(nome, email, senha)

})

//nome: teste
//email: teste@teste.com
//senha: Wabcde123#

function validarEntradas(n, e, s) {
    const nomeValue = String(n.value)
    const emailValue = String(e.value)
    const senhaValue = String(s.value)


    if (nomeValue === "" || nomeValue == null) {
        nome.className = "form-control is-invalid"
        console.log("Nome é um campo obrigatório")

    } else if (emailValue === "" || emailValue == null) {
        console.log("E-mail é um campo obrigatório")
        
    } else if (validarEmail(emailValue) === false) {
        console.log("Formato de email inválido")
        let invalidacao = document.getElementById("invalidacaoEmail")
        invalidacao.classList.remove("d-none")
        document.getElementById('inputEmail').style.borderColor = "red";

    } else if (senhaValue === "" || senhaValue == null) {
        console.log("Senha é um campo obrigatório")

    } else if (senhaValue.length < 8) {
        console.log("A senha deve ter no mínimo 6 caracteres.")
        let invalidacao = document.getElementById("invalidacaoSenha")
        invalidacao.classList.remove("d-none")
        document.getElementById('inputSenha').style.borderColor = "red";

    } else {
        console.log("Cadastro realizado com sucesso")
        sendDataToAPI(nomeValue,emailValue,senhaValue,tipoUsuario.value)
    }
}

function validarEmail(ev) {
    let re = /\S+@\S+\.\S+/
    return re.test(ev)
}


// CADASTRO - REGISTRO

function sendDataToAPI(nome, email, senha, tipoUsuario) {
  (async () => {
      const rawResponse = await fetch('http://localhost:8080/usuario', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({nome: nome, email:email, senha:senha, tipousuario:{
          idTipoUsuario:tipoUsuario
        }})
      });
      const content = await rawResponse.json();
      if (rawResponse.status == 201){
          
          window.location.href = "./login.html"
          

      }
    
      console.log(content);
    })();
}