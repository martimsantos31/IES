import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import './app.css'; // Certifique-se de que este arquivo exista

import Game from './app.jsx'; // Verifique o nome e localização do arquivo App.jsx

const root = createRoot(document.getElementById('root'));
root.render(
    <StrictMode>
        <Game />
    </StrictMode>
);


