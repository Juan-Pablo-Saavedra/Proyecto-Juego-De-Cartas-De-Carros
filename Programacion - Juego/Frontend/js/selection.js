// Conexion a la API
const apiBase   = 'http://localhost:8085/api/games';
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

// Al pulsar Continuar → pide nombres, crea partida (API o demo) y redirige
btnCont.addEventListener('click', async () => {
  // Recoger nombres
  const names = [];
  for (let i = 1; i <= 2; i++) {
    const name = prompt(`Nombre del jugador ${i}:`);
    if (!name || !name.trim()) {
      alert('Debes ingresar un nombre válido.');
      return;
    }
    names.push(name.trim());
  }

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
    alert(' Modo demo activado.');
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


