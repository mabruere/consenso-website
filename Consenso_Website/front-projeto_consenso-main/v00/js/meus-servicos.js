// NOVO
var idServicoFinal
var usuario = localStorage.getItem("usuario")
var idUsuario = localStorage.getItem("idUsuario")
totalAgendamentos = 0
document.addEventListener("DOMContentLoaded", function() {
    // code to be executed when the DOM is ready
    console.log(idUsuario)
    getServicosPorId()
   
    
  });
  

async function getServicosPorId() {
    try {
        const rawResponse = await fetch(`http://localhost:8080/servico/usuario/${idUsuario}`, {
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
                agendamentoPai = document.getElementById("meusServicosPai")
                for (let index = 0; index < data.length; index++) {
                  const element = data[index]; 
                  agendamentoPai.innerHTML += `<div class="card" id="${element.idServico}">
            <div class="card-body" >

              <div class="d-flex bd-highlight col-12 text-start fs-5">
                <label for="tipo-servico" class=" flex-grow-1 bd-highlight" id="tipoServico"> <strong>${element.nome}</strong></label> 
                <img src="../image/pencil-square.svg" onclick="funcaoEditar(${element.idServico})" class="">
                <img src="../image/trash.svg" onclick="deletarServico(${element.idServico})" class="" alt="">
              </div>
            </div>

          </div>
                  `
              totalAgendamentos++
                  
              
                }
            }
         
          
          

        }
          
        
    } catch (error) {
        console.error(error);
    }
  }

  async function deletarServico(el) {
    try {
        const rawResponse = await fetch(`http://localhost:8080/servico/${el}`, {
          method: 'DELETE',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
        });
        console.log(rawResponse.status)


        if( rawResponse.status == 202){
        const divpai = document.getElementById(el)
        console.log(divpai)
          console.log(el)   
          
          divpai.remove()
          window.alert("Serviço deletado!")
          totalAgendamentos--
          if(totalAgendamentos == 0){ 
            document.getElementById("nenhum-agendamento").classList.remove("d-none")
            let text = document.getElementById("titulo-agendamento")
            text.classList.add("d-none")

          }
        }
        
    } catch (error) {
        console.error(error);
    }
    
    
  }

  function funcaoEditar(id){
    localStorage.setItem("idServico", id)
    window.location.href= "../prestador/editar-servico.html"
  }

