// Conexion a la API
const apiBase   = 'http://localhost:8080/api/games';
const btnBack   = document.getElementById('btnBack');
const btnCont   = document.getElementById('btnContinue');
const options   = document.querySelector('.options');
const infoMsg   = document.getElementById('infoMsg');

// boton pa regresar
btnBack.addEventListener('click', () => {
  window.location.href = 'index.html'; // Cambia 'index.html' por la ventana que desees
});

// Generar botones del modo ilustrativo (2–7 jugadores)
let selectedCount = null;
for (let i = 2; i <= 7; i++) {
  const btn = document.createElement('div');
  btn.className = 'option-btn';
  btn.innerText = i;
  btn.addEventListener('click', () => selectCount(i, btn));
  options.appendChild(btn);
}

function selectCount(count, btn) {
  // Limpiar selección previa
  document.querySelectorAll('.option-btn')
          .forEach(x => x.classList.remove('selected'));
  btn.classList.add('selected');

  if (count === 2) {
    selectedCount = 2;
    btnCont.disabled = false;
    infoMsg.innerText = '';
  } else {
    selectedCount = null;
    btnCont.disabled = true;
    infoMsg.innerText = 'Modo demo: solo disponible 1 vs 1';
  }
}

// Al pulsar Continuar → pide nombres en un modal, crea partida (API o demo) y redirige
btnCont.addEventListener('click', async () => {
  let modal = document.getElementById('nameModal');
  if (!modal) {
    modal = document.createElement('div');
    modal.id = 'nameModal';
    modal.style.position = 'fixed';
    modal.style.top = '0';
    modal.style.left = '0';
    modal.style.width = '100vw';
    modal.style.height = '100vh';
    modal.style.background = 'rgba(0,0,0,0.5)';
    modal.style.display = 'flex';
    modal.style.alignItems = 'center';
    modal.style.justifyContent = 'center';
    modal.innerHTML = `
      <div style="background:#fff;padding:2em;border-radius:8px;min-width:250px;text-align:center;">
        <h3>Ingresa los nombres de los jugadores</h3><br>
        <input id="player1" type="text" placeholder="Jugador 1" style="margin-bottom:1em;width:90%;padding:0.5em;" /><br>
        <input id="player2" type="text" placeholder="Jugador 2" style="margin-bottom:1em;width:90%;padding:0.5em;" /><br>
        <div id="modalError" style="color:red;height:1.5em;"></div>
        <button id="modalOk">Aceptar</button>
        <button id="modalCancel">Cancelar</button>
      </div>
    `;
    document.body.appendChild(modal);
  } else {
    modal.style.display = 'flex';
  }

  return new Promise((resolve) => {
    const okBtn = document.getElementById('modalOk');
    const cancelBtn = document.getElementById('modalCancel');
    const errorDiv = document.getElementById('modalError');
    const player1 = document.getElementById('player1');
    const player2 = document.getElementById('player2');

    function closeModal() {
      modal.style.display = 'none';
      okBtn.removeEventListener('click', onOk);
      cancelBtn.removeEventListener('click', onCancel);
    }

    function onOk() {
      const name1 = player1.value.trim();
      const name2 = player2.value.trim();
      if (!name1 || !name2) {
        errorDiv.textContent = 'Debes ingresar un nombre válido para ambos jugadores.';
        return;
      }
      closeModal();
      resolve([name1, name2]);
    }

    function onCancel() {
      closeModal();
      resolve(null);
    }

    okBtn.addEventListener('click', onOk);
    cancelBtn.addEventListener('click', onCancel);
  }).then(async (names) => {
    if (!names) return;

    // Intento de creación real
    let gameData;
    try {
      const res  = await fetch(apiBase, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ numberOfPlayers: 2, playerNames: names })
      });
      const json = await res.json();
      if (!res.ok) throw new Error(json.message || 'Error al crear partida');
      gameData = json;
    } catch (err) {
      // Fallback demo
      console.log(' Modo demo activado.');
      gameData = {
        gameId: 'demo_' + Date.now(),
        players: names.map(n => ({
          name: n,
          stats: {
            strength:      Math.floor(Math.random() * 100) + 1,
            speed:         Math.floor(Math.random() * 100) + 1,
            intelligence:  Math.floor(Math.random() * 100) + 1
          }
        }))
      };
    }

    // Guardar y redirigir a la pantalla de juego
    localStorage.setItem('currentGameData', JSON.stringify(gameData));
    window.location.href = 'game.html';
  });
});


