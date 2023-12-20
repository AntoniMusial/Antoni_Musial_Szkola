// Zdefiniuj plansze i statki
const playerBoard = document.getElementById('player-board');
const computerBoard = document.getElementById('computer-board');
const messagesContainer = document.querySelector('.messages');

const playerShips = [
    { size: 4, name: 'Czteromasztowiec' },
    { size: 3, name: 'Trzymasztowiec 1' },
    { size: 3, name: 'Trzymasztowiec 2' },
    { size: 2, name: 'Dwumasztowiec 1' },
    { size: 2, name: 'Dwumasztowiec 2' },
    { size: 2, name: 'Dwumasztowiec 3' },
    { size: 1, name: 'Jednomasztowiec 1' },
    { size: 1, name: 'Jednomasztowiec 2' },
    { size: 1, name: 'Jednomasztowiec 3' },
    { size: 1, name: 'Jednomasztowiec 4' }
];

let computerShips = generateComputerShips();

let playerMoves = 0;
let maxPlayerMoves = 30; // Maksymalna liczba ruchów gracza

// Funkcja do obsługi kliknięcia gracza
function handlePlayerClick(event) {
    if (playerMoves >= maxPlayerMoves) {
        displayMessage('Osiągnięto maksymalną liczbę ruchów. Gra zakończona.');
        alert('Osiągnięto maksymalną liczbę ruchów. Gra zakończona.');
        return;
    }

    const row = event.target.dataset.row;
    const col = event.target.dataset.col;

    const targetCell = document.querySelector(`#computer-board .cell[data-row="${row}"][data-col="${col}"]`);
    if (targetCell.classList.contains('hit') || targetCell.classList.contains('miss')) {
        // Pole już było trafione lub pudło, zignoruj kliknięcie
        return;
    }

    playerShoot(row, col);
    playerMoves++;

    updateMovesCounter(maxPlayerMoves - playerMoves);

    if (playerMoves >= maxPlayerMoves) {
        displayMessage('Osiągnięto maksymalną liczbę ruchów. Gra zakończona.');
        alert('Osiągnięto maksymalną liczbę ruchów. Gra zakończona.');
    }
}

// Funkcja do aktualizacji licznika pozostałych ruchów
function updateMovesCounter(movesLeft) {
    const movesCounter = document.getElementById('moves-counter');
    movesCounter.textContent = `Pozostało ruchów: ${movesLeft}`;
}

// Funkcja inicjalizująca planszę
function initializeBoard(board, player) {
    // Dodaj oznaczenia kolumn (litery od A do J) nad planszą
    const columnLabels = document.createElement('div');
    columnLabels.classList.add('column-labels');
    for (let i = 65; i < 75; i++) {
        const label = document.createElement('div');
        label.classList.add('label');
        label.textContent = String.fromCharCode(i);
        columnLabels.appendChild(label);
    }
    board.appendChild(columnLabels);

    for (let i = 1; i <= 10; i++) {
        const row = document.createElement('div');
        row.classList.add('row');

        // Dodaj oznaczenia wierszy (liczby od 1 do 10) po lewej stronie planszy
        const rowLabel = document.createElement('div');
        rowLabel.classList.add('label', 'row-label');
        rowLabel.textContent = i;
        row.appendChild(rowLabel);

        for (let j = 1; j <= 10; j++) {
            const cell = document.createElement('div');
            cell.classList.add('cell');
            cell.dataset.row = i;
            cell.dataset.col = j;

            // Dodaj listener do obsługi najechania kursorem na komórkę gracza
            if (player === 'player') {
                cell.addEventListener('mouseover', handleCellHover);
            }

            // Dodaj listener do obsługi kliknięcia na komórkę gracza
            if (player === 'player') {
                cell.addEventListener('click', handlePlayerClick);
            }

            row.appendChild(cell);
        }
        board.appendChild(row);
    }
}

// Funkcja do obsługi najechania kursorem na komórkę gracza
function handleCellHover(event) {
    const row = event.target.dataset.row;
    const col = event.target.dataset.col;

    // Wyświetl numer wiersza i symbol kolumny
    displayCellCoordinates(row, col);
}

// Funkcja do ukrywania numeru wiersza i symbolu kolumny po opuszczeniu komórki
function handleCellLeave() {
    // Ukryj numer wiersza i symbol kolumny
    hideCellCoordinates();
}

// Funkcja do wyświetlania numeru wiersza i symbolu kolumny
function displayCellCoordinates(row, col) {
    const coordinatesDisplay = document.getElementById('coordinates-display');
    coordinatesDisplay.textContent = `${String.fromCharCode(64 + parseInt(col))}-${row}`;
}

// Funkcja do ukrywania numeru wiersza i symbolu kolumny
function hideCellCoordinates() {
    const coordinatesDisplay = document.getElementById('coordinates-display');
    coordinatesDisplay.textContent = '';
}

// Wywołaj funkcję generującą statki komputera
computerShips = generateComputerShips();

// Inicjalizacja plansz
initializeBoard(playerBoard, 'player');
initializeBoard(computerBoard, 'computer');

// Funkcja do losowego rozmieszczenia statków komputera
function generateComputerShips() {
    const ships = [];

    // Funkcja do sprawdzania czy dane pole jest dostępne
    function isPositionAvailable(row, col, size, isVertical, usedPositions) {
        const positionsToCheck = [];
        for (let i = 0; i < size; i++) {
            const newRow = isVertical ? row + i : row;
            const newCol = isVertical ? col : col + i;
            positionsToCheck.push(`${newRow}-${newCol}`);
        }

        return positionsToCheck.every(pos => {
            const [r, c] = pos.split('-');
            return (
                r >= 1 &&
                r <= 10 &&
                c >= 1 &&
                c <= 10 &&
                !usedPositions.has(pos) &&
                !usedPositions.has(`${r - 1}-${c - 1}`) &&
                !usedPositions.has(`${r - 1}-${c}`) &&
                !usedPositions.has(`${r - 1}-${c + 1}`) &&
                !usedPositions.has(`${r}-${c - 1}`) &&
                !usedPositions.has(`${r}-${c + 1}`) &&
                !usedPositions.has(`${r + 1}-${c - 1}`) &&
                !usedPositions.has(`${r + 1}-${c}`) &&
                !usedPositions.has(`${r + 1}-${c + 1}`)
            );
        });
    }

    // Losowe rozmieszczenie statków
    for (const ship of playerShips) {
        const usedPositions = new Set(); // Utwórz nowy zestaw użytych pozycji dla każdego statku
        const isVertical = Math.random() < 0.5; // 50% szans na pionowe rozmieszczenie
        let row, col;

        do {
            row = Math.floor(Math.random() * 10) + 1;
            col = Math.floor(Math.random() * 10) + 1;
        } while (!isPositionAvailable(row, col, ship.size, isVertical, usedPositions));

        // Zajmij pola przez statek
        for (let i = 0; i < ship.size; i++) {
            const newRow = isVertical ? row + i : row;
            const newCol = isVertical ? col : col + i;
            usedPositions.add(`${newRow}-${newCol}`);
        }

        ships.push({
            name: ship.name,
            positions: usedPositions
        });
    }

    return ships;
}

// Funkcja do obsługi strzału gracza
function playerShoot(row, col) {
    const targetCell = document.querySelector(`#computer-board .cell[data-row="${row}"][data-col="${col}"]`);

    // Sprawdź, czy to pole nie było już wcześniej trafione lub zatopione
    if (targetCell.classList.contains('hit') || targetCell.classList.contains('miss')) {
        // Pole już było trafione lub pudło, możesz obsłużyć to zdarzenie lub zignorować
        return;
    }

    if (isHit(row, col, computerShips)) {
        // Jeśli trafiono statek przeciwnika
        targetCell.classList.add('hit');
        targetCell.textContent = '⭕'; // Oznacz pole jako trafione

        displayMessage('Trafiony!');

        // Sprawdź, czy statek został zatopiony
        if (isShipSunk(row, col, computerShips)) {
            targetCell.textContent = '❌'; // Oznacz pole jako trafione
            displayMessage('Zatopiony!');
        }
    } else {
        // Jeśli nie trafiono
        targetCell.classList.add('miss');
        targetCell.textContent = '🔹'; // Oznacz pole jako pudło

        displayMessage('Pudło!');
    }
}

// Funkcja do ustawiania ikony dla pól zatopionego statku
function setShipSunkColor(row, col, ships) {
    for (const ship of ships) {
        if (ship.positions.has(`${row}-${col}`)) {
            ship.positions.forEach(pos => {
                const [r, c] = pos.split('-');
                const sunkCell = document.querySelector(`#computer-board .cell[data-row="${r}"][data-col="${c}"]`);
                sunkCell.textContent = '❌'; // Oznacz pole jako zatopione
            });
            return;
        }
    }
}

// Funkcja sprawdzająca, czy dane pole zawiera statek przeciwnika
function isHit(row, col, ships) {
    const position = `${row}-${col}`;
    for (const ship of ships) {
        if (ship.positions.has(position)) {
            return true; // Trafiono statek
        }
    }
    return false; // Nie trafiono
}

// Funkcja sprawdzająca, czy statek przeciwnika został zatopiony
function isShipSunk(row, col, ships) {
    const position = `${row}-${col}`;
    for (const ship of ships) {
        ship.positions.delete(position); // Usuń trafione pole z pozycji statku

        if (ship.positions.size === 0) {
            // Jeśli wszystkie pola statku zostały trafione, statek został zatopiony
            return true;
        }
    }
    return false;
}

// Funkcja do wyświetlania komunikatów
function displayMessage(message) {
    const messageElement = document.createElement('div');
    messageElement.textContent = message;
    messagesContainer.appendChild(messageElement);
}

function redirectToPage() {
    window.location.href = 'index.html';
}