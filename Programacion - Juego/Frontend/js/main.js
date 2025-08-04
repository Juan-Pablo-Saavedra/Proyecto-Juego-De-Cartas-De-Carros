// js/main.js

// Mostrar instrucciones
document.getElementById('btnInstructions').addEventListener('click', () => {
  alert(
    'Reglas del juego:\n' +
    '- Cada jugador escoge un atributo.\n' +
    '- Gana quien tenga la mejor estadística.\n' +
    '- Empate ⇒ pozo acumulado.'
  );
});

// Al pulsar “Jugar” redirige a la pantalla de selección de jugadores
document.getElementById('btnPlay').addEventListener('click', () => {
  window.location.href = 'selection.html';
});
