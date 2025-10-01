// Esperar a que cada fragmento se cargue
document.querySelectorAll(".table-wrapper").forEach(wrapper => {
    const observer = new MutationObserver((mutationsList, observer) => {
        mutationsList.forEach(mutation => {
            if (mutation.addedNodes.length) {
                ajustarTablas();
            }
        });
    });
    observer.observe(wrapper, { childList: true });
});

// También ajustar al DOMContentLoaded por si ya hay tablas
document.addEventListener("DOMContentLoaded", ajustarTablas);

document.querySelectorAll('input.plain').forEach(input => {
    input.addEventListener('focus', function () {
        // Mover cursor al inicio
        const val = this.value;
        this.setSelectionRange(0, 0);
    });
});
/*function openTab(tabId) {
    const contents = document.querySelectorAll('.tab-content');
    contents.forEach(div => div.style.display = 'none');
    document.getElementById(tabId).style.display = 'block';
}*/

document.querySelector("form[action='/guardar']").addEventListener("submit", async function (e) {
    e.preventDefault();

    // Mostrar overlay de carga
    const overlay = document.getElementById("overlay-loading");
    if (overlay) overlay.style.display = "flex";

    const inputs = document.querySelectorAll("input[data-tabla]");
    const cambiosPorTabla = {};

    // Recoger cambios
    inputs.forEach(input => {
        const name = input.name;
        const original = input.dataset.original ?? "";
        const nuevo = input.value;
        if (original !== nuevo) {
            const match = name.match(/items\[(\d+)]\.(\w+)/);
            if (!match) return;

            const index = match[1];
            const campo = match[2];
            const tabla = input.dataset.tabla;

            if (!cambiosPorTabla[tabla]) cambiosPorTabla[tabla] = [];
            if (!cambiosPorTabla[tabla][index]) cambiosPorTabla[tabla][index] = {};
            cambiosPorTabla[tabla][index][campo] = nuevo;

            // Asegurar PKs (igual que tu código original)
            const pkCamposPorTabla = {
                "GCCTRNMS00": ["ftrntrnñ"],
                "T03OG3P": ["spare3"],
                "T02OG2P": ["spare3"],
                "T01OG1P": ["spare3","cntpsq","crtid","fcasplsp"],
                "GCCCASHD00": ["fcastrnñ"],
                "GCCCASDT00": ["fcastrnñ","fcaslpn"],
                "GCMBTHHD00": ["fbthbthñ"],
                "T07SPRP": ["spare3","cntpsq"],
                "GCCSRTMS00": ["fsrttrn","fsrtcids"],
                "GCMBTHDT00": ["fbtdbth","fbtdtrn"]
            };
            if (pkCamposPorTabla[tabla]) {
                pkCamposPorTabla[tabla].forEach(pk => {
                    if (!cambiosPorTabla[tabla][index][pk]) {
                        const pkInput = document.querySelector(`input[name='items[${index}].${pk}']`);
                        if (pkInput) cambiosPorTabla[tabla][index][pk] = pkInput.value;
                    }
                });
            }
        }
    });

    // Enviar cambios por tabla
    for (let tabla of Object.keys(cambiosPorTabla)) {
        const listaCambios = cambiosPorTabla[tabla].filter(Boolean).filter(cambio => {
            const claves = Object.keys(cambio);
            const clavesSinPK = claves.filter(k => !["ftrntrnñ", "spare3", "fcastrnñ", "fcaslpn", "fbthbthñ", "fsrttrn", "fsrtcids", "cntpsq"].includes(k));
            return clavesSinPK.length > 0;
        });
        if (listaCambios.length === 0) continue;

        let endpoint = "";
        switch (tabla) {
            case "GCCTRNMS00": endpoint = "/guardar/trnm"; break;
            case "T03OG3P": endpoint = "/guardar/t03"; break;
            case "T02OG2P": endpoint = "/guardar/t02"; break;
            case "T01OG1P": endpoint = "/guardar/t01"; break;
            case "GCCCASHD00": endpoint = "/guardar/chd"; break;
            case "GCCCASDT00": endpoint = "/guardar/cdt"; break;
            case "T07SPRP": endpoint = "/guardar/t07"; break;
            case "GCCSRTMS00": endpoint = "/guardar/sm"; break;
            case "GCMBTHHD00": endpoint = "/guardar/bthh"; break;
            case "GCMBTHDT00": endpoint = "/guardar/bth"; break;
        }

        if (endpoint) {
            await fetch(endpoint, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(listaCambios)
            });
        }
    }

    // Ajustar las tablas después de enviar cambios
    function ajustarTablas() {
        document.querySelectorAll(".table").forEach(tbl => {
            tbl.style.tableLayout = "auto";
            tbl.style.width = "100%";
            tbl.querySelectorAll("th, td").forEach(td => {
                td.style.whiteSpace = "normal";
                td.style.wordWrap = "break-word";
                td.style.maxWidth = "200px";
                td.style.overflow = "hidden";
                td.style.textOverflow = "ellipsis";
            });
        });
    }

    ajustarTablas();

    // Ocultar overlay después de un pequeño delay
    setTimeout(() => {
        if (overlay) overlay.style.display = "none";
    }, 500);
});


document.querySelectorAll("input[name$='.fbtdtrn']").forEach(input => {
    input.addEventListener("click", function () {
        const valorCompleto = this.value.trim();
        if (!valorCompleto || valorCompleto.length < 11) return;

        // Extrae los caracteres del 4 al 11: TP035530
        const trenValido = valorCompleto.substring(3, 11);

        // Llena el input de filtro
        const filtroInput = document.querySelector("form[action='/consulta'] input[name='filtro']");
        if (!filtroInput) return;

        filtroInput.value = trenValido;

        // Envía el formulario automáticamente
        filtroInput.form.submit();
    });
});
document.addEventListener('DOMContentLoaded', function () {
    const tipoFiltro = document.getElementById('tipoFiltro');
    const campoFiltro = document.getElementById('campoFiltro');

    if (tipoFiltro && campoFiltro) {
        tipoFiltro.addEventListener('change', function () {
            campoFiltro.value = ''; // limpia el campo

            // cambia el placeholder según la opción
            if (tipoFiltro.value === 'tren') {
                campoFiltro.placeholder = 'Escribe el número de tren';
            } else if (tipoFiltro.value === 'batch') {
                campoFiltro.placeholder = 'Escribe el número de batch';
            }
        });
    }
});
document.querySelectorAll("input").forEach(input => {
    const valorOriginal = input.getAttribute("data-original");

    input.addEventListener("change", function () {
        const nuevoValor = this.value;
        const campo = this.name;
        const tabla = this.getAttribute("data-tabla");

        // Obtener el tren desde la fila
        const fila = this.closest("tr");
        const trenInput = fila.querySelector("[name*='ftrntrnñ'], [name*='spare3'], [name*='fcastrnñ'], [name*='fcaslpn'], [name*='fbthbthñ'], [name*='fsrttrn'], [name*='fsrtcids'], [name*='cntpsq']");
        const tren = trenInput ? trenInput.value : "DESCONOCIDO";

        if (valorOriginal !== nuevoValor) {
            console.log("Tren detectado:", tren);
            fetch("/registro-movimiento", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    accion: "UPDATE_CAMPO",
                    detalle: `Tabla: ${tabla}, Campo: ${campo}, Valor original: ${valorOriginal}, Valor nuevo: ${nuevoValor}`,
                    tren: tren // ✅ ahora sí el backend recibirá este campo
                })
            });
        }
    });
});

// Función para ajustar las columnas de todas las tablas
function ajustarTablas() {
    document.querySelectorAll(".table-wrapper .table").forEach(tbl => {
        tbl.style.tableLayout = "auto";
        tbl.style.width = "100%";
        tbl.querySelectorAll("th, td").forEach(td => {
            td.style.whiteSpace = "normal";
            td.style.wordWrap = "break-word";
            td.style.maxWidth = "200px";
            td.style.overflow = "hidden";
            td.style.textOverflow = "ellipsis";
        });
    });
}












