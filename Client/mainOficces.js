
let guardar = document.getElementById("guardar");
let modificar = document.getElementById("modificar");

guardar.style.display = "block"
modificar.style.display = "none"

const getOffices = () => {
    $.ajax({
        type: 'GET',
        headers: { "Accept": "application/json" },
        url: 'http://localhost:8084/Actividad2_war_exploded/offices'
    }).done(res => {
        let listOffices = res;
        let table = $("#table");

        table.append(
        "<tr class='bg-info text-dark'>"+
        +"<th scope='col'>Office Code</th>"
        +"<th scope='col'>City</th>"
        +"<th scope='col'>Phone</th>"
        +"<th scope='col'>Address Line1</th>"
        +"<th scope='col'>Address Line2</th>"
        +"<th scope='col'>State</th>"
        +"<th scope='col'>Country</th>"
        +"<th scope='col'>Postal Code</th>"
        +"<th scope='col'>Territory</th>"
        +"<th scope='col'>Modificar</th>"
        +"<th scope='col'>Borrar</th>"
        +"</tr>")

        for(let i = 0; i < listOffices.length; i++){
            table.append("<tr>"
            +"<td>"+res[i].officeCode + "</td>"
            +"<td>"+res[i].city + "</td>"
            +"<td>"+res[i].phone+"</td> "
            +"<td>"+res[i].addressLine1 + "</td>"
            +"<td>"+res[i].addressLine2+ "</td>"
            +"<td>"+res[i].state+"</td>"
            +"<td>"+res[i].country+"</td>"
            +"<td>"+res[i].postalCode + "</td>"
            +"<td>"+res[i].territory + "</td>"
            +"<td><button class='btn btn-warning' onclick='findById("+res[i].officeCode+")'>Modificar</button></td>"
            +"<td><button class='btn btn-danger' onclick='delete("+res[i].officeCode+")'>Borrar</button></td>"
            +"</tr>")
        }

    });
};

getOffices();

const findById = id => {
    $.ajax({
        type: 'GET',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/offices'+id
    }).done(res => {
        console.log(res)
        guardar.style.display = "none"
        modificar.style.display = "block"
        
        document.getElementById("officeCode").value=res.officeCode
        document.getElementById("city").value=res.city
        document.getElementById("phone").value=res.phone
        document.getElementById("addressLine1").value=res.addressLine1
        document.getElementById("addressLine2").value=res.addressLine2
        document.getElementById("state").value=res.state
        document.getElementById("country").value=res.country
        document.getElementById("postalCode").value=res.postalCode
        document.getElementById("territory").value=res.territory
       
        
    });
}


const save = () => {
    let offices = new Object();
    offices.officeCode = document.getElementById("officeCode").value
    offices.city = document.getElementById("city").value
    offices.phone = document.getElementById("phone").value
    offices.addressLine1 = document.getElementById("addressLine1").value
    offices.addressLine2 = document.getElementById("addressLine2").value
    offices.state = document.getElementById("state").value
    offices.country = document.getElementById("country").value
    offices.postalCode = document.getElementById("postalCode").value
    offices.territory = document.getElementById("territory").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/offices/save',
        data: offices
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
        url: 'http://localhost:8084/Actividad2_war_exploded/offices/delete/'+id
    }).done(res => {
        console.log(res);
    });
}*/


const update = () => {

    let offices = new Object();
    let id = document.getElementById("officeCode").value
    offices.city = document.getElementById("city").value
    offices.phone = document.getElementById("phone").value
    offices.addressLine1 = document.getElementById("addressLine1").value
    offices.addressLine2 = document.getElementById("addressLine2").value
    offices.state = document.getElementById("state").value
    offices.country = document.getElementById("country").value
    offices.postalCode = document.getElementById("postalCode").value
    offices.territory = document.getElementById("territory").value
    

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/offices/save'+id,
        data: offices
    }).done(res => {
        console.log(res)
        guardar.style.display = "block"
        modificar.style.display = "none"

        document.getElementById("officeCode").value=""
        document.getElementById("city").value=""
        document.getElementById("phone").value=""
        document.getElementById("addressLine1").value=""
        document.getElementById("addressLine2").value=""
        document.getElementById("state").value=""
        document.getElementById("country").value=""
        document.getElementById("postalCode").value=""
        document.getElementById("territory").value=""
    
        
    });
}

