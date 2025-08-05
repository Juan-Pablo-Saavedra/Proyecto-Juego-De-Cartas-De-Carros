const apiBase     = 'http://localhost:8080/api/games';
const raw         = localStorage.getItem('currentGameData');
let gameData      = raw ? JSON.parse(raw) : null;

// Elementos del DOM
const name1       = document.getElementById('name1');
const name2       = document.getElementById('name2');
const strength1   = document.getElementById('strength1');
const strength2   = document.getElementById('strength2');
const speed1      = document.getElementById('speed1');
const speed2      = document.getElementById('speed2');
const intel1      = document.getElementById('intel1');
const intel2      = document.getElementById('intel2');
const resultDiv   = document.getElementById('result');
const attrButtons = document.querySelectorAll('.attr-btn');
const btnNewGame  = document.getElementById('btnNewGame');

async function loadGame() {
  if (!gameData) {
    alert('No hay datos de partida. Regresando a selección.');
    return window.location.href = 'selection.html';
  }

  // Si viene de API demo, no volvemos a fetch; si no, lo intentamos
  if (!gameData.players[0].stats) {
    try {
      const res  = await fetch(`${apiBase}/${gameData.gameId}`);
      const json = await res.json();
      if (!res.ok) throw new Error(json.message);
      gameData = json;
    } catch {
      alert('No se pudo cargar datos de la API. Usando demo.');
    }
  }

  renderGame();
}

function renderGame() {
  const [p1, p2] = gameData.players;

  // Mostrar nombres
  name1.innerText = p1.name;
  name2.innerText = p2.name;

  // Mostrar stats
  strength1.innerText = p1.stats.strength;
  strength2.innerText = p2.stats.strength;
  speed1.innerText    = p1.stats.speed;
  speed2.innerText    = p2.stats.speed;
  intel1.innerText    = p1.stats.intelligence;
  intel2.innerText    = p2.stats.intelligence;
}

// Comparar atributo al clic
attrButtons.forEach(btn => {
  btn.addEventListener('click', () => {
    const attr = btn.dataset.attr;
    const [p1, p2] = gameData.players;
    const v1 = p1.stats[attr];
    const v2 = p2.stats[attr];
    let msg;

    if (v1 > v2)      msg = `${p1.name} gana con ${v1} vs ${v2}`;
    else if (v2 > v1) msg = `${p2.name} gana con ${v2} vs ${v1}`;
    else              msg = `Empate: ${v1} vs ${v2}`;

    resultDiv.innerText = msg;
  });
});

// Nueva partida → volver a selección
btnNewGame.addEventListener('click', () => {
  window.location.href = 'selection.html';
});

// Iniciar todo
window.addEventListener('DOMContentLoaded', loadGame);

// Funcionalidad para mostrar ranking
async function loadRanking() {
  const rankingDiv = document.getElementById('ranking');
  if (!rankingDiv) return;

  try {
    const res = await fetch(`${apiBase}/ranking`);
    if (!res.ok) throw new Error('Error al obtener el ranking');
    const ranking = await res.json();
    if (!Array.isArray(ranking)) throw new Error('Ranking inválido');

    rankingDiv.innerHTML = '<h3>Ranking</h3><ol>' +
      ranking.map(player => `<li>${player.name}: ${player.wins} victorias</li>`).join('') +
      '</ol>';
  } catch (error) {
    rankingDiv.innerHTML = `<p>No se pudo cargar el ranking. ${error.message}</p>`;
  }
}

window.addEventListener('DOMContentLoaded', loadRanking);


