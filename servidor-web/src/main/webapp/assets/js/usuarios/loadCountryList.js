/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/gulpfile.js to edit this template
 */
const nacionalitySelect = document.getElementById('select-nacionality');
const inputDescription = document.getElementById('input-description');

// Función para cargar y analizar el archivo CSV de forma asíncrona
const getCountryListFromCSV = async () => {
    // Verificar el valor seleccionado en el select
    const selectedValue = nacionalitySelect.value;
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
                // Verificar si el país coincide con el valor seleccionado
                if (nombre !== selectedValue) {
                    const option = document.createElement('option');
                    option.value = nombre.trim();
                    option.textContent = nombre.trim();
                    nacionalitySelect.appendChild(option);
                }
            }
        }
    } catch (error) {
        console.error(error);
    }
};

/* Se verifica si el inputDescription tiene algún valor. Ya que si tiene 
 * significa que se está dando de alta o modificando un proveedor y no es 
 * necesario cargar la lista de países. Ya que corresponde solo a los turistas.*/

if (inputDescription !== null) {
    if (inputDescription.value === ""){
        getCountryListFromCSV();
    }
} else {
    getCountryListFromCSV();
}