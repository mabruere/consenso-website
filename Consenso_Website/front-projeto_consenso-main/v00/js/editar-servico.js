var idServico = localStorage.getItem("idServico")

fetch
console.log(idServico)

let botao = document.getElementById("editarServico")
botao.addEventListener("click", function(event){
    event.preventDefault()
  let nome = document.getElementById("nomeServico").value
  let descricao = document.getElementById("descServico").value
  
  sendDataToAPI(nome, descricao, idServico)
  
});


async function sendDataToAPI(nome, descricao, idServico) {
    
  try {
      const rawResponse = await fetch('http://localhost:8080/servico', {
        method: 'PUT',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({nome:nome, descricao:descricao, idServico})
      });
      const content = await rawResponse.json();
      console.log(rawResponse)
      if(rawResponse.status == 202){
        window.alert("Servico editado com sucesso!")
        location.reload()
      }
    
      console.log(content);
    }catch (error) {
      console.error(error);
}}