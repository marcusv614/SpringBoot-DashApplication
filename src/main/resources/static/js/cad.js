const formulario = document.getElementById("formCadastro");

// selecionar pelos IDs existentes no HTML
const inputs = {
  username: document.querySelector("#username"),
  pwd: document.querySelector("#pwd"),
};

function limparCampos() {
  Object.values(inputs).forEach((input) => (input.value = ""));
}

async function cadastrar(dados) {
  try {
    const resposta = await fetch("/cadastro", {
      // caminho relativo
      method: "POST",
      headers: {
        Accept: "application/json, text/plain",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(dados),
    });

    if (!resposta.ok) {
      // tenta ler mensagem de erro (json ou texto)
      let errText;
      try {
        errText = await resposta.text();
      } catch (e) {
        errText = resposta.statusText;
      }
      throw new Error(`Erro na requisição: ${resposta.status} - ${errText}`);
    }

    // backend pode retornar JSON ou texto; tenta JSON primeiro
    let resultado;
    try {
      resultado = await resposta.json();
    } catch (e) {
      resultado = await resposta.text();
    }

    console.log("Dados enviados com sucesso: ", resultado);
    return resultado;
  } catch (erro) {
    console.error("Erro ao cadastrar:", erro);
    throw erro;
  }
}

formulario.addEventListener("submit", async (event) => {
  event.preventDefault();

  // monta o objeto usando os names/ids do form
  const dados = {
    username: inputs.username.value,
    // aqui eu envio 'pwd' para bater com o name do input e com sua coluna 'pwd'
    pwd: inputs.pwd.value,
  };

  console.log("Dados do formulário:", dados);

  try {
    await cadastrar(dados);
    limparCampos();
    // opcional: redirecionar
    // window.location.href = "/login";
  } catch (err) {
    // mostrar mensagem pro usuário (implemente UI)
    alert("Falha ao cadastrar: " + (err.message || "erro desconhecido"));
  }
});
