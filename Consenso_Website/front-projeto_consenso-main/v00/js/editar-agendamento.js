var idUsuario = localStorage.getItem("idUsuario")
var idServico = localStorage.getItem("idServico")
var idAgendamento = localStorage.getItem("idAgendamento")


console.log(idUsuario)
document.addEventListener("DOMContentLoaded", function(){
  getServicosPorId()
})

document.addEventListener("change", function(){
  select = document.getElementById("servico-selecionado")
  servicoSelecionado = String (select.value);
  console.log(servicoSelecionado)
})

let botao = document.getElementById("botao-agendamento")
botao.addEventListener("click", function(event){ 
  event.preventDefault()
  let data = document.getElementById("data-selecionada").value
  let hora = document.getElementById("hora-selecionada").value
  var date = new Date(data);
    var formattedDate = (date.getDate()+2)+ "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    var horaFormatada= `${hora[0]}${hora[1]}:${hora[3]}${hora[4]}`;
    sendDataToAPI(idAgendamento, formattedDate, horaFormatada, idUsuario, servicoSelecionado)
});

async function getServicosPorId() {
  try {
      const rawResponse = await fetch(`http://localhost:8080/servico`, {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
      });
      const data = await rawResponse.json();
      console.log(data)
      console.log(rawResponse.status)
      
      if(rawResponse.status == 200){
          if (data.length == 0){
              let popup = document.getElementById("nenhum-agendamento")
              popup.classList.remove("d-none")

          
          }
          else{
              agendamentoPai = document.getElementById("servico-selecionado")
              for (let index = 0; index < data.length; index++) {
                const element = data[index]; 
                agendamentoPai.innerHTML += `<option value="${element.idServico}">${element.nome}</option>`
            
              }
          }
       
      }
        
      
  } catch (error) {
      console.error(error);
  }
}



async function sendDataToAPI(idAgendamento, formattedDate, horaFormatada, idUsuario, servicoSelecionado) {
  console.log(servicoSelecionado)
  console.log(formattedDate)
  console.log(horaFormatada)
try {
    const rawResponse = await fetch('http://localhost:8080/agendamento', {
      method: 'PUT',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({idAgendamento:idAgendamento, data:formattedDate, hora:horaFormatada, usuario:{idUsuario:idUsuario}, servico:{idServico:servicoSelecionado} })
    });
    const content = await rawResponse.json();
    console.log(rawResponse.status)
    if(rawResponse.status == 202){
      window.alert("Servico alterado com sucesso!")
      location.reload()
    }
  
    console.log(content);
  }catch (error) {
    console.error(error);
}}
