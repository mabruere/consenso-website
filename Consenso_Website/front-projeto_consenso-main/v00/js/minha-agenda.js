// NOVO
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
        const rawResponse = await fetch(`http://localhost:8080/agendamento/usuario/${idUsuario}`, {
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
                agendamentoPai = document.getElementById("entrada-agenda")
                for (let index = 0; index < data.length; index++) {
                  const element = data[index]; 
                  agendamentoPai.innerHTML += `<div class="card" id="${element.idAgendamento}">
                  <div class="card-body">
      
                    <div class="d-flex bd-highlight col-12 text-start fs-5">
                      <label for="tipo-servico" class=" flex-grow-1 bd-highlight" id="tipoServico"> <strong>${element.servico.nome}</strong></label> 
                      <img src="../image/pencil-square.svg" onclick="funcaoEditar(${element.idAgendamento})" class="p-2 bd-highlight" id="pen">
                      <img src="../image/trash.svg" onclick="deletarServico(${element.idAgendamento})" class="" alt="">
                    </div>
      
                    <div class="col-12 text-start fs-5">
                      <label for="nomePrestador"><strong>Cliente: </strong>${element.usuario.nome}</label>
                      <label for=""></label>
                    </div>
      
                    <div class="col-12 text-start fs-5">
                      <label for="diaAgendamento"><strong>Dia: </strong>${element.data}</label>
                    </div>
      
                    <div class="col-12 text-start fs-5">
                      <label for=""><strong>Horário: </strong>${element?.hora[0]}:${element?.hora[1]}</label>
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


  //deletar servico
  async function deletarServico(el) {
    try {
        const rawResponse = await fetch(`http://localhost:8080/agendamento/${el}`, {
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
          window.alert("Agendamento deletado!")
          totalAgendamentos--

        }
        
    } catch (error) {alert("teste")
        console.error(error);
    }
    
    
  }

  function funcaoEditar(id){
    localStorage.setItem("idAgendamento", id)
    window.location.href= "../prestador/editar-servico.html"
  }



