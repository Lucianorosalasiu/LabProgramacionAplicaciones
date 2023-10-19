/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/gulpfile.js to edit this template
 */
const nacionalitySelect = document.getElementById('select-nacionality');

// Función para cargar y analizar el archivo CSV de forma asíncrona
const getCountryListFromCSV = async () => {
    try {
        const response = await fetch('assets/paises.csv');
        if (!response.ok) {
            throw new Error('Error al cargar el archivo CSV');
        }
        const csvContent = await response.text();
        const lines = csvContent.split('\n');

        for (let i = 1; i < lines.length; i++) {
            const line = lines[i].trim(); // Eliminar espacios en blanco
            const [nombre] = line.replace(/"/g, '').split(','); // Eliminar comillas y obtener el primer valor
            if (nombre) {
                const option = document.createElement('option');
                option.value = nombre.trim();
                option.textContent = nombre.trim();
                nacionalitySelect.appendChild(option);
            }
        }
    } catch (error) {
        console.error(error);
    }
};

getCountryListFromCSV();

