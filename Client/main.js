
let guardar = document.getElementById("guardar");
let modificar = document.getElementById("modificar");

guardar.style.display = "block"
modificar.style.display = "none"

const getCustomers = () => {
    $.ajax({
        type: 'GET',
        headers: { "Accept": "application/json" },
        url: 'http://localhost:8084/Actividad2_war_exploded/customers'
    }).done(res => {
        let listCEmployees = res;
        let table = $("#table");

        table.append(
        "<tr class='bg-info text-dark'>"+
        +"<th scope='col'></th>"
        +"<th scope='col'>#</th>"
        +"<th scope='col'>Name</th>"
        +"<th scope='col'>Contact Last Name</th>"
        +"<th scope='col'>Contact First Name</th>"
        +"<th scope='col'>Phone</th>"
        +"<th scope='col'>Address</th>"
        +"<th scope='col'>City</th>"
        +"<th scope='col'>Country</th>"
        +"<th scope='col'>sales Rep Employee Number</th>"
        +"<th scope='col'>credit Limit</th>"
        +"<th scope='col'>Modificar</th>"
        +"<th scope='col'>Borrar</th>"
        +"</tr>")

        for(let i = 0; i < listEmployees.length; i++){
            table.append("<tr>"
            +"<td>"+res[i].customerNumber + "</td>"
            +"<td>"+res[i].customerName + "</td>"
            +"<td>"+res[i].contactLastName+"</td> "
            +"<td>"+res[i].contactFirstName + "</td>"
            +"<td>"+res[i].phone + "</td>"
            +"<td>"+res[i].addressLine1 + "</td>"
            +"<td>"+res[i].city+"</td>"
            +"<td>"+res[i].country+"</td>"
            +"<td>"+res[i].salesRepEmployeeNumber + "</td>"
            +"<td>"+res[i].creditLimit + "</td>"
            +"<td><button class='btn btn-warning' onclick='findById("+res[i].customerNumber+")'>Modificar</button></td>"
            +"<td><button class='btn btn-danger' onclick='delete("+res[i].customerNumber+")'>Borrar</button></td>"
            +"</tr>")
        }

    });
};

getEmployees();

const findById = id => {
    $.ajax({
        type: 'GET',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/customers'+id
    }).done(res => {
        console.log(res)
        guardar.style.display = "none"
        modificar.style.display = "block"
        
        document.getElementById("customerNumber").value=res.customerNumber
        document.getElementById("customerName").value=res.customerName
        document.getElementById("contactLastName").value=res.contactLastName
        document.getElementById("contactFirstName").value=res.contactFirstName
        document.getElementById("phone").value=res.phone
        document.getElementById("addressLine1").value=res.addressLine1
        document.getElementById("addressLine2").value=res.addressLine2
        document.getElementById("city").value=res.city
        document.getElementById("state").value=res.state
        document.getElementById("postalCode").value=res.postalCode
        document.getElementById("country").value=res.country
        document.getElementById("salesRepEmployeeNumber").value=res.salesRepEmployeeNumber
        document.getElementById("creditLimit").value=res.creditLimit
        
    });
}


const save = () => {
    let customer = new Object();
    customer.customerNumber = document.getElementById("customerNumber").value
    customer.customerName = document.getElementById("customerName").value
    customer.contactLastName = document.getElementById("contactLastName").value
    customer.contactFirstName = document.getElementById("contactFirstName").value
    customer.phone = document.getElementById("phone").value
    customer.addressLine1 = document.getElementById("addressLine1").value
    customer.addressLine2 = document.getElementById("addressLine2").value
    customer.city = document.getElementById("city").value
    customer.state = document.getElementById("state").value
    customer.postalCode = document.getElementById("postalCode").value
    customer.country = document.getElementById("country").value
    customer.salesRepEmployeeNumber = document.getElementById("salesRepEmployeeNumber").value
    customer.creditLimit = document.getElementById("creditLimit").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/customers/save',
        data: customer
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
        url: 'http://localhost:8084/Actividad2_war_exploded/customers/delete/'+id
    }).done(res => {
        console.log(res);
    });
}*/


const update = () => {

    let customer = new Object();
    let id = document.getElementById("customerNumber").value
    customer.customerNumber = document.getElementById("customerNumber").value
    customer.customerName = document.getElementById("customerName").value
    customer.contactLastName = document.getElementById("contactLastName").value
    customer.contactFirstName = document.getElementById("contactFirstName").value
    customer.phone = document.getElementById("phone").value
    customer.addressLine1 = document.getElementById("addressLine1").value
    customer.addressLine2 = document.getElementById("addressLine2").value
    customer.city = document.getElementById("city").value
    customer.state = document.getElementById("state").value
    customer.postalCode = document.getElementById("postalCode").value
    customer.country = document.getElementById("country").value
    customer.salesRepEmployeeNumber = document.getElementById("salesRepEmployeeNumber").value
    customer.creditLimit = document.getElementById("creditLimit").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/customers/save'+id,
        data: customer
    }).done(res => {
        console.log(res)
        guardar.style.display = "block"
        modificar.style.display = "none"

        document.getElementById("customerNumber").value=""
        document.getElementById("customerName").value=""
        document.getElementById("contactLastName").value=""
        document.getElementById("contactFirstName").value=""
        document.getElementById("phone").value=res.phone
        document.getElementById("addressLine1").value=""
        document.getElementById("addressLine2").value=""
        document.getElementById("city").value=""
        document.getElementById("state").value=""
        document.getElementById("postalCode").value=""
        document.getElementById("country").value=""
        document.getElementById("salesRepEmployeeNumber").value=""
        document.getElementById("creditLimit").value=""
        
    });
}

