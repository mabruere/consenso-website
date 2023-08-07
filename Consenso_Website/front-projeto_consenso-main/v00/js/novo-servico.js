var usuario = localStorage.getItem("usuario")
var idUsuario = localStorage.getItem("idUsuario")

console.log(idUsuario)

let botao = document.getElementById("cadastrarServico")
botao.addEventListener("click", function(event){
    event.preventDefault()
  let nome = document.getElementById("nomeServico").value
  let descricao = document.getElementById("descServico").value
  
  sendDataToAPI(nome, descricao, idUsuario)
  
});


async function sendDataToAPI(nome, descricao, idUsuario) {
    
  try {
      const rawResponse = await fetch('http://localhost:8080/servico', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({nome:nome, descricao:descricao, usuario:{idUsuario:idUsuario}})
      });
      const content = await rawResponse.json();
      console.log(rawResponse)
      if(rawResponse.status == 201){
        window.alert("Servico criado com sucesso!")
        location.reload()
      }
    
      console.log(content);
    }catch (error) {
      console.error(error);
}}