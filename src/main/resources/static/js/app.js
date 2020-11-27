const app = (() => {
    let object = {}
    let containers = [];
    let alertas = [];
    const addToTable = (apps) => {
        console.log(apps)
        let table = $("#containers > tbody");
        table.empty();
        containers = apps.data.map(({AppName, gitRepo, url}) => ({
            AppName: AppName,
            gitRepo: gitRepo,
            url: url,
        }))
        containers.forEach(({AppName, gitRepo, url}) => {
            table.append(
                `<tr> 
                  <td>${AppName}</td>
                  <td>${gitRepo}</td>
                  <td><a href="${url}">${url}</a></td>
                </tr>`
            );
        })
        console.log("holaaa")
    }
    const mapOpjects = (alerts) => {
        let alertUl = $("#estadisticas");
        alertUl.empty();
        alertUl.append(
            `<li class="list-group-item">Temperatura: ${alerts.data.temp}</li>
            <li class="list-group-item">Luz: ${alerts.data.light}</li>
            <li class="list-group-item">Humedad: ${alerts.data.humidity}</li>
            <li class="list-group-item">Humedad del suelo: ${alerts.data.ground}</li>
            <li class="list-group-item">Altura de la planta: ${alerts.data.proximity}</li>
            `
        )
        let precauciones = $("#alertas");
        precauciones.empty();
        alertas = alerts.data.affected;
        if (alertas.temp !== undefined) {
            precauciones.append(`<li class="list-group-item">🔥Alerta Temperatura Actual: ${alertas.temp}</li>`);
        }
        if (alertas.light !== undefined) {
            precauciones.append(`<li class="list-group-item">☀Necesito Luz, Luz actual: ${alertas.light}</li>`);
        }
        if (alertas.humidity !== undefined) {
            precauciones.append(`<li class="list-group-item">💧 Algo raro pasó, humedad actual: ${alertas.humidity}</li>`);
        }
        if (alertas.ground !== undefined) {
            precauciones.append(`<li class="list-group-item">💧 Necesito agua, Humedad del suelo: ${alertas.ground}</li>`);
        }
        if (alertas.proximity !== undefined) {
            precauciones.append(`<li class="list-group-item">✂Necesito un corte, Altura: ${alertas.proximity}</li>`);
        }

    }
    const init = () => {
        cloudservice.poolData(mapOpjects);
    }
    return {
        init: init
    }
})();
