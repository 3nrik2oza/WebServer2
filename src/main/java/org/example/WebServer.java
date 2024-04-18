package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/games")
public class WebServer {
    @GetMapping("ticTacToe")
    public String hello(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Tic Tac Toe</title>\n" +
                "<style>\n" +
                "    .board {\n" +
                "        display: grid;\n" +
                "        grid-template-columns: repeat(3, 100px);\n" +
                "        grid-template-rows: repeat(3, 100px);\n" +
                "        gap: 5px;\n" +
                "    }\n" +
                "    .cell {\n" +
                "        width: 100px;\n" +
                "        height: 100px;\n" +
                "        border: 1px solid black;\n" +
                "        display: flex;\n" +
                "        justify-content: center;\n" +
                "        align-items: center;\n" +
                "        font-size: 24px;\n" +
                "        cursor: pointer;\n" +
                "    }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"board\" id=\"board\">\n" +
                "    <div class=\"cell\" data-position=\"0\"></div>\n" +
                "    <div class=\"cell\" data-position=\"1\"></div>\n" +
                "    <div class=\"cell\" data-position=\"2\"></div>\n" +
                "    <div class=\"cell\" data-position=\"3\"></div>\n" +
                "    <div class=\"cell\" data-position=\"4\"></div>\n" +
                "    <div class=\"cell\" data-position=\"5\"></div>\n" +
                "    <div class=\"cell\" data-position=\"6\"></div>\n" +
                "    <div class=\"cell\" data-position=\"7\"></div>\n" +
                "    <div class=\"cell\" data-position=\"8\"></div>\n" +
                "</div>\n" +
                "<div>\n" +
                "    <label for=\"sign\">Choose your sign:</label>\n" +
                "    <select id=\"sign\">\n" +
                "        <option value=\"X\">X</option>\n" +
                "        <option value=\"O\">O</option>\n" +
                "    </select>\n" +
                "</div>\n" +
                "<script>\n" +
                "document.addEventListener('DOMContentLoaded', function() {\n" +
                "    const cells = document.querySelectorAll('.cell');\n" +
                "    const signSelect = document.getElementById('sign');\n" +
                "    let currentPlayerSign = signSelect.value;\n" +
                "\n" +
                "    signSelect.addEventListener('change', function() {\n" +
                "        currentPlayerSign = signSelect.value;\n" +
                "    });\n" +
                "    \n" +
                "    cells.forEach(cell => {\n" +
                "        cell.addEventListener('click', function() {\n" +
                "            if (!cell.textContent) {\n" +
                "                const position = cell.dataset.position;\n" +
                "                sendDataToBackend(position, currentPlayerSign, cell);\n" +
                "            }\n" +
                "        });\n" +
                "    });\n" +
                "\n" +
                "    function sendDataToBackend(position, sign, cell) {\n" +
                "        fetch('http://localhost:8080/move', {\n" +
                "            method: 'POST',\n" +
                "            headers: {\n" +
                "                'Content-Type': 'application/json'\n" +
                "            },\n" +
                "            body: JSON.stringify({ position, sign })\n" +
                "        })\n" +
                "        .then(response => {\n" +
                "            if (!response.ok) {\n" +
                "                throw new Error('Network response was not ok');\n" +
                "            }\n" +
                "            return response.json();\n" +
                "        })\n" +
                "        .then(data => {\n" +
                "            cell.textContent = sign;\n" +
                "        })\n" +
                "        .catch(error => {\n" +
                "            console.error('There was a problem with your fetch operation:', error);\n" +
                "        });\n" +
                "    }\n" +
                "});\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n" +
                "\n";
    }


    @GetMapping("snake")
    public String snakeGame(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Snake Game</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      display: flex;\n" +
                "      justify-content: center;\n" +
                "      align-items: center;\n" +
                "      height: 100vh;\n" +
                "      margin: 0;\n" +
                "    }\n" +
                "    #game-board {\n" +
                "      border: 1px solid black;\n" +
                "      background-color: #eee;\n" +
                "    }\n" +
                "    .cell {\n" +
                "      width: 20px;\n" +
                "      height: 20px;\n" +
                "      border: 1px solid #ccc;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div id=\"game-board\"></div>\n" +
                "  <script>\n" +
                "    const ROWS = 20;\n" +
                "    const COLS = 20;\n" +
                "    const CELL_SIZE = 20;\n" +
                "    \n" +
                "    const board = document.getElementById('game-board');\n" +
                "    let snake = [{ x: 10, y: 10 }];\n" +
                "    let food = { x: 5, y: 5 };\n" +
                "    let dx = 0;\n" +
                "    let dy = 0;\n" +
                "    let intervalId;\n" +
                "\n" +
                "    function drawBoard() {\n" +
                "      board.innerHTML = '';\n" +
                "      for (let y = 0; y < ROWS; y++) {\n" +
                "        for (let x = 0; x < COLS; x++) {\n" +
                "          const cell = document.createElement('div');\n" +
                "          cell.classList.add('cell');\n" +
                "          cell.style.width = CELL_SIZE + 'px';\n" +
                "          cell.style.height = CELL_SIZE + 'px';\n" +
                "          cell.style.top = (y * CELL_SIZE) + 'px';\n" +
                "          cell.style.left = (x * CELL_SIZE) + 'px';\n" +
                "          board.appendChild(cell);\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    function drawSnake() {\n" +
                "      snake.forEach(segment => {\n" +
                "        const cell = document.createElement('div');\n" +
                "        cell.classList.add('cell');\n" +
                "        cell.style.width = CELL_SIZE + 'px';\n" +
                "        cell.style.height = CELL_SIZE + 'px';\n" +
                "        cell.style.top = (segment.y * CELL_SIZE) + 'px';\n" +
                "        cell.style.left = (segment.x * CELL_SIZE) + 'px';\n" +
                "        cell.style.backgroundColor = 'green';\n" +
                "        board.appendChild(cell);\n" +
                "      });\n" +
                "    }\n" +
                "\n" +
                "    function drawFood() {\n" +
                "      const cell = document.createElement('div');\n" +
                "      cell.classList.add('cell');\n" +
                "      cell.style.width = CELL_SIZE + 'px';\n" +
                "      cell.style.height = CELL_SIZE + 'px';\n" +
                "      cell.style.top = (food.y * CELL_SIZE) + 'px';\n" +
                "      cell.style.left = (food.x * CELL_SIZE) + 'px';\n" +
                "      cell.style.backgroundColor = 'red';\n" +
                "      board.appendChild(cell);\n" +
                "    }\n" +
                "\n" +
                "    function updateSnake() {\n" +
                "      const head = { x: snake[0].x + dx, y: snake[0].y + dy };\n" +
                "      snake.unshift(head);\n" +
                "      if (head.x === food.x && head.y === food.y) {\n" +
                "        generateFood();\n" +
                "      } else {\n" +
                "        snake.pop();\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    function generateFood() {\n" +
                "      food.x = Math.floor(Math.random() * COLS);\n" +
                "      food.y = Math.floor(Math.random() * ROWS);\n" +
                "    }\n" +
                "\n" +
                "    function checkCollision() {\n" +
                "      const head = snake[0];\n" +
                "      if (head.x < 0 || head.x >= COLS || head.y < 0 || head.y >= ROWS) {\n" +
                "        return true;\n" +
                "      }\n" +
                "      for (let i = 1; i < snake.length; i++) {\n" +
                "        if (head.x === snake[i].x && head.y === snake[i].y) {\n" +
                "          return true;\n" +
                "        }\n" +
                "      }\n" +
                "      return false;\n" +
                "    }\n" +
                "\n" +
                "    function gameLoop() {\n" +
                "      if (checkCollision()) {\n" +
                "        clearInterval(intervalId);\n" +
                "        alert('Game Over!');\n" +
                "        return;\n" +
                "      }\n" +
                "      board.innerHTML = '';\n" +
                "      updateSnake();\n" +
                "      drawSnake();\n" +
                "      drawFood();\n" +
                "    }\n" +
                "\n" +
                "    function startGame() {\n" +
                "      drawBoard();\n" +
                "      generateFood();\n" +
                "      intervalId = setInterval(gameLoop, 100);\n" +
                "      document.addEventListener('keydown', (event) => {\n" +
                "        const key = event.key;\n" +
                "        if (key === 'ArrowUp' && dy !== 1) {\n" +
                "          dx = 0;\n" +
                "          dy = -1;\n" +
                "        } else if (key === 'ArrowDown' && dy !== -1) {\n" +
                "          dx = 0;\n" +
                "          dy = 1;\n" +
                "        } else if (key === 'ArrowLeft' && dx !== 1) {\n" +
                "          dx = -1;\n" +
                "          dy = 0;\n" +
                "        } else if (key === 'ArrowRight' && dx !== -1) {\n" +
                "          dx = 1;\n" +
                "          dy = 0;\n" +
                "        }\n" +
                "      });\n" +
                "    }\n" +
                "\n" +
                "    startGame();\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>";
    }

}



