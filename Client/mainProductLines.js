
let guardar = document.getElementById("guardar");
let modificar = document.getElementById("modificar");

guardar.style.display = "block"
modificar.style.display = "none"

const getProductsLines = () => {
    $.ajax({
        type: 'GET',
        headers: { "Accept": "application/json" },
        url: 'http://localhost:8084/Actividad2_war_exploded/productsLine'
    }).done(res => {
        let listProductsLines = res;
        let table = $("#table");

        table.append(
        "<tr class='bg-info text-dark'>"+
        +"<th scope='col'>Product Line</th>"
        +"<th scope='col'>Text Description</th>"
        +"<th scope='col'>Html Description</th>"
        +"<th scope='col'>Image</th>"
        +"</tr>")

        for(let i = 0; i < listProductsLines.length; i++){
            table.append("<tr>"
            +"<td>"+res[i].productLine + "</td>"
            +"<td>"+res[i].textDescription + "</td>"
            +"<td>"+res[i].htmlDescription+"</td> "
            +"<td>"+res[i].image + "</td>"
            +"<td><button class='btn btn-warning' onclick='findById("+res[i].productLine+")'>Modificar</button></td>"
            +"<td><button class='btn btn-danger' onclick='delete("+res[i].productLine+")'>Borrar</button></td>"
            +"</tr>")
        }

    });
};

getProductsLines();

const findById = id => {
    $.ajax({
        type: 'GET',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/productsLine'+id
    }).done(res => {
        console.log(res)
        guardar.style.display = "none"
        modificar.style.display = "block"
        
        document.getElementById("productLine").value=res.productLine
        document.getElementById("textDescription").value=res.textDescription
        document.getElementById("htmlDescription").value=res.htmlDescription
        document.getElementById("image").value=res.image

    });
}


const save = () => {
    let productsLine = new Object();
    productsLine.productLine = document.getElementById("productLine").value
    productsLine.textDescription = document.getElementById("textDescription").value
    productsLine.htmlDescription = document.getElementById("htmlDescription").value
    productsLine.image = document.getElementById("image").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/productsLine/save',
        data: productsLine
    }).done(res => {
        console.log(res);
        guardar.style.display = "block"
        modificar.style.display = "none"
    });
}

/*const delete =() id => {
    $.ajax({
        type: 'POST',
        headers: { 
            "Accept": "application/json",
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/productsLine/delete/'+id
    }).done(res => {
        console.log(res);
    });
}*/


const update = () => {

    let productsLine = new Object();
    let id = document.getElementById("productLine").value
    productsLine.textDescription = document.getElementById("textDescription").value
    productsLine.htmlDescription = document.getElementById("htmlDescription").value
    productsLine.image = document.getElementById("image").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/productsLine/save'+id,
        data: productsLine
    }).done(res => {
        console.log(res)
        guardar.style.display = "block"
        modificar.style.display = "none"

        document.getElementById("productLine").value=""
        document.getElementById("textDescription").value=""
        document.getElementById("htmlDescription").value=""
        document.getElementById("image").value=""
        
    });
}

